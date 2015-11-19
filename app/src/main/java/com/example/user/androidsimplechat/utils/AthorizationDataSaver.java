package com.example.user.androidsimplechat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 19.11.15.
 */
public class AthorizationDataSaver
{
    private static String loginKey = "login";
    private static String passwordKey = "password";

    public AthorizationDataSaver(Context context, String login, String password)
    {

        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putString(loginKey, login);
        editor.putString(passwordKey, password);
        editor.commit();

    }

    public static String getSavedLogin(Context context)
    {

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String login = sharedPreferences.getString(loginKey, "");

        return login;
    }

    public static String getSavedPassword(Context context)
    {

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String password = sharedPreferences.getString(passwordKey, "");

        return password;
    }

    public static void Clear(Context context)
    {
        SharedPreferences.Editor sharedPreferences = getPrefsEditor(context);
        sharedPreferences.remove(loginKey);
        sharedPreferences.remove(passwordKey);
        sharedPreferences.commit();
    }

    private static SharedPreferences.Editor getPrefsEditor(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences;
    }
}
