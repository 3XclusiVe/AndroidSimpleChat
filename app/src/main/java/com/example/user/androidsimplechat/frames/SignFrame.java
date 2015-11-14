package com.example.user.androidsimplechat.frames;

import android.app.Fragment;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import com.example.user.androidsimplechat.ILoadable;
import com.example.user.androidsimplechat.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 07.11.15.
 */
public class SignFrame extends Fragment
{
    private ILoadable mainActivity;
    Button button;
    EditText loginEditText;
    EditText passwordEditText;
    EditText nicknameEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.sign_frame, null);

        if (mainActivity == null) {
            mainActivity = (ILoadable) getActivity();
        }

        button = (Button) v.findViewById(R.id.sign_button);
        loginEditText = (EditText) v.findViewById(R.id.login);
        passwordEditText = (EditText) v.findViewById(R.id.password);
        nicknameEditText = (EditText) v.findViewById(R.id.nickname);

        loginEditText.setText("mmmalkin007@mail.r");
        passwordEditText.setText("12345");
        nicknameEditText.setText("QW12");

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener()

                                  {
                                      public void onClick(View v)
                                      {

                                          String login = loginEditText.getText().toString();
                                          String password = passwordEditText.getText().toString();
                                          String nick = nicknameEditText.getText().toString();

                                          Connection connection = new Connection(mainActivity);
                                          connection.execute(login, password, nick);
                                      }
                                  }

        );
    }
}
