package com.htf.fmusic.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.htf.fmusic.models.Artist;
import com.htf.fmusic.models.Playlist;
import com.htf.fmusic.models.Week;

/**
 * @author HTFeeds
 */
public interface PlaylistRepository extends BaseRepository<Playlist, Integer> {

    public List<Playlist> findByNameContainsIgnoreCase(String name);

    public List<Playlist> findTop5BySlideActivedOrderByIdDesc(Boolean actived);

    public Page<Playlist> findByOnHomeInAndTypeIn(Collection<Boolean> onHomes, Collection<String> types, Pageable pageable);

    public Page<Playlist> findByTypeInAndGenreName(Collection<String> types, String genreName, Pageable pageable);

    public Page<Playlist> findByTypeIn(Collection<String> types, Pageable pageable);

    public Playlist findFirstByWeekAndTypeAndCountry(Week week, String type, String country);

    public List<Playlist> findByCreatedByUserOrderByIdDesc(String username);

    public List<Playlist> findTop3ByCreatedByUserOrderByIdDesc(String username);

    public List<Playlist> findTop4ByTypeInAndArtistOrderByIdDesc(Collection<String> types, Artist artist);

    public List<Playlist> findTop3ByTypeInOrderByWeekViewsDesc(Collection<String> types);

    public List<Playlist> findByUsersUsername(String username);

}
