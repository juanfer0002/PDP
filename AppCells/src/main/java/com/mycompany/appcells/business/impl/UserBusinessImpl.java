/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.business.impl;

import com.mycompany.appcells.business.UserBusiness;
import com.mycompany.appcells.dao.impl.UserDAOImpl;
import com.mycompany.appcells.model.User;
import com.mycompany.appcells.util.RoleEnum;
import java.util.List;

/**
 *
 * @author giocode
 */
public class UserBusinessImpl implements UserBusiness {

    UserDAOImpl userDao = new UserDAOImpl();

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(String documento) {
        return userDao.getUser(documento);
    }

    @Override
    public User saveUser(User usuario) {
        if (!userExists(usuario)) {
            return userDao.saveUser(usuario);
        }
        return null;
    }

    @Override
    public void deleteUser(String documento) {
        userDao.deleteUser(documento);

    }

    @Override
    public boolean authUser(String username, String password) {
        List<User> users = this.getUsers();

        User foundUser = null;
        for (int i = 0; i < users.size() && foundUser == null; i++) {
            User user = users.get(i);
            foundUser = user.getEmail().equalsIgnoreCase(username) ? user : null;
        }

        return foundUser != null && foundUser.getPassword().equals(password)
                && foundUser.getRole() == RoleEnum.ADMIN;
    }

    private boolean userExists(User user) {
        return this.getUsers().contains(user);
    }

}
