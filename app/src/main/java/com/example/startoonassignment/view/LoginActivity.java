package com.example.startoonassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.example.startoonassignment.R;
import com.example.startoonassignment.databinding.ActivityLoginBinding;
import com.example.startoonassignment.model.User;
import com.example.startoonassignment.viewmodel.UserViewModel;
import com.example.startoonassignment.viewmodel.factory.UserViewModelFactory;

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /**
         * Create instance for data binding auto generated class file
         */

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        /**
         * Create instance for ViewModel class
         */

        UserViewModel userViewModel = ViewModelProviders.of(this, new UserViewModelFactory(this, new User())).get(UserViewModel.class);

        /**
         * Set ViewModel instance to binding class
         */
        binding.setUserViewModel(userViewModel);
        binding.setLifecycleOwner(this);
    }
}