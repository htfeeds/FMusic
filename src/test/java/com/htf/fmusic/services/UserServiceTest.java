package com.htf.fmusic.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.htf.fmusic.models.User;
import com.htf.fmusic.repositories.PlaylistRepository;
import com.htf.fmusic.repositories.UserRepository;
import com.nitorcreations.junit.runners.NestedRunner;

/**
 * @author HTFeeds
 */
@RunWith(NestedRunner.class)
public class UserServiceTest {

    private UserRepository userRepository;
    private PlaylistRepository playlistRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        playlistRepository = mock(PlaylistRepository.class);
        userService = new UserServiceImpl(userRepository, playlistRepository);
    }

    public class Update {
        public class WhenUserEntryIsFound {
            private User created;

            @Before
            public void returnUpdatedUserEntry() {
                created = new User();
                created.setId(10);
                created.setFullname("Created_Fullname");
                created.setUsername("created_username");
                created.setEmail("created_email@yahoo.com");
                created.setPassword("created_password");

                given(userRepository.findOne(10)).willReturn(Optional.of(created));
            }

            @Test
            public void shouldUpdateFullname() {
                User updated = new User();
                updated.setId(10);
                updated.setFullname("Updated_Fullname");
                updated.setEmail("updated_email@yahoo.com");

                userService.update(updated);

                //update
                assertTrue(created.getFullname().equals("Updated_Fullname"));
                assertTrue(created.getEmail().equals("updated_email@yahoo.com"));
                //no update
                assertTrue(created.getUsername().equals("created_username"));
                assertTrue(created.getPassword().equals("created_password"));
            }
        }
    }
}
