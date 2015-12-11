package com.example.user.androidsimplechat.presentation;

import android.app.Fragment;

/**
 * Created by user on 14.11.15.
 */
public interface ILoadable
{

    void finishLoading();

    void startLoad();

    void loadFrame(Fragment fragment);

    void setAuthorizationInfo(String login, String password);

}
