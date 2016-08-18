package com.example.arpan.recordandplayback;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static final String LOG_TAG = "AudioRecorder";
    private String name = null;
    private MediaRecorder rec = null;
    private MediaPlayer pl = null;
    private String folder = "/Recordings";
    private File path = null;

    public void setRecord() {
        rec = new MediaRecorder();
        rec.setAudioSource(MediaRecorder.AudioSource.MIC);
        rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        name = Environment.getExternalStorageDirectory().getAbsolutePath();
        path = new File(name + folder);
        if (!path.exists())
            path.mkdirs();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyyHH-mm-ss");
        String timeStamp = df.format(c.getTime());
        name += folder + "/Recording" + timeStamp + ".3gp";
        rec.setOutputFile(name);
        Log.e(LOG_TAG, name);
    }

    public void startRecording() {
        try {
            rec.prepare();
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        rec.start();
    }

    public void stopRecording() {
        rec.stop();
        rec.release();
        rec = null;
    }

    public void startPlaying() {
        pl = new MediaPlayer();
        try {
            pl.setDataSource(name);
            pl.prepare();
            pl.start();
        }
        catch (IOException e) {
            Log.e(LOG_TAG, "Prepare failed");
        }
    }

    public void stopPlaying() {
        pl.release();
        pl = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton record = (ToggleButton) findViewById(R.id.toggleButton);
        ToggleButton play = (ToggleButton) findViewById(R.id.toggleButton2);
        Button files = (Button) findViewById(R.id.filebutton);

        record.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
                //TextView text = (TextView) findViewById(R.id.sample);
                if (isChecked) {
                    //text.setVisibility(View.VISIBLE);
                    setRecord();
                    startRecording();
                    findViewById(R.id.toggleButton2).setEnabled(false);
                }
                else {
                    //text.setVisibility(View.INVISIBLE);
                    stopRecording();
                    findViewById(R.id.toggleButton2).setEnabled(true);
                }
            }

        });


        play.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
                if (isChecked) {
                    startPlaying();
                    findViewById(R.id.toggleButton).setEnabled(false);
                }
                else {
                    stopPlaying();
                    findViewById(R.id.toggleButton).setEnabled(true);
                }
            }
        });

        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, FilesActivity.class);
                myIntent.putExtra("path", path);
                MainActivity.this.startActivity(myIntent);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.arpan.recordandplayback/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.arpan.recordandplayback/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}