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
public class UserDAOImpl implements UserDAO{

    @Override
    public List<User> getUsers() {
        return UserData.getUserList();
    }

    @Override
    public User getUser(String id) {
        
        User lookingUser = new User(id);
        List<User> users = this.getUsers();
        User foundUser = users.indexOf(lookingUser);
        
        
        
        ;
        for (User user : users) {
            if (user.getId().equals(id)) {
                foundUser = user;
                break;
            }
        }
        
        return foundUser;
    }

    @Override
    public User saveUser(User user) {
        
        User userInlist = this.getUser(user.getId());
        
        if (userInlist == null) {
            this.getUsers().add(user);
            userInlist = user;
        }
        
        return userInlist;
    }

    @Override
    public void deleteUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
