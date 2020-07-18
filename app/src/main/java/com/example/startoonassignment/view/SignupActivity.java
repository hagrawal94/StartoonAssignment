package com.example.startoonassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.startoonassignment.R;
import com.example.startoonassignment.databinding.ActivityLoginBinding;
import com.example.startoonassignment.databinding.ActivitySignupBinding;
import com.example.startoonassignment.model.User;
import com.example.startoonassignment.viewmodel.UserViewModel;
import com.example.startoonassignment.viewmodel.factory.UserViewModelFactory;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActivitySignupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

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