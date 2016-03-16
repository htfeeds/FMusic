package com.htf.fmusic.user_controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.htf.fmusic.enums.Country;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.services.PlaylistService;
import com.htf.fmusic.services.SongPlaylistService;
import com.htf.fmusic.services.SongService;

/**
 * @author HTFeeds
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final PlaylistService playlistService;
    private final SongService songService;
    private final SongPlaylistService songPlaylistService;

    @Autowired
    HomeController(PlaylistService playlistService, SongService songService, SongPlaylistService songPlaylistService) {
        LOGGER.info("Inside constructor of HomeController.");
        this.playlistService = playlistService;
        this.songService = songService;
        this.songPlaylistService = songPlaylistService;
    }

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String home(@CookieValue(value = "recent", defaultValue = "") String recent, Locale locale, Model model, Principal principal) {
        LOGGER.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);

        List<Playlist> slidePlaylists = playlistService.getSlideActivedPlaylists();
        model.addAttribute("slidePlaylists", slidePlaylists);

        Page<Playlist> homePlaylists = playlistService.getHomePlaylists();
        model.addAttribute("homePlaylists", homePlaylists);

        List<Song> homeSongs = songService.getHomeSongs();
        model.addAttribute("homeSongs", homeSongs);

        Playlist topVnPl = playlistService.getLatestTopPlaylist(Country.VN.getCountry());
        model.addAttribute("topVnPl", topVnPl);
        List<SongPlaylist> topVn = songPlaylistService.findByPlaylistOrderByOrderAsc(topVnPl);
        model.addAttribute("topVn", topVn);

        Playlist topUsPl = playlistService.getLatestTopPlaylist(Country.US.getCountry());
        model.addAttribute("topUsPl", topUsPl);
        List<SongPlaylist> topUs = songPlaylistService.findByPlaylistOrderByOrderAsc(topUsPl);
        model.addAttribute("topUs", topUs);

        Playlist topKrPl = playlistService.getLatestTopPlaylist(Country.KR.getCountry());
        model.addAttribute("topKrPl", topKrPl);
        List<SongPlaylist> topKr = songPlaylistService.findByPlaylistOrderByOrderAsc(topKrPl);
        model.addAttribute("topKr", topKr);

        List<Playlist> recommendedPlaylists = playlistService.getRecommendedPlaylists(recent);
        model.addAttribute("recommendedPlaylists", recommendedPlaylists);

        if (principal != null) {
            List<Playlist> userPlaylists = playlistService.getTop3UserPlaylists(principal.getName());
            model.addAttribute("userPlaylists", userPlaylists);
        }

        return "home/index";
    }

}
