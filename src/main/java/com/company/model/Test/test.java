package com.company.model.Test;

import com.company.model.dao.UserDAO;
import com.company.model.entity.user.Role;
import com.company.model.entity.user.User;
import com.company.model.entity.user.UserDetails;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        User user = new User();
        UserDetails details = new UserDetails();
        user.setId(1);
        user.setLogin("fgfgdggd");
        user.setPassword("dfsdfdsfsf");
        user.setRole(Role.ADMIN);
        user.setDetails(details);
        details.setFirstnameEn("jhghjhj");
        details.setLastnameEn("ghjhvghvh");
        details.setFirstnameUa("ddgdfgddfg");
        details.setLastnameUa("dcsdfdfsdf");
        details.setEmail("jjhbhvvhvh");
        details.setPhone("9809855734");
        UserDAO dao = new UserDAO();
        dao.insertUser(user);
        dao.getUserDetails(1);
        System.out.println(dao.getUser("fgfgdggd").getLogin());
    }
}
