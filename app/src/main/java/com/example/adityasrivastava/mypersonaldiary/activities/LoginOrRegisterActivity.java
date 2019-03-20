package com.example.adityasrivastava.mypersonaldiary.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.os.Bundle;
import android.view.View;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.fragments.LoginFragment;
import com.example.adityasrivastava.mypersonaldiary.fragments.RegisterFragment;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

/**
 * The type Login or register activity.
 */
public class LoginOrRegisterActivity extends AppCompatActivity {

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The Preference storage.
     */
    SharedPreferenceStorage preferenceStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);

        preferenceStorage = new SharedPreferenceStorage(this);
        preferenceStorage.initializePreferences();
        if(preferenceStorage.getRegisterPreference()){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new LoginFragment()).commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new RegisterFragment()).commit();
        }
    }
}
