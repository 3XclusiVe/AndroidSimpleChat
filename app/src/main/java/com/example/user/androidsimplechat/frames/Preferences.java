package com.example.user.androidsimplechat.frames;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;
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
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.settings_menu, menu);
        getActivity().setTitle("Settings");
    }

}