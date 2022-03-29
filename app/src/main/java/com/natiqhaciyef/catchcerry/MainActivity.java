package com.natiqhaciyef.catchcerry;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView c1, c2 , c3 , c4 , c5 , c6 ,c7 ,c8 ,c9 ,c10 ,c11 ,c12 ;
    ImageView [] imageArray ;

    TextView time ;
    TextView score ;
    int point = 0 ;

    Handler handler ;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        score = findViewById(R.id.score);

        c1 = findViewById(R.id.imageView1);
        c2 = findViewById(R.id.imageView2);
        c3 = findViewById(R.id.imageView3);
        c4 = findViewById(R.id.imageView4);
        c5 = findViewById(R.id.imageView5);
        c6 = findViewById(R.id.imageView6);
        c7 = findViewById(R.id.imageView7);
        c8 = findViewById(R.id.imageView8);
        c9 = findViewById(R.id.imageView9);
        c10 = findViewById(R.id.imageView10);
        c11 = findViewById(R.id.imageView11);
        c12 = findViewById(R.id.imageView12);


        imageArray = new ImageView[] {c1 , c2 , c3 ,c4 ,c5 , c6 , c7 ,c8 ,c9 , c10 , c11 ,c12};

        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                time.setText("Time : " + l/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Time Finished", Toast.LENGTH_LONG);
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Game ended !");
                alert.setMessage("Do you want to start again");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Application stopped",Toast.LENGTH_LONG);
                        finish();
                    }
                });

                alert.show();
            }

        }.start();



    }


    public void mouseClick(View view){
        point++ ;
        score.setText("Score : " + point);

    }


    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(12);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this , 750);
            }
        };

        handler.post(runnable);

    }


}