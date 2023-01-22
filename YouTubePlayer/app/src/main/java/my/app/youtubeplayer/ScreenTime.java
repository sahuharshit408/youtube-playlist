package my.app.youtubeplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ScreenTime extends AppCompatActivity {

    static TextView timerText ;
    Button resetBtn ;

    Timer timer;
    TimerTask timerTask;
    TimerTask mainTimerTask;
    Double time;

    boolean timerStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_time);

        timerText = (TextView) findViewById(R.id.timeclock);
        resetBtn = (Button) findViewById(R.id.ResetBtn);
        MainActivity mA = new MainActivity();
        timer = mA.timer;
        time = mA.time;
        mainTimerTask = mA.timerTask;

    }
    static void updateTextView(String time){
        timerText.setText(time);
    }
    public void resetTapped(View view){
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are You Sure You Want to Reset?");

        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                timerTask.cancel();
                MainActivity.timerTask.cancel();
                time = 0.0;
                timerStarted = false;
                timerText.setText(formatTime(0 , 0));
            }
        });

        resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do Nothing
            }
        });

        resetAlert.show();
    }

//    public void startTimer(){
//        timerTask = new TimerTask() {
//            @Override
//            public void run() {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        time++;
//                        timerText.setText(getTimerText());
//                    }
//                });
//
//            }
//        };
//        timer.scheduleAtFixedRate(timerTask , 0 , 1000);
//    }

//    private String getTimerText(){
//        int rounded = (int) Math.round(time);
//
//        int minutes = ((rounded % 86400) % 3600) / 60;
//        int hours = ((rounded % 86400) % 3600);
//
//        return formatTime(minutes,hours);
//    }
//
    private String formatTime(int minutes , int hours){
        return String.format("%02d" , hours) +" : "+ String.format("%02d" , minutes);
    }
}