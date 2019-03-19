package com.example.adityasrivastava.mypersonaldiary.utils;

import android.content.Context;

import com.example.adityasrivastava.mypersonaldiary.R;

public class Utility {

    public static String updateLoadingText(Context context, String dots){
        return context.getResources().getString(R.string.loading_please_wait) + dots;
    }


}
