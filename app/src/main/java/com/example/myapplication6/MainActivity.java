package com.example.myapplication6;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class MainActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {


    //AIzaSyAJ77vDsBTCXGAFWA8uQeZikOpYmvu_CJo
    public static final String API_KEY = "AIzaSyDkrp_fr1QDU0C0EApK4Rhzt6ejKxrflUw";
    public static final String VIDEO_ID = "CQ6dbUmU__o";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);

        youTubePlayerFragment.initialize(API_KEY, this);

    }

    /**
     * @param provider      The provider which was used to initialize the YouTubePlayer
     * @param youTubePlayer A YouTubePlayer which can be used to control video playback in the provider.
     * @param wasRestored   Whether the player was restored from a previously saved state, as part of the YouTubePlayerView
     *                      or YouTubePlayerFragment restoring its state. true usually means playback is resuming from where
     *                      the user expects it would, and that a new video should not be loaded
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION |
                YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);


        if (!wasRestored) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    /**
     * @param provider The provider which failed to initialize a YouTubePlayer.
     * @param error    The reason for this failure, along with potential resolutions to this failure.
     */
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {

        final int REQUEST_CODE = 1;

        if (error.isUserRecoverableError()) {
            error.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", error.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    //[[メニューバー]] p280
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);

        Log.i("log", "onCreateOptionsMenu");

        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.top:
//                Intent i = new Intent(getApplication(), WebActivity.class);
//                startActivity(i);
//                break;
//
//            case R.id.store:
//                Intent i2 = new Intent(getApplication(), StoreActivity.class);
//                startActivity(i2);
//                break;
//
//            case R.id.contact:
//                Intent i3 = new Intent(getApplication(), ContactActivity.class);
//                startActivity(i3);
//                break;
//        }
//
//        Log.i("log", "onCreateOptionsItemSelected");
//
//        return true;
//    }
}
