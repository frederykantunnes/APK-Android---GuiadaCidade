package br.com.bitcodeti.guia.View;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import br.com.bitcodeti.guia.R;

public class RadioFM92 extends Activity {

    private Button play;
    private String URL = "http://45.43.208.42:22160/";
    private MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_radio_fm92);


        mPlayer = new MediaPlayer();
        play = (Button)findViewById(R.id.play_radio92);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mPlayer.setDataSource(URL);
                    mPlayer.prepareAsync();
                    TextView a = (TextView)findViewById(R.id.text_ouvindo_92);
                    a.setVisibility(View.VISIBLE);
                    play.setText("   OUVINDO...   ");
                    play.setEnabled(false);
                    mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}
