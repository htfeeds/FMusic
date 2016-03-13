package com.htf.fmusic.admin_controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htf.fmusic.enums.Country;
import com.htf.fmusic.models.Artist;
import com.htf.fmusic.services.ArtistService;
import com.htf.fmusic.utils.FmusicFunctions;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin/artist")
@SessionAttributes("countries")
public class ArtistManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final ArtistService artistService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ArtistManagementController(ArtistService artistService, ServletContext servletContext) {
        LOGGER.info("Inside constructor of ArtistManagementController.");

        this.artistService = artistService;
        this.ABSTRACT_PATH = "/static/img/artist/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    //-------------------Retrieve All Artists---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listArtists(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("title", "Manage Artists | FMusic Administration");
        return "admin/artist/list";
    }

    //-------------------Retrieve Single Artist-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Artist Details | FMusic Administration");
        return "admin/artist/details";
    }

    //-------------------Create a Artist--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newArtist(Model model) {
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Add a new Artist | FMusic Administration");
        return "admin/artist/create_dpk";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveArtist(@Valid @ModelAttribute("artist") Artist artist, BindingResult result, @RequestParam String save,
            @RequestParam MultipartFile image, @RequestParam MultipartFile cover, Model model, RedirectAttributes redirectAttributes)
                    throws IOException {
        boolean hasErrors = false;
        if (!artistService.isArtistNameUnique(artist.getId(), artist.getName())) {
            FieldError nameError = new FieldError("artist", "name",
                    messageSource.getMessage("UniqueName.artist.name", new String[] { artist.getName() }, Locale.getDefault()));
            result.addError(nameError);
        }
        if (image.getSize() > 1024000) {
            model.addAttribute("imgError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (cover.getSize() > 1024000) {
            model.addAttribute("coverError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (result.hasErrors() || hasErrors) {
            return "admin/artist/create_dpk";
        }

        if (!image.isEmpty()) {
            String uploaded = FmusicFunctions.uploadFile(image, DIRECTORY);
            String imageUrl = ABSTRACT_PATH + uploaded;
            artist.setImageUrl(imageUrl);
        }

        if (!cover.isEmpty()) {
            String uploaded = FmusicFunctions.uploadFile(cover, DIRECTORY);
            String coverUrl = ABSTRACT_PATH + uploaded;
            artist.setCoverImageUrl(coverUrl);
        }

        Artist r = artistService.create(artist);
        model.asMap().clear();

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New artist has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Artist ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editArtist(@PathVariable Integer id, Model model) {
        Artist artist = artistService.findById(id);
        model.addAttribute("artist", artist);
        model.addAttribute("title", "Edit Artist details | FMusic Administration");
        return "admin/artist/edit_dpk";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Artist artist, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
            RedirectAttributes redirectAttributes) {
        if (!artistService.isArtistNameUnique(artist.getId(), artist.getName())) {
            FieldError nameError = new FieldError("artist", "name",
                    messageSource.getMessage("UniqueName.artist.name", new String[] { artist.getName() }, Locale.getDefault()));
            result.addError(nameError);
        }
        if (result.hasErrors()) {
            return "admin/artist/edit_dpk";
        }

        artistService.update(artist);
        model.asMap().clear();

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This artist has been changed successfully.");
            return "redirect:edit-" + artist.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a Artist ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteArtist(@PathVariable Integer id, Model model) {
        artistService.delete(id);
        model.asMap().clear();

        return "redirect:list";
    }

    //------------------- Model Attributes -----------------------------------------------------
    @ModelAttribute(value = "countries")
    private List<String> initializeCountries() {
        LOGGER.info("Create countries ModelAttribute");

        List<String> countries = new ArrayList<String>();
        countries.add(Country.US.getCountry());
        countries.add(Country.UK.getCountry());
        countries.add(Country.VN.getCountry());
        countries.add(Country.FR.getCountry());
        countries.add(Country.KR.getCountry());
        countries.add(Country.CA.getCountry());
        countries.add(Country.UNKNOWN.getCountry());
        return countries;
    }
}
