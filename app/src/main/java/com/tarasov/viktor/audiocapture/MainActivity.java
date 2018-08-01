package com.tarasov.viktor.audiocapture;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder recorder = new MediaRecorder();
    private MediaPlayer mediaPlayer;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //RECORD START
    public void rec(View v) {
        try {
            //new file
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());
            fileName = Environment.getExternalStorageDirectory() + "/record_" + currentDateandTime + ".3gpp";

            recorder.release();

            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            recorder.setOutputFile(fileName);
            recorder.setMaxDuration(10000);
            recorder.prepare();
            recorder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //PLAY
    public void play(View v) {
        try {
            if (recorder != null) {
                recorder.release();
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
