package com.example.user.androidsimplechat.frames;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.example.user.androidsimplechat.R;

/**
 * Created by user on 07.11.15.
 */
public class Preferences extends PreferenceFragment
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}