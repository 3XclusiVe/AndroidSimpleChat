package com.example.user.androidsimplechat.presentation.main_activity.frames.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.view.*;
import android.os.Bundle;
import com.example.user.androidsimplechat.presentation.main_activity.MainActivity;
import com.example.user.androidsimplechat.R;
import com.example.user.androidsimplechat.presentation.ServerClient;
import com.example.user.androidsimplechat.model.Account;
import com.example.user.androidsimplechat.model.ChatRoom;
import com.example.user.androidsimplechat.infrastructure.IChatServerResponcesObserver;
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            onReceiveArgument(args);
        }
    }

    protected abstract void onReceiveArgument(Bundle args);

    public static Fragment newInstance(Fragment instacne, String argKey, String argName)
    {
        Bundle args = new Bundle();
        args.putString(argKey, argName);
        instacne.setArguments(args);
        return instacne;
    }

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
