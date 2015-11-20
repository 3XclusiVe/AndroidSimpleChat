package com.example.user.androidsimplechat.presentation.start_activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.presentation.ILoadable;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.presentation.Connection;
import com.example.user.androidsimplechat.infrastructure.IChatServerResponcesObserver;
import com.example.user.androidsimplechat.model.*;
import com.example.user.androidsimplechat.presentation.main_activity.MainActivity;
import com.example.user.androidsimplechat.utils.AthorizationDataSaver;

import java.io.IOException;
import java.util.List;

public class SplashScreen extends Activity implements ILoadable, IChatServerResponcesObserver
{
    private static Fragment currentFragment = null;
    private static Class<?> nextActivityToLoad = null;

    private volatile ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (nextActivityToLoad == null) {
            nextActivityToLoad = MainActivity.class;
        }
        if (savedInstanceState == null) {
            String login = AthorizationDataSaver.getSavedLogin(this);
            String pass = AthorizationDataSaver.getSavedPassword(this);
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
                if (ServerClient.instance != null) {
                    ServerClient.instance.disconnect();
                }
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


    private void loadNextActivity()
    {
        Intent intent = new Intent(this, nextActivityToLoad);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        ServerClient.instance.unsubscribe(this);
        this.finish();
    }

    private void onSuccessToConnect()
    {
        finishLoading();
        loadNextActivity();
    }

    private void onFailToConnect(String reason)
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

    @Override
    public void loadFrame(Fragment fragmentToLoad)
    {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragmentToLoad);
        ft.commit();
        //ft.commitAllowingStateLoss();
        this.invalidateOptionsMenu();
        currentFragment = fragmentToLoad;

    }

    @Override
    public void onRegister(String status)
    {
        if (status.equals("OK")) {

        } else {
            onFailToConnect(status);
        }
    }

    @Override
    public void onAuthorization(String status)
    {
        if (status.equals("OK")) {
            onSuccessToConnect();
        } else {
            onFailToConnect(status);
        }
    }

    private Fragment reCreate(String errorDescription)
    {
        if (currentFragment == null) {
            currentFragment = new SignInFrame();
            return currentFragment;

        } else {
            Fragment Fragment = null;

            try {
                Fragment = currentFragment.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            Bundle args = new Bundle();
            args.putString("error", errorDescription);
            Fragment.setArguments(args);

            return Fragment;
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
    public void onCreateChannel(String status)
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

    @Override
    public void onChangeUserInfo()
    {

    }
}
