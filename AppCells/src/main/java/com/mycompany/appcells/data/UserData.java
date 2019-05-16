/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appcells.data;

import com.mycompany.appcells.model.User;
import com.mycompany.appcells.util.RoleEnum;
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
        USERS.add(new User("1", "pepe@gmail.com", "123", true, RoleEnum.ADMIN));
        USERS.add(new User("2", "jose@gmail.com", "123", true, RoleEnum.NON_ADMIN));
        USERS.add(new User("3", "arturo@gmail.com", "123", true, RoleEnum.NON_ADMIN));
    }

    public static List<User> getUserList() {
        return USERS;
    }

}
