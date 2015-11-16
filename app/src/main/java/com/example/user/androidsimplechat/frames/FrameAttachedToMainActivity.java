package com.example.user.androidsimplechat.frames;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.view.*;
import com.example.user.androidsimplechat.IFramable;
import android.os.Bundle;
import com.example.user.androidsimplechat.MainActivity;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.ServerClient;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.model.ChatRoom;
import com.example.user.androidsimplechat.model.IChatServerResponcesObserver;
import com.example.user.androidsimplechat.model.Message;

import java.util.List;

/**
 * Created by user on 07.11.15.
 */
public abstract class FrameAttachedToMainActivity extends Fragment implements IChatServerResponcesObserver
{
    protected MainActivity mainActivity;
    private int mActionBar;
    private String mActionBarTitle;
    private ProgressDialog progressDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mainActivity == null) {
            mainActivity = (MainActivity) getActivity();
        }

        mActionBar = getActionBar();
        mActionBarTitle = getActionBarTitle();

        setHasOptionsMenu(true);

        ServerClient.instance.subscribe(this);

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

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ServerClient.instance.unsubscribe(this);
    }

    protected int getActionBar()
    {
        return R.menu.fragment_menu;
    }

    public void finishLoading()
    {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void startLoad()
    {
        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setMessage("Loading data");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    public void onRegister(String status)
    {

    }

    @Override
    public void onAuthorization(String status)
    {

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
