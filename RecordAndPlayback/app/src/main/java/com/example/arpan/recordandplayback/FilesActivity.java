package com.example.arpan.recordandplayback;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;

public class FilesActivity extends AppCompatActivity {

    private String[] names;
    private ArrayAdapter<String> adapter;
    private File dir;
    private static Context context;
    private MediaPlayer pl = null;
    private static final String LOG_TAG = "AudioRecorder";
    private String str = Environment.getExternalStorageDirectory().getAbsolutePath();
    private String folder = "/Recordings";

    public void startPlaying(String n) {
        pl = new MediaPlayer();
        try {
            pl.setDataSource(n);
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
        setContentView(R.layout.activity_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        ListView lv = (ListView) findViewById(R.id.listView);


        dir =  new File(str + "/Recordings");
        if (!dir.exists())
            dir.mkdirs();
        File[] fileArray = dir.listFiles();
        names = new String[fileArray.length];
        for (int i = 0; i < names.length; i++) {
            if (fileArray[i].isFile())
                names[i] = fileArray[i].getName();
        }
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> lv, View v, int pos, long id) {
                String name = (String) lv.getItemAtPosition(pos);
                String fileName = str + folder + "/" + name;
                startPlaying(fileName);
            }


        });
    }

}
