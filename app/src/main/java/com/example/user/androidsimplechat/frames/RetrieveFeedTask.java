package com.example.user.androidsimplechat.frames;

import android.os.AsyncTask;
import com.example.user.androidsimplechat.IFramable;
import com.example.user.androidsimplechat.MainActivity;
import com.example.user.androidsimplechat.model.Client;

import java.io.IOException;

/**
 * Created by user on 14.11.15.
 */
public class RetrieveFeedTask extends AsyncTask<String, Void, Void>
{
    private IFramable activity;

    public RetrieveFeedTask(IFramable activity)
    {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(String... params)
    {
        String login = "mmmalkin007@mail.r";
        String password = "12345";
        String nick = "QW12";

        try {
            activity.setClient(new Client(login, password, nick));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void par)
    {
        activity.onAuth();
    }
}
