package com.MusicApp.loginRegister;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.drawerlayout.widget.DrawerLayout;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Track;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UserPage extends Success {

    private TextView textView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main_page);

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
                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("Success", throwable.getMessage(), throwable);

                    }
                });

        textView = findViewById(R.id.playView);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    float x_accl = sensorEvent.values[0];
                    float y_accl = sensorEvent.values[1];
                    float z_accl = sensorEvent.values[2];

                    float floatSum = Math.abs(x_accl) + Math.abs(y_accl) + Math.abs(z_accl);

                    if (floatSum > 14) {
                        List<String> givenList = Arrays.asList(
                                "spotify:playlist:3orcllxQnhCM4lGjrg0Blq",
                                "spotify:playlist:37i9dQZF1DX889U0CL85jj",
                                "spotify:playlist:37i9dQZF1EVHGWrwldPRtj",
                                "spotify:playlist:6nxPNnmSE0d5WlplUsa5L3",
                                "spotify:playlist:6nxPNnmSE0d5WlplUsa5L3",
                                "spotify:playlist:0DiIiLcScBTTTRgtRkwGxe",
                                "spotify:playlist:37i9dQZF1DX7qK8ma5wgG1",
                                "spotify:playlist:37i9dQZF1DX76Wlfdnj7AP",
                                "spotify:playlist:37i9dQZF1DWUSyphfcc6aL",
                                "spotify:playlist:37i9dQZF1DX9oh43oAzkyx",
                                "spotify:playlist:37i9dQZF1DWZqd5JICZI0u",
                                "spotify:playlist:3ksy3Zso4vdt4JIzTYvpF9",
                                "spotify:playlist:37i9dQZF1DXcvykn1vm7iP",
                                "spotify:playlist:15eJCdcsxMvR0KJhYRnYx4",
                                "spotify:playlist:5AHH67GYsljwoB1q6UGvWg",
                                "spotify:playlist:4nDV6PoLfWz1S7U7JYsFYq");

                        Random rand = new Random();
                        String randomElement = givenList.get(rand.nextInt(givenList.size()));

                        mSpotifyAppRemote.getPlayerApi().play(randomElement);
                        mSpotifyAppRemote.getPlayerApi()
                                .subscribeToPlayerState()
                                .setEventCallback(playerState -> {
                                    final Track track = playerState.track;
                                    if (track != null) {
                                        Log.d("Success", track.name + " by " + track.artist.name);
                                        TextView showSong = (TextView)findViewById(R.id.playView);
                                        showSong.setText(track.name + " by " + track.artist.name);
                                    }
                                });
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensorManager.registerListener(sensorEventListener, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
        // find view for the drawer
        drawerLayout=findViewById(R.id.drawer_forUser);
    }
    // The call the navdrawer layout
    public void ClickMenu(View view) {MainActivity.openDrawer(drawerLayout);}
    public void ClickLogo(View view){MainActivity.closeDrawer(drawerLayout);}
    public void ClickLogout(View view){MainActivity.redirectActivity(this,MainActivity.class);}

    // chill vibes playlist buttons
    public void chillvibes_btn1(View view) {
        // play playlist here
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:3orcllxQnhCM4lGjrg0Blq");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });

        String response = "ChillVibes btn 1 clicked";
        Log.d("res", response);
    }

    public void chillvibes_btn2(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX889U0CL85jj");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);

                    }
                });
        String response = "ChillVibes btn 2 clicked";
        Log.d("res", response);
    }

    public void chillvibes_btn3(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1EVHGWrwldPRtj");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "ChillVibes btn 3 clicked";
        Log.d("res", response);
    }

    // sad playlist buttons
    public void sad_btn1(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:6nxPNnmSE0d5WlplUsa5L3");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "sad btn 1 clicked";
        Log.d("res", response);
    }

    public void sad_btn2(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:0DiIiLcScBTTTRgtRkwGxe");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "sad btn 2 clicked";
        Log.d("res", response);
    }

    public void sad_btn3(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX7qK8ma5wgG1");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "sad btn 3 clicked";
        Log.d("res", response);
    }

    // workout playlist buttons
    public void workout_btn1(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX76Wlfdnj7AP");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "workout btn 1 clicked";
        Log.d("res", response);
    }

    public void workout_btn2(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DWUSyphfcc6aL");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "workout btn 2 clicked";
        Log.d("res", response);
    }

    public void workout_btn3(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX9oh43oAzkyx");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "workout btn 3 clicked";
        Log.d("res", response);
    }

    // meditation playlist buttons
    public void med_btn1(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DWZqd5JICZI0u");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "meditation btn 1 clicked";
        Log.d("res", response);
    }

    public void med_btn2(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:3ksy3Zso4vdt4JIzTYvpF9");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "meditation btn 2 clicked";
        Log.d("res", response);
    }

    public void med_btn3(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DXcvykn1vm7iP");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "meditation btn 3 clicked";
        Log.d("res", response);
    }

    // gaming playlist buttons
    public void gaming_btn1(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:15eJCdcsxMvR0KJhYRnYx4");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "gaming btn 1 clicked";
        Log.d("res", response);
    }

    public void gaming_btn2(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:5AHH67GYsljwoB1q6UGvWg");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "gaming btn 2 clicked";
        Log.d("res", response);
    }

    public void gaming_btn3(View view) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:4nDV6PoLfWz1S7U7JYsFYq");
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Success", track.name + " by " + track.artist.name);
                        TextView showSong = (TextView)findViewById(R.id.playView);
                        showSong.setText(track.name + " by " + track.artist.name);
                    }
                });
        String response = "gaming btn 3 clicked";
        Log.d("res", response);
    }
   // close the drawer
    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(drawerLayout);
    }


}