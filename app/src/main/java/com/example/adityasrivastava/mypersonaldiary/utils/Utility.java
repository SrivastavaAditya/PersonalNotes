package com.example.adityasrivastava.mypersonaldiary.utils;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.models.UserCredentials;

import java.util.logging.Handler;

/**
 * The type Utility.
 */
public class Utility {
    /**
     * Update loading text string.
     *
     * @param context the context
     * @param dots    the dots
     * @return the string
     */
    public static String updateLoadingText(Context context, String dots){
        return context.getResources().getString(R.string.loading_please_wait) + dots;
    }

    /**
     * Show message.
     *
     * @param context the context
     * @param message the message
     */
    public static void showMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
