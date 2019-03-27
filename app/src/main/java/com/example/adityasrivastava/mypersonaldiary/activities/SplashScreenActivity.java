package com.example.adityasrivastava.mypersonaldiary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.utils.Utility;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The type Splash screen activity.
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * The Unbinder.
     */
    Unbinder unbinder;
    /**
     * The Preference storage.
     */
    SharedPreferenceStorage preferenceStorage;

    /**
     * The Tv loader.
     */
    @BindView(R.id.text_loader)
    TextView tvLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        unbinder = ButterKnife.bind(this);

        preferenceStorage = new SharedPreferenceStorage(this);
        preferenceStorage.initializePreferences();

        for(int i=0; i<5; i++){
            int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch(finalI){
                        case 0:
                            tvLoader.setText(Utility.updateLoadingText(SplashScreenActivity.this, "."));
                            break;

                        case 1:
                            tvLoader.setText(Utility.updateLoadingText(SplashScreenActivity.this, ".."));
                            break;

                        case 2:
                            tvLoader.setText(Utility.updateLoadingText(SplashScreenActivity.this, "..."));
                            break;

                        case 3:
                            tvLoader.setText(Utility.updateLoadingText(SplashScreenActivity.this, ""));
                            break;

                        default:
                            if(preferenceStorage.getRegisterPreference()){
                                if(preferenceStorage.getLoginPreference()){
                                    Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Intent intent = new Intent(SplashScreenActivity.this, LoginOrRegisterActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }else{
                                Intent intent = new Intent(SplashScreenActivity.this, LoginOrRegisterActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            break;
                    }
                }
            }, 1000);
        }
    }
}