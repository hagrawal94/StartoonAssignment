package com.example.startoonassignment.viewmodel;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.startoonassignment.model.User;
import com.example.startoonassignment.view.HomeActivity;
import com.example.startoonassignment.view.LoginActivity;
import com.example.startoonassignment.view.SignupActivity;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel
{
    public MutableLiveData<String> phn_signup = new MutableLiveData<>();
    public MutableLiveData<String> otp_signup = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Integer> busy;

    private User user;
    private Context context;


    /**
     * Constructor for UserViewModel
     * @param context
     * @param user
     */
    public UserViewModel(Context context, User user)
    {
        this.user = user;
        this.context = context;
    }


    /**
     * Get Mutable Data instance for progress bar
     * @return
     */
    public MutableLiveData<Integer> getBusy() {

        if (busy == null)
        {
            busy = new MutableLiveData<>();
            busy.setValue(View.GONE);
        }

        return busy;
    }


    /**
     * Event generated for login button
     * @return
     */


    public void onSendOTPSignUpClick() {
        user.setPhn(phn_signup.getValue());
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());
        user.setUsername(username.getValue());
        if(!user.isValidPhn())
        {
            Toast.makeText(context, "Invalid Phone number", Toast.LENGTH_SHORT).show();
        }
        if(!user.isValidPassword())
        {
            Toast.makeText(context, "Password must be greater than 5 in length", Toast.LENGTH_SHORT).show();
        }
        else{
            ArrayList<AuthUserAttribute> attributes = new ArrayList<>();
            attributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), user.getEmail()));
            attributes.add(new AuthUserAttribute(AuthUserAttributeKey.phoneNumber(), user.getPhn()));

            try{Amplify.addPlugin(new AWSCognitoAuthPlugin());}catch (Exception e){
                //Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }

            try{Amplify.configure(context);}catch (Exception e){
                //Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }

            Amplify.Auth.signUp(
                    user.getPhn(),
                    user.getPassword(),
                    AuthSignUpOptions.builder().userAttributes(attributes).build(),
                    result -> Log.i("TAG", result.toString()),
                    error -> Log.e("TAG", error.toString())
            );
            Toast.makeText(context, "OTP sent", Toast.LENGTH_SHORT).show();
        }


    }

    public void onSignUpClick(){
        user.setPhn(phn_signup.getValue());
        user.setOtp(otp_signup.getValue());
        Amplify.Auth.confirmSignUp(
                user.getPhn(),
                user.getOtp(),
                result -> {if(result.isSignUpComplete()){
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
                else Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();},
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }

    public void onMoveToSignUpClick(){
        Intent intent = new Intent(context, SignupActivity.class);
        context.startActivity(intent);
    }

    public void onLoginClick() {
        Toast.makeText(context, "Validating Credentials...", Toast.LENGTH_SHORT).show();
        user.setUsername(username.getValue());
        user.setPassword(password.getValue());

        try{
            Amplify.addPlugin(new AWSCognitoAuthPlugin());}catch (Exception e){
            //Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

        try{Amplify.configure(context);}catch (Exception e){
            //Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

        Amplify.Auth.signIn(
                user.getUsername(),
                user.getPassword(),
                result -> {if(result.isSignInComplete()){
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
                else Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();},
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
}