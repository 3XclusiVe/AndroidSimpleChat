package com.example.user.androidsimplechat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.user.androidsimplechat.frames.ChatListFrame;
import com.example.user.androidsimplechat.frames.Preferences;
import com.example.user.androidsimplechat.frames.SignFrame;
import com.example.user.androidsimplechat.frames.UserInfoFrame;
import com.example.user.androidsimplechat.model.Client;

public class MainActivity extends Activity implements IFramable
{
    private static Fragment currentFragment;
    private static Fragment startFragment = new SignFrame();
    private static Client serverClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFrame(startFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                loadFrame(new Preferences());
                return true;

            case R.id.action_account:
                loadFrame(new UserInfoFrame());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadFrame(Fragment fragmentToLoad)
    {
        if (fragmentToLoad != currentFragment) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, fragmentToLoad);
            if (currentFragment != null) {
                ft.addToBackStack(null);
            }
            ft.commit();
            this.invalidateOptionsMenu();
            currentFragment = fragmentToLoad;
        }
    }

    @Override
    public Client getClient()
    {
        return serverClient;
    }

    @Override
    public void setClient(Client client)
    {
        serverClient = client;
    }

    @Override
    public void onAuth()
    {
        ChatListFrame frame;
        frame = (ChatListFrame) currentFragment;
        frame.onAuthorization("ok");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        if ((currentFragment instanceof SignFrame) || (currentFragment instanceof ChatListFrame)) {
            currentFragment = null;
            this.finish();
        }
    }
}
