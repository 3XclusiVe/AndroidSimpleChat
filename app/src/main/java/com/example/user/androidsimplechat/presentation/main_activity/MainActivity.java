package com.example.user.androidsimplechat.presentation.main_activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.presentation.IFramable;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.presentation.main_activity.add_chat_room_fragment.AddChatRoomFragment;
import com.example.user.androidsimplechat.presentation.main_activity.frames.chat_list_frame.ChatListFrame;
import com.example.user.androidsimplechat.presentation.main_activity.frames.Preferences;
import com.example.user.androidsimplechat.presentation.main_activity.frames.UserInfoFrame;
import com.example.user.androidsimplechat.presentation.main_activity.frames.chat_room_members_frame.ChatRoomMembersFrame;
import com.example.user.androidsimplechat.presentation.main_activity.frames.self_user_info_fragment.self_user_info_fragment;

import java.io.IOException;

public class MainActivity extends Activity implements IFramable
{
    private static Fragment currentFragment;
    private static Fragment startFragment = new ChatListFrame();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (currentFragment == null) {
            loadFrame(startFragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                loadFrame(new self_user_info_fragment());
                return true;

            case R.id.action_add_chat:
                loadFrame(new AddChatRoomFragment());
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
}
