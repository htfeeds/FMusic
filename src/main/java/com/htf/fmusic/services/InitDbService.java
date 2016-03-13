package com.htf.fmusic.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htf.fmusic.enums.Country;
import com.htf.fmusic.enums.PlaylistType;
import com.htf.fmusic.enums.RoleType;
import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Genre;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Role;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.models.User;

/**
 * @author HTFeeds
 */
@Transactional
@Service
public class InitDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDbService.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private SongService songService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private GenreService genreService;

    @PostConstruct
    public void init() throws ParseException {
        if (roleService.findByType(RoleType.ADMIN.getRoleType()) == null) {
            LOGGER.info("Create a new Admin");

            Role role = new Role();
            role.setType(RoleType.ADMIN.getRoleType());
            Role adminRole = roleService.create(role);

            Role role2 = new Role();
            role2.setType(RoleType.DBA.getRoleType());
            Role dbaRole = roleService.create(role2);

            User user = new User();
            user.setUsername("htfeeds");
            user.setPassword("124356");
            user.setFullname("HTFeeds");
            user.setSex("Male");
            user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("07/01/1991"));
            user.setPhoneNumber("0932569525");
            user.setEmail("htf52gl@gmail.com");
            user.setImageUrl("/static/img/user/htfeeds.jpg");
            user.getRoles().add(adminRole);
            user.getRoles().add(dbaRole);

            userService.create(user);

            //Create X Album
            create();

            //Create a new Artist
            Artist a = new Artist();
            a.setName("Generated Artist");
            Artist artist = artistService.create(a);

            //Create some Genre
            Genre genre = new Genre();
            genre.setName("Pop");

            Genre genre2 = new Genre();
            genre2.setName("Rock");

            Genre genre3 = new Genre();
            genre3.setName("Rap");

            Genre genre4 = new Genre();
            genre4.setName("R&B");

            Genre popG = genreService.create(genre);
            Genre rockG = genreService.create(genre2);
            Genre rapG = genreService.create(genre3);
            Genre rnbG = genreService.create(genre4);

            //Create 1000Songs For Test
            for (int i = 0; i < 1000; i++) {
                Song s = new Song();
                s.setName("Generated song " + i);
                s.addArtist(artist);
                s.setIsPublished(true);
                if (i < 250) {
                    s.setGenre(popG);
                } else if (i < 500) {
                    s.setGenre(rockG);
                } else if (i < 750) {
                    s.setGenre(rapG);
                } else {
                    s.setGenre(rnbG);
                }

                songService.create(s);
            }

            //Create 1000Album For Test
            for (int i = 0; i < 1000; i++) {
                Playlist pl = new Playlist();
                pl.setName("Generated album " + i);
                pl.setArtist(artist);
                pl.setImageUrl("/static/img/playlist/xdeluxe.png");
                if (i < 250) {
                    pl.setType(PlaylistType.OFFICIAL.getPlaylistType());
                    pl.setSlideImageUrl("/static/img/playlist/xalbumbanner.jpg");
                    pl.setGenre(genre4);
                    pl.setGenre(popG);
                } else if (i < 500) {
                    pl.setType(PlaylistType.COLLECTION.getPlaylistType());
                    pl.setGenre(rockG);
                } else if (i < 750) {
                    pl.setGenre(rapG);
                } else {
                    pl.setGenre(rnbG);
                }
                
                playlistService.create(pl);
            }
        }

    }

    public void create() throws ParseException {
        Artist a = new Artist();
        a.setName("Ed Sheeran");
        a.setRealName("Edward Christopher Sheeran");
        a.setSex("Male");
        a.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("17/2/1991"));
        a.setCountry(Country.UK.getCountry());
        a.setImageUrl("/static/img/artist/edsheeran.jpg");
        Artist artist = artistService.create(a);

        Playlist pl = new Playlist();
        pl.setName("X (Deluxe Edition)");
        pl.setArtist(artist);
        pl.setTotalViews(0);
        pl.setImageUrl("/static/img/playlist/xdeluxe.png");
        pl.setSlideImageUrl("/static/img/playlist/xalbumbanner.jpg");
        pl.setType(PlaylistType.TOP.getPlaylistType());
        pl.setOnHome(true);
        pl.setSlideActived(true);
        Playlist playlist = playlistService.create(pl);

        Song s1 = new Song();
        s1.setName("One");
        s1.addArtist(artist);
        s1.setUrl("/static/data/mp3/one.mp3");
        s1.setOnHome(true);
        s1.setImageUrl("/static/img/artist/edsheeran.jpg");

        Song s2 = new Song();
        s2.setName("I'm A Mess");
        s2.addArtist(artist);
        s2.setUrl("/static/data/mp3/imamess.mp3");
        s2.setOnHome(true);

        Song s3 = new Song();
        s3.setName("Sing");
        s3.addArtist(artist);
        s3.setUrl("/static/data/mp3/sing.mp3");

        Song s4 = new Song();
        s4.setName("Don't");
        s4.addArtist(artist);
        s4.setUrl("/static/data/mp3/dont.mp3");

        Song s5 = new Song();
        s5.setName("Nina");
        s5.addArtist(artist);
        s5.setUrl("/static/data/mp3/nina.mp3");

        Song s6 = new Song();
        s6.setName("Photograph");
        s6.addArtist(artist);
        s6.setUrl("/static/data/mp3/photograph.mp3");

        Song s7 = new Song();
        s7.setName("Bloodstream");
        s7.addArtist(artist);
        s7.setUrl("/static/data/mp3/bloodstream.mp3");

        Song s8 = new Song();
        s8.setName("Tenerife Sea");
        s8.addArtist(artist);
        s8.setUrl("/static/data/mp3/tenerifesea.mp3");

        Song s9 = new Song();
        s9.setName("Runaway");
        s9.addArtist(artist);
        s9.setUrl("/static/data/mp3/runaway.mp3");

        Song s10 = new Song();
        s10.setName("The Man");
        s10.addArtist(artist);
        s10.setUrl("/static/data/mp3/theman.mp3");

        Song s11 = new Song();
        s11.setName("Thinking Out Loud");
        s11.addArtist(artist);
        s11.setUrl("/static/data/mp3/thinkingoutloud.mp3");

        List<Song> lsSongs = new ArrayList<Song>();
        lsSongs.add(songService.create(s1));
        lsSongs.add(songService.create(s2));
        lsSongs.add(songService.create(s3));
        lsSongs.add(songService.create(s4));
        lsSongs.add(songService.create(s5));
        lsSongs.add(songService.create(s6));
        lsSongs.add(songService.create(s7));
        lsSongs.add(songService.create(s8));
        lsSongs.add(songService.create(s9));
        lsSongs.add(songService.create(s10));
        lsSongs.add(songService.create(s11));

        playlistService.addSongs(playlist.getId(), lsSongs);
    }
}
