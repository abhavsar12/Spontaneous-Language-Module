package com.example.arpan.spontaneouslanguage;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Start extends AppCompatActivity {

    private String[] questions;
    private int pos;
    private TextView box;
    private int len;
    private static final String LOG_TAG = "AudioRecorder";
    private String name;
    private Intent intent;
    private String formattedDate;

    private MediaRecorder rec;
    private String folder = "/Recordings";
    private File path;

    public void setRecord(String session) {
        rec = new MediaRecorder();
        rec.setAudioSource(MediaRecorder.AudioSource.MIC);
        rec.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        rec.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        name = Environment.getExternalStorageDirectory() + folder + "/" + session;
        path = new File(name);
        if (!path.exists())
            path.mkdirs();
        int num = pos+1;
        name += "/Question" + num + ".3gp";
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questions = new String[10];
        questions[0] = "What is your name?";
        questions[1] = "How old are you?";
        questions[2] = "What did you eat for breakfast?";
        len = 3;
        pos = 0;
        box = (TextView) findViewById(R.id.question);
        box.setText(questions[pos]);

        Button next = (Button) findViewById(R.id.button);
        Button end  = (Button) findViewById((R.id.endButton));

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        formattedDate = df.format(c.getTime());
        Log.e(LOG_TAG, formattedDate);
        setRecord(formattedDate);
        startRecording();

        intent = new Intent(this, MainActivity.class);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos < len) {
                    stopRecording();
                    box.setText(questions[pos]);
                    setRecord(formattedDate);
                    startRecording();
                    pos++;
                } else
                    pos = 0;

            }

        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });

    }

}
