package com.example.adityasrivastava.mypersonaldiary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.dbHelper.SQLiteHelper;
import com.example.adityasrivastava.mypersonaldiary.events.LogoutEvent;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The type Home activity.
 */
public class HomeActivity extends BaseActivity {

    /**
     * The Unbinder.
     */
    Unbinder unbinder;
    /**
     * The Preference storage.
     */
    SharedPreferenceStorage preferenceStorage;

    /**
     * The Tv title.
     */
    @BindView(R.id.text_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
        preferenceStorage = new SharedPreferenceStorage(this);
        preferenceStorage.initializePreferences();

        tvTitle.setText(getResources().getString(R.string.welcome_with_name,
                SQLiteHelper.getInstance(this).getLoginUser().userName));
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

    /**
     * On logout event.
     *
     * @param logoutEvent the logout event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutEvent(LogoutEvent logoutEvent){
        if(preferenceStorage.getLoginPreference()){
            preferenceStorage.setIsLoggedInPreference(false);
            SQLiteHelper.getInstance(this).delete(SQLiteHelper.getInstance(this).getLoginUser());
            Intent intent = new Intent(HomeActivity.this,
                    LoginOrRegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}