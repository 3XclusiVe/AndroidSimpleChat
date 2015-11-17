package com.example.user.androidsimplechat.presentation;

import android.os.AsyncTask;
import com.example.user.androidsimplechat.presentation.ILoadable;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.infrastructure.Client;
import com.example.user.androidsimplechat.infrastructure.IChatServerResponcesObserver;

import java.io.IOException;

/**
 * Created by user on 14.11.15.
 */
public class Connection extends AsyncTask<String, Void, Void>
{
    private ILoadable activity;

    public Connection(ILoadable activity)
    {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute()
    {
        activity.startLoad();
    }


    @Override
    protected Void doInBackground(String... params)
    {
        Client client = ServerClient.instance;
        if (params.length == 2) {
            String login = params[0];
            String password = params[1];

            try {
                if (client != null) {
                    client.disconnect();
                }

                ServerClient.instance = new Client(login, password, (IChatServerResponcesObserver) activity);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            String login = params[0];
            String password = params[1];
            String nick = params[2];

            try {
                if (client != null) {
                    client.disconnect();
                }

                ServerClient.instance = new Client(login, password, nick, (IChatServerResponcesObserver) activity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void par)
    {
        //activity.finishLoading();
    }
}
