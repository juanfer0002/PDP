/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.sampleweb.dao.impl;

import co.com.jmunoz.sampleweb.dao.UserDAO;
import co.com.jmunoz.sampleweb.data.UserData;
import co.com.jmunoz.sampleweb.model.User;
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

    public static void main(String[] args) {
        UserDAOImpl dao = new UserDAOImpl();
        System.out.println(dao.getUsers().size());
        System.out.println(dao.getUser("1"));
        System.out.println(dao.saveUser(new User("4")));
        System.out.println(dao.getUsers().size());
        dao.deleteUser("4");
        System.out.println(dao.getUsers().size());
    }

}
