/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.sampleweb.business.impl;

import co.com.jmunoz.sampleweb.business.UserBusiness;
import co.com.jmunoz.sampleweb.dao.impl.UserDAOImpl;
import co.com.jmunoz.sampleweb.model.User;
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

    // TODO: ver
    public String actualizarUser(User usuario) {
        if (userExists(usuario)) {
            userDao.saveUser(usuario);
            return "User actualizado con exito";
        }
        return "User no existe";
    }

    public boolean userExists(User user) {
        return this.getUsers().contains(user);
    }

}
