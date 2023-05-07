package com.ca.security.roles.demo78.utils;

public class LoginField {
    private String username1;
    private String encrypted_password;

    public LoginField(String username1, String encrypted_password) {
        this.username1 = username1;
        this.encrypted_password = encrypted_password;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username) {
        this.username1 = username;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }
}
