package com.CarlosJimenez.SlotsApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView msg;
    private ImageView img1, img2, img3;
    private Reel reel1, reel2, reel3;
    private Button btn;
    private boolean isStarted;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        btn = findViewById(R.id.btn);
        msg = findViewById(R.id.msg);

        btn.setOnClickListener(view -> {
            //if btn pressed while reels are running
            //stop and display results
            if (isStarted) {
                reel1.stopReel();
                reel2.stopReel();
                reel3.stopReel();

                if (reel1.currentIndex == reel2.currentIndex && reel2.currentIndex == reel3.currentIndex) {
                    msg.setText("You win the big prize");
                } else if (reel1.currentIndex == reel2.currentIndex || reel2.currentIndex == reel3.currentIndex
                        || reel1.currentIndex == reel3.currentIndex) {
                    msg.setText("Little Prize");
                } else {
                    msg.setText("You lose");
                }

                btn.setText("Start");
                isStarted = false;

                //else make reels run
            } else {

                reel1 = new Reel(img -> runOnUiThread(() ->
                        img1.setImageResource(img)),
                        200, randomLong(0, 200));

                reel1.start();

                reel2 = new Reel(img -> runOnUiThread(() ->
                        img2.setImageResource(img)),
                        200, randomLong(150, 400));

                reel2.start();

                reel3 = new Reel(img -> runOnUiThread(() ->
                        img3.setImageResource(img)),
                        200, randomLong(150, 600));

                reel3.start();

                btn.setText("Stop");
                msg.setText("");
                isStarted = true;
            }
        });




    }
}