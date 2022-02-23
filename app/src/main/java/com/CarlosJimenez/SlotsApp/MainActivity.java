package com.CarlosJimenez.SlotsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //dropdown
    AppCompatTextView tvPicker;

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

        //dropdown
        tvPicker = findViewById(R.id.tvPicker);
        tvPicker.setOnClickListener(view -> showCategoryMenu());

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


    //dropdown
    private void showCategoryMenu(){
        final SetBetDropdownMenu menu = new SetBetDropdownMenu(this);
        menu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        menu.setWidth(getPxFromDp(200));
        menu.setOutsideTouchable(true);
        menu.setFocusable(true);
        menu.showAsDropDown(tvPicker);
        menu.setBetSelectedListener((position, setBet) -> {
            menu.dismiss();
            Toast.makeText(MainActivity.this, "Your bet has been set at : $"+ setBet.bet, Toast.LENGTH_SHORT).show();
        });
    }

    //Convert DP to Pixel
    private int getPxFromDp(int dp){
        return (int) (dp * getResources().getDisplayMetrics().density);
    }



}