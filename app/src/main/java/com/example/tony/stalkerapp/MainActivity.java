package com.example.tony.stalkerapp;

<<<<<<< HEAD
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
=======
>>>>>>> cc0a6399564fddf76b05ce3f476afc735e5fe366
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;




public class MainActivity extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ucwC2VMyvehJQHKUSZtD46uRF";
    private static final String TWITTER_SECRET = "s2sYX4Q8XuuLVziWP72I5yLrMM0mZZrBSU8fxn9bX2Kjp1Roul";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);


<<<<<<< HEAD


=======
>>>>>>> cc0a6399564fddf76b05ce3f476afc735e5fe366
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
           /* // Log setting open event with category="ui", action="open", and label="settings"
            Analytics.tracker().send(new HitBuilders.EventBuilder("ui", "open")
                    .setLabel("settings")
                    .build());
            return true;
        */
        }

        return super.onOptionsItemSelected(item);
    }
}
