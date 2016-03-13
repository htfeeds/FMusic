package com.htf.fmusic.user_controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.htf.fmusic.models.Genre;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.services.GenreService;
import com.htf.fmusic.services.PlaylistService;
import com.htf.fmusic.services.SongService;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/song")
public class SongController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongController.class);

    private final GenreService genreService;
    private final SongService songService;
    private final PlaylistService playlistService;

    @Autowired
    public SongController(GenreService genreService, SongService songService, PlaylistService playlistService) {
        super();
        LOGGER.info("Inside SongController constructor.");
        this.genreService = genreService;
        this.songService = songService;
        this.playlistService = playlistService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        List<Genre> genres = genreService.findAll();

        Page<Song> page = songService.getAllSongs(1);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 3);
        int end = Math.min(begin + 7, page.getTotalPages());

        model.addAttribute("currentGenre", "All");
        model.addAttribute("genres", genres);
        model.addAttribute("page", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "song/index";
    }

    @RequestMapping(value = { "/{genre}/{pageNumber}" }, method = RequestMethod.GET)
    public String index(@PathVariable String genre, @PathVariable Integer pageNumber, Model model) {
        List<Genre> genres = genreService.findAll();

        Page<Song> page;
        if (genre.equals("All")) {
            page = songService.getAllSongs(pageNumber);
        } else {
            page = songService.getSongsByGenreName(genre, pageNumber);
        }

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 3);
        int end = Math.min(begin + 7, page.getTotalPages());

        model.addAttribute("currentGenre", genre);
        model.addAttribute("genres", genres);
        model.addAttribute("page", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "song/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Song song = songService.findById(id);

        if (song == null) {
            return "shared/404";
        }

        List<Playlist> relatedPlaylists = playlistService.getRelatedPlaylists(song.getArtists().iterator().next());
        List<Song> relatedSongs = songService.getRelatedSongs(song.getArtists().iterator().next());

        model.addAttribute("song", song);
        model.addAttribute("relatedPlaylists", relatedPlaylists);
        model.addAttribute("relatedSongs", relatedSongs);

        return "song/details";
    }
}
