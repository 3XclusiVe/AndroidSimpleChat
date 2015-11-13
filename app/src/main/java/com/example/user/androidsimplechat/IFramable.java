package com.example.user.androidsimplechat;


import android.app.Fragment;
import com.example.user.androidsimplechat.model.Client;

/**
 * Created by user on 07.11.15.
 */
public interface IFramable
{
    public void loadFrame(Fragment fragmentToLoad);

    public Client getClient();

    public void setClient(Client client);

    public void onAuth();
}
