/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.dao.impl;

import com.mycompany.appcells.dao.UserDAO;
import com.mycompany.appcells.data.UserData;
import com.mycompany.appcells.model.User;
import java.util.List;

/**
 *
 * @author sala312
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getUsers() {
        return UserData.getUserList();
    }

    @Override
    public User getUser(String id) {
        User lookingUser = new User(id);
        List<User> users = this.getUsers();
        int index = users.indexOf(lookingUser);
        return index > -1 ? users.get(index) : null;
    }

    @Override
    public User saveUser(User user) {
        User userInlist = this.getUser(user.getId());

        if (userInlist == null) {
            this.getUsers().add(user);
            userInlist = user;
        } else {
            // Update user
        }

        return userInlist;
    }

    @Override
    public void deleteUser(String id) {
        User userInlist = this.getUser(id);

        if (userInlist != null) {
            this.getUsers().remove(userInlist);
        }
    }

}
