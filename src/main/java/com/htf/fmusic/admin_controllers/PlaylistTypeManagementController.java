package com.htf.fmusic.admin_controllers;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.htf.fmusic.models.PlaylistType;
import com.htf.fmusic.services.PlaylistTypeService;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/admin/pltype")
public class PlaylistTypeManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistTypeManagementController.class);

    private final PlaylistTypeService playlistTypeService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PlaylistTypeManagementController(PlaylistTypeService playlistTypeService) {
        LOGGER.info("Inside constructor of PlaylistTypeManagementController.");
        this.playlistTypeService = playlistTypeService;
    }

    //-------------------Retrieve All PlaylistTypes---------------------------------------------------
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listPlaylistTypes(Model model) {
        List<PlaylistType> playlistTypes = playlistTypeService.findAll();
        model.addAttribute("playlistTypes", playlistTypes);
        model.addAttribute("title", "Manage PlaylistTypes | FMusic Administration");
        return "admin/pltype/list";
    }

    //-------------------Retrieve Single PlaylistType-------------------------------------------------
    @RequestMapping(value = "/details-{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        PlaylistType playlistType = playlistTypeService.findById(id);
        model.addAttribute("playlistType", playlistType);
        model.addAttribute("title", "PlaylistType Details | FMusic Administration");
        return "admin/pltype/details";
    }

    //-------------------Create a PlaylistType--------------------------------------------------------
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newPlaylistType(Model model) {
        PlaylistType playlistType = new PlaylistType();
        model.addAttribute("playlistType", playlistType);
        model.addAttribute("title", "Add a new PlaylistType | FMusic Administration");
        return "admin/pltype/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String savePlaylistType(@Valid @ModelAttribute("playlistType") PlaylistType playlistType, BindingResult result, @RequestParam String save,
            Model model, RedirectAttributes redirectAttributes) {
        if (!playlistTypeService.isPlaylistTypeUnique(playlistType.getId(), playlistType.getName())) {
            FieldError nameError = new FieldError("playlistType", "name",
                    messageSource.getMessage("UniqueName.playlistType.name", new String[] { playlistType.getName() }, Locale.getDefault()));
            result.addError(nameError);
        }
        if (result.hasErrors()) {
            return "admin/pltype/create";
        }

        PlaylistType r = playlistTypeService.create(playlistType);

        if (save.equals("Create and Edit")) {
            redirectAttributes.addFlashAttribute("success", "New PlType has been created successfully.");
            return "redirect:edit-" + r.getId();
        }

        return "redirect:list";
    }

    //------------------- Update a PlaylistType ------------------------------------------------------
    @RequestMapping(value = "/edit-{id}", method = RequestMethod.GET)
    public String editPlaylistType(@PathVariable Integer id, Model model) {
        PlaylistType playlistType = playlistTypeService.findById(id);
        model.addAttribute("playlistType", playlistType);
        model.addAttribute("title", "Edit PlaylistType details | FMusic Administration");
        return "admin/pltype/edit";
    }

    @RequestMapping(value = "/edit-{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute PlaylistType playlistType, BindingResult result, @PathVariable Integer id, @RequestParam String save,
            Model model, RedirectAttributes redirectAttributes) {
        if (!playlistTypeService.isPlaylistTypeUnique(playlistType.getId(), playlistType.getName())) {
            FieldError nameError = new FieldError("playlistType", "name",
                    messageSource.getMessage("UniqueName.playlistType.name", new String[] { playlistType.getName() }, Locale.getDefault()));
            result.addError(nameError);
        }
        if (result.hasErrors()) {
            return "admin/pltype/edit";
        }

        playlistTypeService.update(playlistType);

        if (save.equals("Save and Continue")) {
            redirectAttributes.addFlashAttribute("success", "This PlType has been changed successfully.");
            return "redirect:edit-" + playlistType.getId();
        }

        return "redirect:list";
    }

    //------------------- Delete a PlaylistType ------------------------------------------------------
    @RequestMapping(value = "/delete-{id}", method = RequestMethod.GET)
    public String deletePlaylistType(@PathVariable Integer id, Model model) {
        playlistTypeService.delete(id);
        return "redirect:list";
    }
}
