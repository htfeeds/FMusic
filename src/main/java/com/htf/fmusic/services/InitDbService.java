package com.htf.fmusic.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htf.fmusic.enums.RoleType;
import com.htf.fmusic.models.Role;
import com.htf.fmusic.models.User;

/**
 * @author HTFeeds
 */
@Transactional
@Service
public class InitDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDbService.class);

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    InitDbService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

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
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("07/01/1991");
            user.setBirthDate(date);
            user.setPhoneNumber("0932569525");
            user.setEmail("htf52gl@outlook.com");
            user.setImageUrl("/static/img/user/htfeeds.jpg");
            user.getRoles().add(adminRole);
            user.getRoles().add(dbaRole);

            userService.create(user);

            for (int i = 0; i < 10; i++) {
                User u = new User();
                u.setUsername("fortest" + i);
                u.setPassword("124356" + i);
                u.setFullname("For Test " + i);
                u.setState("Inactive");
                u.setEmail("fortest" + i + "@outlook.com");

                userService.create(u);
            }

        }
    }
}
