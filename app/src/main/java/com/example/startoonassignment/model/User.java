package com.example.startoonassignment.model;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.Serializable;

public class User implements Serializable
{

    private String phn, email, password, username;
    private String otp;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhn()
    {
        return this.phn;
    }

    public void setPhn(String phn)
    {
        this.phn = phn;
    }

    public String getOtp()
    {
        return this.otp;
    }

    public void setOtp(String otp)
    {
        this.otp = otp;
    }


    public boolean isValidPhn()
    {
        if(this.phn != null && !TextUtils.isEmpty(phn) && Patterns.PHONE.matcher(phn).matches())
        {
            return true;
        }

        return false;
    }

    public boolean isValidPassword()
    {
        if(this.password != null && this.password.length() >= 6)
        {
            return true;
        }

        return false;
    }
}