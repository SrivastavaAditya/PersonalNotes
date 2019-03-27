package com.example.adityasrivastava.mypersonaldiary.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.events.LogoutEvent;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * The type Home activity.
 */
public class HomeActivity extends BaseActivity {

    SharedPreferenceStorage preferenceStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferenceStorage = new SharedPreferenceStorage(this);
        preferenceStorage.initializePreferences();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutEvent(LogoutEvent logoutEvent){
        if(preferenceStorage.getLoginPreference()){
            preferenceStorage.setIsLoggedInPreference(false);
            Intent intent = new Intent(HomeActivity.this, LoginOrRegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
