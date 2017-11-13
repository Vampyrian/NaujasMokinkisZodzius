package com.pv.vampyrian.mokinkiszodzius.ui.signin;

public class User {

    private String mEmail;
    private String mPassword;

    User (String email, String password) {
        mEmail = email;
        mPassword = password;
    }



    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
