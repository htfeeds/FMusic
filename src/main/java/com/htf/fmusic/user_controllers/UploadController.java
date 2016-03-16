package com.htf.fmusic.user_controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.htf.fmusic.enums.PlaylistType;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.services.ArtistService;
import com.htf.fmusic.services.GenreService;
import com.htf.fmusic.services.SongService;
import com.htf.fmusic.utils.FmusicFunctions;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    private final String ABSTRACT_PATH;
    private final String DIRECTORY;

    private final SongService songService;
    private final GenreService genreService;
    private final ArtistService artistService;

    @Autowired
    UploadController(SongService songService, GenreService genreService, ServletContext servletContext, ArtistService artistService) {
        LOGGER.info("Inside constructor of UploadController.");
        this.songService = songService;
        this.genreService = genreService;
        this.artistService = artistService;
        this.ABSTRACT_PATH = "/static/data/mp3/";
        this.DIRECTORY = servletContext.getRealPath(ABSTRACT_PATH) + "/";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String upload(Model model) {
        Song song = new Song();
        model.addAttribute("song", song);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("artists", artistService.findAll());

        return "upload/form";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String doUpload(@Valid @ModelAttribute("song") Song song, BindingResult result, @RequestParam MultipartFile file, Model model)
            throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("artists", artistService.findAll());
            return "upload/form";
        }
        //Validate file
        if (file.isEmpty() || file.getSize() > 10024000) {
            model.addAttribute("fileError", "File cannot null and file size must be less than 10 MB.");
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("artists", artistService.findAll());
            return "upload/form";
        }
        //Upload file
        String uploaded = FmusicFunctions.uploadFile(file, DIRECTORY);
        String fileUrl = ABSTRACT_PATH + uploaded;
        song.setUrl(fileUrl);

        song.setType(PlaylistType.USER.getPlaylistType());
        songService.create(song);

        return "upload/success";
    }

}
