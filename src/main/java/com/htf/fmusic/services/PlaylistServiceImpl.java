package com.htf.fmusic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htf.fmusic.enums.PlaylistType;
import com.htf.fmusic.exceptions.PlaylistNotFoundException;
import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Song;
import com.htf.fmusic.models.SongPlaylist;
import com.htf.fmusic.models.Week;
import com.htf.fmusic.repositories.PlaylistRepository;
import com.htf.fmusic.repositories.SongPlaylistRepository;
import com.htf.fmusic.repositories.UserRepository;
import com.htf.fmusic.repositories.WeekRepository;

/**
 * @author HTFeeds
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaylistServiceImpl.class);

    private static final int PAGE_SIZE = 25;

    private final PlaylistRepository repository;
    private final SongPlaylistRepository songPlaylistRepository;
    private final WeekRepository weekRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository repository, SongPlaylistRepository songPlaylistRepository, WeekRepository weekRepository,
            UserRepository userRepository) {
        super();
        this.repository = repository;
        this.songPlaylistRepository = songPlaylistRepository;
        this.weekRepository = weekRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> findAll() {
        LOGGER.info("Finding all playlist entries.");

        List<Playlist> playlistEntries = repository.findAll();
        LOGGER.info("Found {} playlist entries", playlistEntries.size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist findById(Integer id) {
        LOGGER.info("Finding playlist entry by using id: {}", id);

        Playlist playlistEntry = findPlaylistEntryById(id);
        LOGGER.info("Found playlist entry: {}", playlistEntry);

        return playlistEntry;
    }

    @Override
    @Transactional
    public Playlist create(Playlist newEntry) {
        LOGGER.info("Creating a new playlist entry by using information: {}", newEntry);

        Playlist created = repository.save(newEntry);
        LOGGER.info("Created a new playlist entry: {}", created);

        return created;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        LOGGER.info("Deleting a playlist entry with id: {}", id);

        Playlist deleted = findPlaylistEntryById(id);
        LOGGER.debug("Found playlist entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted user entry: {}");
    }

    @Override
    @Transactional
    public Playlist update(Playlist updatedEntry) {
        LOGGER.info("Updating the information of a playlist entry by using information: {}", updatedEntry);

        Playlist updated = findPlaylistEntryById(updatedEntry.getId());
        updated.update(updatedEntry.getName(), updatedEntry.getTotalViews(), updatedEntry.getWeekViews(), updatedEntry.getCountry(),
                updatedEntry.getArtist(), updatedEntry.getGenre(), updatedEntry.getType(), updatedEntry.getOnHome(), updatedEntry.getWeek(),
                updatedEntry.getSlideActived());
        repository.flush();

        LOGGER.info("Updated the information of the playlist entry: {}", updated);
        return updated;
    }

    @Override
    @Transactional
    public Playlist addSongs(Integer id, List<Song> songs) {
        Playlist playlist = findById(id);

        for (int i = 0; i < songs.size(); i++) {
            SongPlaylist sp = new SongPlaylist();
            sp.setPlaylist(playlist);
            sp.setSong(songs.get(i));
            sp.setOrder(i + 1);

            songPlaylistRepository.save(sp);
        }

        LOGGER.info("Updated the information of the playlist entry: {}", playlist);
        return playlist;
    }

    private Playlist findPlaylistEntryById(Integer id) {
        Optional<Playlist> playlistResult = repository.findOne(id);
        return playlistResult.orElseThrow(() -> new PlaylistNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getSlideActivedPlaylists() {
        LOGGER.info("Finding all playlist entries by slideActived: true.");

        List<Playlist> playlistEntries = repository.findTop5BySlideActivedOrderByIdDesc(true);
        LOGGER.info("Found {} playlist entries", playlistEntries.size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Playlist getLatestTopPlaylist(String country) {
        LOGGER.info("Finding lastest top playlist entry by country: {}", country);

        Week lastestWeek = weekRepository.findFirstByOrderByEndDateDesc();
        Playlist playlistEntry = repository.findFirstByWeekAndTypeAndCountry(lastestWeek, PlaylistType.TOP.getPlaylistType(), country);
        LOGGER.info("Found playlist entry: {}", playlistEntry);

        return playlistEntry;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getTop3UserPlaylists(String username) {
        LOGGER.info("Finding Top 3 playlist entries by username: {}", username);

        List<Playlist> playlistEntries = repository.findTop3ByCreatedByUserOrderByIdDesc(username);
        LOGGER.info("Found {} playlist entries", playlistEntries.size());

        return playlistEntries;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getAllUserPlaylists(String username) {
        LOGGER.info("Finding ALL playlist entries by username: {}", username);

        List<Playlist> playlistEntries = repository.findByCreatedByUserOrderByIdDesc(username);
        List<Playlist> playlistEntries2 = repository.findByUsersUsername(username);

        List<Playlist> playlistFinal = ListUtils.union(playlistEntries, playlistEntries2);
        LOGGER.info("Found {} playlist entries", playlistFinal.size());

        return playlistFinal;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Playlist> getHomePlaylists() {
        LOGGER.info("Finding home playlist entries");

        List<Boolean> onHomes = new ArrayList<Boolean>();
        onHomes.add(true);

        List<String> types = new ArrayList<String>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        PageRequest request = new PageRequest(0, PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Playlist> playlistEntries = repository.findByOnHomeInAndTypeIn(onHomes, types, request);
        LOGGER.info("Found {} playlist entries", playlistEntries.getContent().size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Playlist> getAllOfficialAndCollectionPlaylists(int page) {
        LOGGER.info("Finding all playlists entries page {}", page);

        List<String> types = new ArrayList<String>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Playlist> playlistEntries = repository.findByTypeIn(types, request);
        LOGGER.info("Found {} playlist entries", playlistEntries.getContent().size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Playlist> getPlaylistByGenreName(String genreName, int page) {
        LOGGER.info("Finding all official and collection playlists entries page {}", page);

        List<String> types = new ArrayList<String>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.DESC, "id");

        Page<Playlist> playlistEntries = repository.findByTypeInAndGenreName(types, genreName, request);
        LOGGER.info("Found {} playlist entries", playlistEntries.getContent().size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getRelatedPlaylists(Artist artist) {
        LOGGER.info("Finding Top 4 related playlist entries of :{}", artist);

        List<String> types = new ArrayList<String>();
        types.add(PlaylistType.OFFICIAL.getPlaylistType());
        types.add(PlaylistType.COLLECTION.getPlaylistType());

        List<Playlist> playlistEntries = repository.findTop4ByTypeInAndArtistOrderByIdDesc(types, artist);
        LOGGER.info("Found {} playlist entries", playlistEntries.size());

        return playlistEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Playlist> getRecommendedPlaylists(String recentPlaylists) {
        LOGGER.info("Finding Top 3 Recommended playlist entries");

        List<Playlist> returned = new ArrayList<Playlist>();

        //Get Playlist From User Cookie
        if (!recentPlaylists.equals("")) {
            //recentPlaylists pattern: {id}:{times}\n ...
            for (String retval : recentPlaylists.split("\n", 3)) {
                LOGGER.debug("PLAYLIST HISTORY: ", retval);
            }
        }

        int no = returned.size();

        //Make sure you enough get 3 playlists
        if (no < 3) {
            List<String> types = new ArrayList<String>();
            types.add(PlaylistType.OFFICIAL.getPlaylistType());
            types.add(PlaylistType.COLLECTION.getPlaylistType());

            List<Playlist> weekTopPls = repository.findTop3ByTypeInOrderByWeekViewsDesc(types);
            for (int i = 0; i < 3 - no; i++) {
                returned.add(weekTopPls.get(i));
            }
        }

        LOGGER.info("Found {} playlist entries", returned.size());
        return returned;
    }

    @Override
    @Transactional
    public Playlist getById(Integer id) {
        LOGGER.info("Finding playlist entry by using id: {}", id);

        Playlist playlistEntry = findPlaylistEntryById(id);
        LOGGER.info("Found playlist entry: {}", playlistEntry);

        LOGGER.info("Increments Views of playlist entrie: {}", playlistEntry);

        playlistEntry.incrementViews();
        repository.flush();

        return playlistEntry;
    }

}
