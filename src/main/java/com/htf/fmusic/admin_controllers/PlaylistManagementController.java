package com.htf.fmusic.admin_controllers;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Genre;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.PlaylistType;
import com.htf.fmusic.services.ArtistService;
import com.htf.fmusic.services.GenreService;
import com.htf.fmusic.services.PlaylistService;
import com.htf.fmusic.services.PlaylistTypeService;
import com.htf.fmusic.utils.FmusicFunctions;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin/playlist")
public class PlaylistManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistManagementController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final PlaylistService playlistService;
    private final PlaylistTypeService plTypeService;
    private final ArtistService artistService;
    private final GenreService genreService;

    @Autowired
    PlaylistManagementController(PlaylistService playlistService, PlaylistTypeService plTypeService, ArtistService artistService,
            GenreService genreService, ServletContext servletContext) {
        LOGGER.info("Inside constructor of PlaylistManagementController.");

        this.playlistService = playlistService;
        this.plTypeService = plTypeService;
        this.artistService = artistService;
        this.genreService = genreService;
        this.ABSTRACT_PATH = "/static/img/playlist/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    //-------------------Retrieve All Playlists-------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listPlaylists(Model model) {
        List<Playlist> playlists = playlistService.findAll();
        model.addAttribute("playlists", playlists);
        model.addAttribute("title", "Manage Playlists | FMusic Administration");
        return "admin/playlist/list";
    }

    //-------------------Retrieve Single Playlist-----------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);
        model.addAttribute("title", "Playlist Details | FMusic Administration");
        return "admin/playlist/details";
    }

    //-------------------Create a Playlist------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newPlaylist(Model model) {
        Playlist playlist = new Playlist();
        model.addAttribute("playlist", playlist);
        model.addAttribute("title", "Add a new Playlist | FMusic Administration");
        return "admin/playlist/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String savePlaylist(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, @RequestParam String save,
            @RequestParam MultipartFile image, Model model, RedirectAttributes redirectAttributes) throws IOException {
        boolean hasErrors = false;
        if (image.getSize() > 1024000) {
            model.addAttribute("imgError", "File size must be less than 1 MB.");
            hasErrors = true;
        }
        if (result.hasErrors() || hasErrors) {
            return "admin/playlist/create";
        }

        if (!image.isEmpty()) {
            String uploaded = FmusicFunctions.uploadFile(image, DIRECTORY);
            String imageUrl = ABSTRACT_PATH + uploaded;
            playlist.setImageUrl(imageUrl);
        }
        Playlist r = playlistService.create(playlist);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New playlist has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a Playlist ----------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editPlaylist(@PathVariable Integer id, Model model) {
        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);
        model.addAttribute("title", "Edit Playlist details | FMusic Administration");
        return "admin/playlist/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Playlist playlist, BindingResult result, @PathVariable Integer id, @RequestParam String save,
            Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/playlist/edit";
        }

        playlistService.update(playlist);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This playlist has been changed successfully.");
            return "redirect:edit-" + playlist.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a Playlist ---------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deletePlaylist(@PathVariable Integer id, Model model) {
        playlistService.delete(id);
        return "redirect:list";
    }

    //------------------- Model Attributes -----------------------------------------------------
    @ModelAttribute(value = "artists")
    private List<Artist> initializeArtists() {
        return artistService.findAll();
    }

    @ModelAttribute(value = "genres")
    private List<Genre> initializeGenres() {
        return genreService.findAll();
    }

    @ModelAttribute(value = "plTypes")
    private List<PlaylistType> initializePlaylistTypes() {
        return plTypeService.findAll();
    }
}
