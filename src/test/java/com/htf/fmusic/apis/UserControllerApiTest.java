package com.htf.fmusic.apis;

import org.springframework.web.client.RestTemplate;

import com.htf.fmusic.dtos.Credentials;

/**
 * @author HTFeeds
 */
public class UserControllerApiTest {
    public static final String REST_USER_URI = "https://fmusic.herokuapp.com/rest/user/";

    private static void getUserById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ClientUser u = restTemplate.getForObject(REST_USER_URI + id, ClientUser.class);
        System.out.println(u);
    }

    public static void getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ClientUser[] users = restTemplate.getForObject(REST_USER_URI + "list", ClientUser[].class);
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }
    }

    public static void postRegister(ClientUser user) {
        RestTemplate restTemplate = new RestTemplate();
        ClientUser created = restTemplate.postForObject(REST_USER_URI + "register", user, ClientUser.class);
        System.out.println(created);
    }

    public static void postLogin(Credentials credentials) {
        RestTemplate restTemplate = new RestTemplate();
        boolean b = restTemplate.postForObject(REST_USER_URI + "login", credentials, Boolean.class);
        System.out.println(b);
    }

    public static void main(String[] args) {
        System.out.println("Get user with id = 1");
        getUserById(1);

        System.out.println("Get all users");
        getAllUsers();

        ClientUser user = new ClientUser();
        user.setFullname("Tester003");
        user.setUsername("tester003");
        user.setEmail("tester003@yahoo.com");
        user.setPassword("123456");
        System.out.println("Register with user: " + user);
        postRegister(user);

        Credentials credentials = new Credentials();
        credentials.setUsername("tester001");
        credentials.setPassword("123456");
        System.out.println("Login with username: " + credentials.getUsername());
        postLogin(credentials);

    }
}
