package com.example.adityasrivastava.mypersonaldiary.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.events.LogoutEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The type Logout dialog.
 */
public class LogoutDialog implements View.OnClickListener {

    /**
     * The Unbinder.
     */
    Unbinder unbinder;
    /**
     * The Dialog.
     */
    Dialog dialog;

    /**
     * The Btn close.
     */
    @BindView(R.id.btn_close)
    TextView btnClose;

    /**
     * The Btn logout.
     */
    @BindView(R.id.button_logout)
    Button btnLogout;

    /**
     * The Btn no.
     */
    @BindView(R.id.button_no)
    Button btnNo;

    /**
     * Get instance logout dialog.
     *
     * @param context the context
     * @return the logout dialog
     */
    public static LogoutDialog getInstance(Context context){
        return new LogoutDialog(context);
    }

    /**
     * Instantiates a new Logout dialog.
     */
    public LogoutDialog() {
    }

    /**
     * Instantiates a new Logout dialog.
     *
     * @param context the context
     */
    public LogoutDialog(Context context) {
        dialog = new Dialog(context);

        View rootView = ((Activity)context).getLayoutInflater().inflate(R.layout.logout_dialog, null);
        dialog.setContentView(rootView);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        unbinder = ButterKnife.bind(this, rootView);
        btnClose.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_close:
                dialog.dismiss();
                break;

            case R.id.button_logout:
                //To-Do
                dialog.dismiss();
                EventBus.getDefault().postSticky(new LogoutEvent());
                break;

            case R.id.button_no:
                dialog.dismiss();
                break;

            default:
                break;
        }
    }
}