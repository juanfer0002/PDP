/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.sampleweb.dao;

import co.com.jmunoz.sampleweb.model.User;
import java.util.List;

/**
 *
 * @author sala312
 */
public interface UserDAO {
    
    List<User> getUsers();
    User getUser(String id);
    User saveUser(User user);
    void deleteUser(String id);
    

}
