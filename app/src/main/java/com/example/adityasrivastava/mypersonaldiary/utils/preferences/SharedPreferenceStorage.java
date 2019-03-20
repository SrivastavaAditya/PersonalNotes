package com.example.adityasrivastava.mypersonaldiary.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * The type Shared preference storage.
 */
public class SharedPreferenceStorage{

    private Context context;
    private SharedPreferences isLoggedInPreference;
    private SharedPreferences.Editor isLoggedIn;

    private SharedPreferences isRegisteredPreference;
    private SharedPreferences.Editor isRegistered;

    private String IS_REGISTERED_PREFERENCE = "IsRegisteredPreference";
    private String IS_REGISTERED_EDITOR = "IsRegisteredEditor";

    private String IS_LOGGED_IN_PREFERENCE = "IsLoggedInPreference";
    private String IS_LOGGED_IN_EDITOR = "IsLoggedInEditor";

    /**
     * Instantiates a new Shared preference storage.
     *
     * @param context the context
     */
    public SharedPreferenceStorage(Context context){
        this.context = context;
    }

    /**
     * Initialize preferences.
     */
    public void initializePreferences(){
        //Initializing Registration Preferences
        isRegisteredPreference = context.getSharedPreferences(IS_REGISTERED_PREFERENCE, Context.MODE_PRIVATE);
        isRegistered = isRegisteredPreference.edit();

        //Initializing Login Preferences
        isLoggedInPreference = context.getSharedPreferences(IS_LOGGED_IN_PREFERENCE, Context.MODE_PRIVATE);
        isLoggedIn = isLoggedInPreference.edit();
    }

    /**
     * Set register preference.
     *
     * @param b the b
     */
    public void setRegisterPreference(boolean b){
        isRegistered.putBoolean(IS_REGISTERED_EDITOR, b);
        isRegistered.commit();
    }

    /**
     * Set is logged in preference.
     *
     * @param b the b
     */
    public void setIsLoggedInPreference(boolean b){
        isLoggedIn.putBoolean(IS_LOGGED_IN_EDITOR, b);
        isLoggedIn.commit();
    }

    /**
     * Get register preference boolean.
     *
     * @return the boolean
     */
    public boolean getRegisterPreference(){
        return isRegisteredPreference.getBoolean(IS_REGISTERED_EDITOR, false);
    }

    /**
     * Get login preference boolean.
     *
     * @return the boolean
     */
    public boolean getLoginPreference(){
        return isLoggedInPreference.getBoolean(IS_LOGGED_IN_EDITOR, false);
    }
}