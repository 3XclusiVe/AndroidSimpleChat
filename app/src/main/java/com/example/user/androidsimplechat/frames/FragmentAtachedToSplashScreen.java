package com.example.user.androidsimplechat.frames;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.user.androidsimplechat.ILoadable;
import com.example.user.androidsimplechat.R;

/**
 * Created by user on 16.11.15.
 */
public abstract class FragmentAtachedToSplashScreen extends Fragment
{
    private static Connection connectionTask;

    private ILoadable mainActivity;
    private Button enterButton;
    private EditText loginEditText;
    private EditText passwordEditText;
    protected EditText nickNameEditText;
    protected Button changePerspectiveButton;
    private TextView errorMessage;
    private String error;

    private boolean isRegistrationFrame;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.sign_frame, null);

        if (mainActivity == null) {
            mainActivity = (ILoadable) getActivity();
        }

        enterButton = (Button) v.findViewById(R.id.connect_button);
        loginEditText = (EditText) v.findViewById(R.id.login);
        passwordEditText = (EditText) v.findViewById(R.id.password);
        errorMessage = (TextView) v.findViewById(R.id.error);
        nickNameEditText = (EditText) v.findViewById(R.id.nickname);
        changePerspectiveButton = (Button) v.findViewById(R.id.change_perspective_button);

        errorMessage.setText(error);

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        isRegistrationFrame = isRegistrationFrame();

        enterButton.setOnClickListener(new View.OnClickListener()

                                       {
                                           public void onClick(View v)
                                           {

                                               String login = loginEditText.getText().toString();
                                               String password = passwordEditText.getText().toString();
                                               String nick = nickNameEditText.getText().toString();


                                               connectionTask = new Connection(mainActivity);
                                               if (!isRegistrationFrame) {
                                                   connectionTask.execute(login, password);
                                               } else {
                                                   connectionTask.execute(login, password, nick);
                                               }

                                           }
                                       }

        );

        changePerspectiveButton.setOnClickListener(new View.OnClickListener()

                                                   {
                                                       public void onClick(View v)
                                                       {
                                                           if (!isRegistrationFrame) {
                                                               mainActivity.loadFrame(new SignUpFrame());
                                                           } else {
                                                               mainActivity.loadFrame(new SignInFrame());
                                                           }
                                                       }
                                                   }

        );
    }

    protected abstract boolean isRegistrationFrame();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.error = "";
        Bundle args = getArguments();
        if (args != null) {
            String error = args.getString("error");
            if (error != null) {
                this.error = error;
            }
        }
    }
}
