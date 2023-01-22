package my.app.youtubeplayer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public Button button;
    public TextView timerText ;
    public Timer timer;
    static TimerTask timerTask;
    public Double time = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView fullScreenButton =(ImageView) findViewById(R.id.fullscreen_button);
        button = (Button) findViewById(R.id.button1);
        timer = new Timer();
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.toggleFullScreen();
            }
        });
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "HbTON0nb4DU";
                youTubePlayer.loadVideo(videoId, 0);
                startTimer();

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ScreenTime.class);
                startActivity(intent);
            }
        });
    }
    public void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
//                        ScreenTime screenTime = new ScreenTime();
                        try {
                            ScreenTime.updateTextView(getTimerText());
                        }catch (Exception e){
                            Log.e("ERROR OCCURED", e.toString());
                        }
                        Log.v("TIMER CHECK", time.toString());
//                        timerText.setText(getTimerText());
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask , 0 , 1000);
    }
    private String getTimerText(){
        int rounded = (int) Math.round(time);

        int minutes = ((rounded % 86400) % 3600) % 60;
        int hours = ((rounded % 86400) % 3600) / 60;

        return formatTime(minutes,hours);
    }
    private String formatTime(int minutes , int hours){
        return  String.format("%02d" , hours)+" : "+String.format("%02d" , minutes);
    }
}