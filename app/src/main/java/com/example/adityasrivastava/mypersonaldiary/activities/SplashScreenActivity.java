package com.example.adityasrivastava.mypersonaldiary.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agrawalsuneet.loaderspack.loaders.SearchLoader;
import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.utils.Utility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SplashScreenActivity extends AppCompatActivity {

    Unbinder unbinder;

    @BindView(R.id.text_loader)
    TextView tvLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        unbinder = ButterKnife.bind(this);

//        SearchLoader searchLoader = new SearchLoader(this,
//                60, 20, 80,
//                ContextCompat.getColor(this, R.color.colorAccent),
//                500, 500, true);
//
//        container.addView(searchLoader);
    }
}