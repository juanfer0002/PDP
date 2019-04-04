/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.sampleweb.data;

import co.com.jmunoz.sampleweb.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sala312
 */
public class UserData {

    private static final List<User> USERS;

    static {
        USERS = new ArrayList<>();
        USERS.add(new User("1", "pepe", "lopez", "pepe@gmail.com", "123", true));
        USERS.add(new User("2", "jose", "cabrera", "jose@gmail.com", "123", true));
        USERS.add(new User("3", "arturo", "cáceres", "arturo@gmail.com", "123", true));
    }
    
    public static List<User> getUserList() {
        return USERS;
    }

}
