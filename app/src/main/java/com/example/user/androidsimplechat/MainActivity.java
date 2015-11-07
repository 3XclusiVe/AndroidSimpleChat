package com.example.user.androidsimplechat;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.user.androidsimplechat.frames.SignFrame;
import com.example.user.androidsimplechat.frames.UserInfoFrame;

public class MainActivity extends AppCompatActivity implements IFramable
{
    private static Fragment currentFragment;
    private static Fragment startFragment = new SignFrame();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFrame(startFragment);
            Log.d("1", "1");
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            loadFrame(new UserInfoFrame());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadFrame(Fragment fragmentToLoad)
    {
        if (fragmentToLoad != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, fragmentToLoad);
            ft.addToBackStack(null);
            ft.commit();
            currentFragment = fragmentToLoad;
        }
    }

}
