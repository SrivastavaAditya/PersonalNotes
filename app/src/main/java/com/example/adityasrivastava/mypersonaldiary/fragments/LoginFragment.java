package com.example.adityasrivastava.mypersonaldiary.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

public class LoginFragment extends Fragment {

    SharedPreferenceStorage sharedPreferenceStorage;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferenceStorage = new SharedPreferenceStorage(getContext());
        sharedPreferenceStorage.initializePreferences();
        if(!sharedPreferenceStorage.getLoginPreference()){
            sharedPreferenceStorage.setIsLoggedInPreference(true);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}