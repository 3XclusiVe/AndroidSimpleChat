package com.example.user.androidsimplechat.frames;

import android.app.Fragment;
import android.view.*;
import com.example.user.androidsimplechat.IFramable;
import android.os.Bundle;
import com.example.user.androidsimplechat.R;

/**
 * Created by user on 07.11.15.
 */
public abstract class FrameAttachedToMainActivity extends Fragment
{
    protected IFramable mainActivity;
    private int mActionBar;
    private String mActionBarTitle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mainActivity == null) {
            mainActivity = (IFramable) getActivity();
        }

        mActionBar = getActionBar();
        mActionBarTitle = getActionBarTitle();

        setHasOptionsMenu(true);

        return null;
    }

    protected abstract String getActionBarTitle();

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(mActionBar, menu);
        getActivity().setTitle(mActionBarTitle);
    }

    protected int getActionBar()
    {
        return R.menu.fragment_menu;
    }
}
