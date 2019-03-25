package com.example.adityasrivastava.mypersonaldiary.dialogs;

import android.app.Dialog;
import android.content.Context;

import com.example.adityasrivastava.mypersonaldiary.R;

import butterknife.Unbinder;

public class LogoutDialog {

    Unbinder unbinder;
    Dialog dialog;

    public static LogoutDialog getInstance(Context context){
        return new LogoutDialog(context);
    }

    public LogoutDialog() {
    }

    public LogoutDialog(Context context) {
        dialog = new Dialog(context);

        dialog.setContentView(R.layout.exit_dialog);
    }
}
