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

            Role role3 = new Role();
            role3.setType(RoleType.USER.getRoleType());
            Role userRole = roleService.create(role2);

            User user = new User();
            user.setUsername("fmusic");
            user.setPassword("fmusic");
            user.setFullname("HTFeeds");
            user.setSex("Male");
            user.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("07/01/1991"));
            user.setPhoneNumber("0932569525");
            user.setEmail("htf52gl@gmail.com");
            user.setImageUrl("/static/img/user/htfeeds.jpg");
            user.getRoles().add(adminRole);
            user.getRoles().add(dbaRole);
            user.getRoles().add(userRole);

            userService.create(user);

            //Create X Album
            create();

            //Create some Genre
            Genre genre = new Genre();
            genre.setName("Pop");

            Genre genre2 = new Genre();
            genre2.setName("Rock");

            Genre genre3 = new Genre();
            genre3.setName("Rap");

            Genre genre4 = new Genre();
            genre4.setName("R&B");

            Genre genre5 = new Genre();
            genre5.setName("Country");

            Genre genre6 = new Genre();
            genre6.setName("Dance");

            Genre popG = genreService.create(genre);
            Genre rockG = genreService.create(genre2);
            Genre rapG = genreService.create(genre3);
            Genre rnbG = genreService.create(genre4);
            Genre country = genreService.create(genre5);
            Genre dance = genreService.create(genre6);

            //Create 20 Artsit
            Artist a = new Artist();
            a.setName("Generateed Artsit");
            a.setSex("Male");
            a.setCountry(Country.VN.getCountry());
            a.setImageUrl("/static/img/artist/edsheeran.jpg");
            Artist artist = artistService.create(a);

            //Create 100 Songs
            for (int i = 0; i < 100; i++) {
                Song s = new Song();
                s.setName("Generated song " + i);
                s.setUrl("/static/data/mp3/Runaway - Ed Sheeran.mp3");
                s.addArtist(artist);
                s.setIsPublished(true);
                if (i < 50) {
                    s.setGenre(popG);
                } else if (i < 60) {
                    s.setGenre(rockG);
                } else if (i < 70) {
                    s.setGenre(rapG);
                } else if (i < 80) {
                    s.setGenre(country);
                } else if (i < 90) {
                    s.setGenre(rnbG);
                }
                songService.create(s);
            }
        }

        //Call one time
        //create50Playlits();
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
        pl.setType(PlaylistType.OFFICIAL.getPlaylistType());
        pl.setOnHome(true);
        pl.setSlideActived(true);
        Playlist playlist = playlistService.create(pl);

        Song s1 = new Song();
        s1.setName("One");
        s1.addArtist(artist);
        s1.setUrl("/static/data/mp3/One - Ed Sheeran.mp3");
        s1.setIsPublished(true);
        s1.setOnHome(true);
        s1.setImageUrl("/static/img/artist/edsheeran.jpg");

        Song s2 = new Song();
        s2.setName("I'm A Mess");
        s2.addArtist(artist);
        s2.setUrl("/static/data/mp3/I'm A Mess - Ed Sheeran.mp3");
        s2.setIsPublished(true);
        s2.setOnHome(true);

        Song s3 = new Song();
        s3.setName("Sing");
        s3.addArtist(artist);
        s3.setUrl("/static/data/mp3/Sing - Ed Sheeran.mp3");
        s3.setIsPublished(true);
        s3.setOnHome(true);

        Song s4 = new Song();
        s4.setName("Don't");
        s4.addArtist(artist);
        s4.setUrl("/static/data/mp3/Don't - Ed Sheeran.mp3");
        s4.setIsPublished(true);
        s4.setOnHome(true);

        Song s5 = new Song();
        s5.setName("Nina");
        s5.addArtist(artist);
        s5.setUrl("/static/data/mp3/Nina - Ed Sheeran.mp3");
        s5.setIsPublished(true);
        s5.setOnHome(true);

        Song s6 = new Song();
        s6.setName("Photograph");
        s6.addArtist(artist);
        s6.setUrl("/static/data/mp3/Photograph - Ed Sheeran.mp3");
        s6.setIsPublished(true);
        s6.setOnHome(true);

        Song s7 = new Song();
        s7.setName("Bloodstream");
        s7.addArtist(artist);
        s7.setUrl("/static/data/mp3/bloodstream.mp3");
        s7.setIsPublished(true);
        s7.setOnHome(true);

        Song s8 = new Song();
        s8.setName("Tenerife Sea");
        s8.addArtist(artist);
        s8.setUrl("/static/data/mp3/Tenerife Sea - Ed Sheeran.mp3");
        s8.setIsPublished(true);
        s8.setOnHome(true);

        Song s9 = new Song();
        s9.setName("Runaway");
        s9.addArtist(artist);
        s9.setUrl("/static/data/mp3/Runaway - Ed Sheeran.mp3");
        s9.setIsPublished(true);
        s9.setOnHome(true);

        Song s10 = new Song();
        s10.setName("The Man");
        s10.addArtist(artist);
        s10.setUrl("/static/data/mp3/The Man - Ed Sheeran.mp3");
        s10.setIsPublished(true);
        s10.setOnHome(true);

        Song s11 = new Song();
        s11.setName("Thinking Out Loud");
        s11.addArtist(artist);
        s11.setUrl("/static/data/mp3/Thinking Out Loud.mp3");
        s11.setIsPublished(true);
        s11.setOnHome(true);

        Song s12 = new Song();
        s12.setName("I See Fire");
        s12.addArtist(artist);
        s12.setUrl("/static/data/mp3/I See Fire - Ed Sheeran.mp3");
        s12.setIsPublished(true);
        s12.setOnHome(true);

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
        lsSongs.add(songService.create(s12));

        playlistService.addSongs(playlist.getId(), lsSongs);
    }

    public void create50Playlits() throws ParseException {
        Artist a = new Artist();
        a.setName("Robin Thicke");
        Artist artist = artistService.create(a);

        Song s1 = new Song();
        s1.setName("I Can't Make You Love Me");
        s1.addArtist(artist);
        s1.setUrl("/static/data/mp3/Josh Kaufman - I Can't Make You Love Me - Studio Version - The Voice 2014.mp3");
        s1.setIsPublished(true);
        s1.setOnHome(true);
        
        Song s2 = new Song();
        s2.setName("Too Little Too Late");
        s2.addArtist(artist);
        s2.setUrl("/static/data/mp3/Too Little Too Late.mp3");
        s2.setIsPublished(true);
        s2.setOnHome(true);
        
        List<Song> lsSongs = new ArrayList<Song>();
        lsSongs.add(songService.create(s1));
        lsSongs.add(songService.create(s2));

        for (int i = 0; i < 100; i++) {
            Playlist pl = new Playlist();
            pl.setName("Generated Album " + i);
            pl.setArtist(artist);
            pl.setTotalViews(1234567);
            pl.setType(PlaylistType.COLLECTION.getPlaylistType());

            Playlist playlist = playlistService.create(pl);

            playlistService.addSongs(playlist.getId(), lsSongs);
        }

    }
}
