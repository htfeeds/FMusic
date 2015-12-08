package com.htf.fmusic.admin_controllers;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htf.fmusic.models.Song;
import com.htf.fmusic.services.SongService;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin/song")
public class SongManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongManagementController.class);

    private final SongService songService;

    @Autowired
    SongManagementController(SongService songService) {
        LOGGER.info("Inside constructor of SongManagementController.");
        this.songService = songService;
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
    public String saveSong(@Valid @ModelAttribute("song") Song song, BindingResult result, @RequestParam String save, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/song/create";
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
    public String update(@Valid @ModelAttribute Song song, BindingResult result, @PathVariable Integer id, @RequestParam String save, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/song/edit";
        }

        songService.update(song);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This song has been changed successfully.");
            return "redirect:edit-" + song.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a Song ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deleteSong(@PathVariable Integer id, Model model) {
        songService.delete(id);
        return "redirect:list";
    }
}
