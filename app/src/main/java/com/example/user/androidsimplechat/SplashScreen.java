package com.example.user.androidsimplechat;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.example.user.androidsimplechat.frames.ChatListFrame;
import com.example.user.androidsimplechat.frames.Connection;
import com.example.user.androidsimplechat.frames.SignFrame;
import com.example.user.androidsimplechat.model.*;

import java.io.IOException;
import java.util.List;

public class SplashScreen extends Activity implements ILoadable, IChatServerResponcesObserver
{
    private static Fragment currentFragment = null;
    private static Class<?> nextActivityToLoad = null;

    private ProgressDialog progressDialog;

    static String login = "mmmalkin007@mail.r";
    static String pass = "123456";
    static String nick = "QW12";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (nextActivityToLoad == null) {
            nextActivityToLoad = MainActivity.class;
        }
        if (savedInstanceState == null) {
            Connection connection = new Connection(this);
            connection.execute(login, pass);
        }

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

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();

            currentFragment = null;

            try {
                ServerClient.instance.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            this.finish();

        } else {
            getFragmentManager().popBackStack();
        }
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
        finishLoading();
        loadNextActivity();
    }

    @Override
    public void onFailToCoonnect(String reason)
    {
        finishLoading();
        loadFrame(reCreate(reason));
    }

    @Override
    public void finishLoading()
    {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void startLoad()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Connecting");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }


    private void loadFrame(Fragment fragmentToLoad)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragmentToLoad);
        ft.commit();
        this.invalidateOptionsMenu();
        currentFragment = fragmentToLoad;

    }

    @Override
    public void onRegister(String status)
    {
        if (status.equals("OK")) {
            onSuccessToConnect();
        } else {
            onFailToCoonnect(status);
        }
    }

    @Override
    public void onAuthorization(String status)
    {
        if (status.equals("OK")) {
            onSuccessToConnect();
        } else {
            onFailToCoonnect(status);
        }
    }

    private Fragment reCreate(String errorDescription)
    {
        SignFrame signFragment = new SignFrame();

        Bundle args = new Bundle();
        args.putString("error", errorDescription);
        signFragment.setArguments(args);

        return signFragment;
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
