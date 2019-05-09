/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.jmunoz.sampleweb.util;

/**
 *
 * @author sala312
 */
public enum AlertTypes {

    SUCCESS("Success"), ERROR("Error", "danger"), WARNING("Warning");

    private final String title;
    private final String code;

    private AlertTypes(String title) {
        this(title, null);

    }

    private AlertTypes(String title, String code) {
        this.title = title;
        this.code = code != null ? code : this.name().toLowerCase();
    }

    public String getTitle() {
        return this.title;
    }

    public String getCode() {
        return code;
    }

}
