package com.htf.fmusic.user_controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.htf.fmusic.models.Genre;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.services.GenreService;
import com.htf.fmusic.services.PlaylistService;
import com.htf.fmusic.services.SongPlaylistService;
import com.htf.fmusic.services.SongService;
import com.htf.fmusic.services.UserService;

/**
 * @author HTFeeds
 */
@Controller
@RequestMapping("/playlist")
public class PlaylistController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistController.class);

    private final GenreService genreService;
    private final PlaylistService playlistService;
    private final SongPlaylistService songPlaylistService;
    private final SongService songService;
    private final UserService userService;

    @Autowired
    public PlaylistController(GenreService genreService, PlaylistService playlistService, SongPlaylistService songPlaylistService,
            SongService songService, UserService userService) {
        super();
        LOGGER.info("Inside PlaylistController constructor.");
        this.genreService = genreService;
        this.playlistService = playlistService;
        this.songPlaylistService = songPlaylistService;
        this.songService = songService;
        this.userService = userService;
    }

    @RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        List<Genre> genres = genreService.findAll();

        Page<Playlist> page = playlistService.getAllOfficialAndCollectionPlaylists(1);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 3);
        int end = Math.min(begin + 7, page.getTotalPages());

        model.addAttribute("currentGenre", "All");
        model.addAttribute("genres", genres);
        model.addAttribute("page", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "playlist/index";
    }

    @RequestMapping(value = { "/{genre}/{pageNumber}" }, method = RequestMethod.GET)
    public String index(@PathVariable String genre, @PathVariable Integer pageNumber, Model model) {
        List<Genre> genres = genreService.findAll();

        Page<Playlist> page;
        if (genre.equals("All")) {
            page = playlistService.getAllOfficialAndCollectionPlaylists(pageNumber);
        } else {
            page = playlistService.getPlaylistByGenreName(genre, pageNumber);
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

        return "playlist/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, HttpServletResponse response, Model model) throws JsonProcessingException {
        Playlist playlist = playlistService.getById(id);
        if (playlist == null) {
            //throws Exception
        }

        List<SongPlaylist> songs = songPlaylistService.findByPlaylistOrderByOrderAsc(playlist);
        if (songs.size() == 0) {
            //throws Exception
        }

        List<Playlist> relatedPlaylists = playlistService.getRelatedPlaylists(playlist.getArtist());
        List<Song> relatedSongs = songService.getRelatedSongs(playlist.getArtist());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new Hibernate4Module());
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(songs);

        model.addAttribute("playlist", playlist);
        model.addAttribute("songs", json);
        model.addAttribute("relatedPlaylists", relatedPlaylists);
        model.addAttribute("relatedSongs", relatedSongs);

        //ADD TO COOKIE
        //response.addCookie(new Cookie("recent", "{id}:{times}\n"));

        return "playlist/details";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPlaylistToUser(@RequestParam Integer id, Principal principal) {
        if (principal == null) {
            return "unauthorized";
        }

        Playlist playlist = playlistService.findById(id);
        boolean b = userService.addPlaylistToUser(principal.getName(), playlist);
        if (b) {
            return "success";
        } else {
            return "fail";
        }
    }
}
