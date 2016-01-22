package com.htf.fmusic.admin_controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Genre;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.models.Views;
import com.htf.fmusic.services.ArtistService;
import com.htf.fmusic.services.GenreService;
import com.htf.fmusic.services.SongService;
import com.htf.fmusic.utils.FmusicFunctions;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin/song")
public class SongManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final SongService songService;
    private final GenreService genreService;
    private final ArtistService artistService;

    @Autowired
    SongManagementController(SongService songService, GenreService genreService, ServletContext servletContext, ArtistService artistService) {
        LOGGER.info("Inside constructor of SongManagementController.");

        this.songService = songService;
        this.genreService = genreService;
        this.artistService = artistService;
        this.ABSTRACT_PATH = "/static/data/mp3/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    //-------------------Retrieve All Songs---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listSongs(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("title", "Manage Songs | FMusic Administration");
        return "admin/song/list";
    }

    //-------------------Retrieve Single Song-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        model.addAttribute("title", "Song Details | FMusic Administration");
        return "admin/song/details";
    }

    //-------------------Create a Song--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newSong(Model model) {
        Song song = new Song();
        model.addAttribute("song", song);
        model.addAttribute("title", "Add a new Song | FMusic Administration");
        return "admin/song/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String saveSong(@Valid @ModelAttribute("song") Song song, BindingResult result, @RequestParam String save,
            @RequestParam(required = false) String resource, @RequestParam MultipartFile appFile, @RequestParam MultipartFile gFile, Model model,
            RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "admin/song/create";
        }

        if (resource.equals("application")) {
            if (appFile.isEmpty() || appFile.getSize() > 10024000) {
                model.addAttribute("appFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/create";
            }
            //Upload file to application
            String uploaded = FmusicFunctions.uploadFile(appFile, DIRECTORY);
            String fileUrl = ABSTRACT_PATH + uploaded;
            song.setUrl(fileUrl);
        } else if (resource.equals("google")) {
            if (gFile.isEmpty() || gFile.getSize() > 10024000) {
                model.addAttribute("gFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/create";
            }
            //Upload file to google drive
        }

        Song r = songService.create(song);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New song has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Song ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editSong(@PathVariable Integer id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        model.addAttribute("title", "Edit Song details | FMusic Administration");
        return "admin/song/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Song song, BindingResult result, @PathVariable Integer id, @RequestParam String save,
            @RequestParam(required = false) String resource, @RequestParam MultipartFile appFile, @RequestParam MultipartFile gFile, Model model,
            RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "admin/song/edit";
        }

        if (resource.equals("application")) {
            if (appFile.isEmpty() || appFile.getSize() > 10024000) {
                model.addAttribute("appFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/edit";
            }
            //Upload file to application
            String uploaded = FmusicFunctions.uploadFile(appFile, DIRECTORY);
            String fileUrl = ABSTRACT_PATH + uploaded;
            //Change SongUrl
            song.setUrl(fileUrl);
        } else if (resource.equals("google")) {
            if (gFile.isEmpty() || gFile.getSize() > 10024000) {
                model.addAttribute("gFileError", "File cannot null and file size must be less than 10 MB.");
                return "admin/song/edit";
            }
            //Upload file to google drive
        }

        songService.update(song);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This song has been changed successfully.");
            return "redirect:edit-" + song.getId();
        }

        return "redirect:list";
    }

    //------------------- Get Artists of Song -------------------------------------------------
    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/GetArtists", method = RequestMethod.GET)
    public Set<Artist> getMappedArtists(@PathVariable Integer id) {
        Song song = songService.findById(id);
        if (song != null) {
            return song.getArtists();
        }
        return null;
    }

    //------------------- Mapping Artist To Song------------------------------------------------
    @JsonView(Views.Summary.class)
    @ResponseBody
    @RequestMapping(value = "/{id}/AddArtist", method = RequestMethod.POST)
    public Artist mappingArtist(@PathVariable Integer id, @RequestParam Artist artist) {
        Artist added = songService.addArtist(id, artist);
        return added;
    }

    //------------------- Remove Artist From Song ---------------------------------------------
    @ResponseBody
    @RequestMapping(value = "/{id}/RemoveArtist", method = RequestMethod.POST)
    public String removeMappedArtist(@PathVariable Integer id, @RequestParam Artist artist) {
        Boolean removed = songService.removeArtist(id, artist);
        return removed.toString();
    }

    //------------------- Delete a Song ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteSong(@PathVariable Integer id, Model model) {
        songService.delete(id);
        return "redirect:list";
    }

    //------------------- Model Attributes -----------------------------------------------------
    @ModelAttribute(value = "genres")
    private List<Genre> initializeGenres() {
        return genreService.findAll();
    }

    @ModelAttribute(value = "artists")
    private List<Artist> initializeArtists() {
        return artistService.findAll();
    }

    @ModelAttribute(value = "songArtists")
    private List<Artist> getSongArtists() {
        return artistService.findAll();
    }
}
