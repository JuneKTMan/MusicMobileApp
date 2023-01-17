package com.MusicApp.loginRegister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import java.io.File;

public class Success extends AppCompatActivity {
    // changed to public
    public static final String CLIENT_ID = "24bc552722a3438c813de1690aeb2a69";
    public static final String REDIRECT_URI = "http://localhost";
    public SpotifyAppRemote mSpotifyAppRemote;

    DrawerLayout drawerLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        drawerLayout=findViewById(R.id.drawer_forConnection);
    }

    public void ClickMenu(View view) {MainActivity.openDrawer(drawerLayout);}
    public void ClickLogo(View view){MainActivity.closeDrawer(drawerLayout);}
    public void ClickLogout(View view){MainActivity.redirectActivity(this,MainActivity.class);}



    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    private void connected() {
        // Play a playlist
       // mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
        Log.d("myTag", "CONNECTED!!");
        user_page();


        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                    }
                });
    }

    public void spotifyConnect(View view) {
        Log.d("myTag", "Button clicked");

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                       .showAuthView(true)
                       .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("Success", "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        connected();
                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("Success", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });

    }


    public void user_page() {
        Intent intent = new Intent(this, UserPage.class);
        startActivity(intent);
        finish();
    }


}
