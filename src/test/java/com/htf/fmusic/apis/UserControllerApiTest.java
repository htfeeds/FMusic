package com.htf.fmusic.apis;

import org.springframework.web.client.RestTemplate;

import com.htf.fmusic.models.User;

/**
 * @author HTFeeds
 */
public class UserControllerApiTest {
    public static final String REST_USER_URI = "http://localhost:8088/rest/user/";

    private static void getUserById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        User u = restTemplate.getForObject(REST_USER_URI + id, User.class);
        System.out.println(u);
    }

    public static void main(String[] args) {
        getUserById(1);
    }
}
