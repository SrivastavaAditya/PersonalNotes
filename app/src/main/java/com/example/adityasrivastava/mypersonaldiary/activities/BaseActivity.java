package com.example.adityasrivastava.mypersonaldiary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.dialogs.LogoutDialog;
import com.example.adityasrivastava.mypersonaldiary.fragments.ChangePasswordFragment;

/**
 * The type Base activity.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.base_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_logout:
                LogoutDialog.getInstance(this);
                break;

            case R.id.menu_change_password:
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new ChangePasswordFragment()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}