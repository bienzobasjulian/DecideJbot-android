package com.example.decidejbot.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.view.LayoutInflater;

import com.example.decidejbot.R;

public class LoadingResultadosDialog {
    Activity activity;
    AlertDialog dialog;
    private MediaPlayer mediaPlayer;

    public LoadingResultadosDialog(Activity myActivity){
        this.activity = myActivity;
    }

    public void startLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_resultados_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();

        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), R.raw.drum_roll);
        mediaPlayer.start();

    }

    public void dimissDialog(){
        dialog.dismiss();
    }
}
