package com.example.user.androidsimplechat;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.example.user.androidsimplechat.frames.SignFrame;
import com.example.user.androidsimplechat.model.*;

import java.util.List;

public class SplashScreen extends Activity implements ILoadable, IChatServerResponcesObserver
{
    private static Fragment currentFragment = null;
    private static Class<?> nextActivityToLoad = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (nextActivityToLoad == null) {
            nextActivityToLoad = MainActivity.class;
        }
        loadFrame(new SignFrame());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return false;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void loadNextActivity()
    {
        Intent intent = new Intent(this, nextActivityToLoad);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        ServerClient.instance.unsubscribe(this);
        this.finish();
    }

    @Override
    public void onSuccessToConnect()
    {
        loadNextActivity();
    }

    @Override
    public void onFailToCoonnect()
    {
        loadFrame(new SignFrame());
    }


    private void loadFrame(Fragment fragmentToLoad)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragmentToLoad);
        if (currentFragment != null) {
            ft.addToBackStack(null);
        }
        ft.commit();
        this.invalidateOptionsMenu();
        currentFragment = fragmentToLoad;

    }

    @Override
    public void onRegister(String status)
    {

    }

    @Override
    public void onAuthorization(String status)
    {
        if (status.equals("OK")) {
            onSuccessToConnect();
        } else {
            onFailToCoonnect();
        }
    }

    @Override
    public void onChannelList(List<ChatRoom> rooms)
    {

    }

    @Override
    public void onEnterToChannel(List<Message> messages)
    {

    }

    @Override
    public void onUserInfo(Account user)
    {

    }

    @Override
    public void onUserLeaveChannel(Message message)
    {

    }

    @Override
    public void onUserEnterToChannel(Message message)
    {

    }

    @Override
    public void onMessage(Message message)
    {

    }
}
