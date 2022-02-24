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
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //dropdown
    AppCompatTextView tvPicker;

    private TextView msg;
    private ImageView img1, img2, img3;
    private Reel reel1, reel2, reel3;
    private Button btn;
    private TextView curr_bet;
    private TextView curr_balance;
    private boolean isStarted;

    private boolean isBetSet;
    private int dollarBet;
    private int currentBalance;
    private String tempStringCurrBalance;

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
        curr_bet = findViewById(R.id.current_bet);
        curr_balance = findViewById(R.id.current_balance);


        //temp curr balance
        tempStringCurrBalance = curr_balance.getText().toString();
        String tempNumsOnly = tempStringCurrBalance.replaceAll("[^0-9]", "");
        currentBalance = Integer.parseInt(tempNumsOnly);

        //dropdown
        tvPicker = findViewById(R.id.tvPicker);
        tvPicker.setOnClickListener(view -> showCategoryMenu());

        btn.setOnClickListener(view -> {
            //check if a bet is set
            if(isBetSet && dollarBet<=currentBalance) {
                //if btn pressed while reels are running
                //stop and display results
                if (isStarted) {
                    reel1.stopReel();
                    reel2.stopReel();
                    reel3.stopReel();

                    if (reel1.currentIndex == reel2.currentIndex && reel2.currentIndex == reel3.currentIndex) {
                        //JACKPOT
                        msg.setText("+$"+(dollarBet * 3));
                        currentBalance = currentBalance + (dollarBet * 3);
                        curr_balance.setText("Current Balance: $" + currentBalance);
                        Toast.makeText(MainActivity.this, "JACKPOT!!!", Toast.LENGTH_LONG).show();
                    } else if (reel1.currentIndex == reel2.currentIndex || reel2.currentIndex == reel3.currentIndex
                            || reel1.currentIndex == reel3.currentIndex) {
                        //TWO HITS
                        msg.setText("+$"+(dollarBet ));
                        currentBalance = currentBalance + dollarBet;
                        curr_balance.setText("Current Balance: $" + currentBalance);
                        Toast.makeText(MainActivity.this, "SMALL WIN!", Toast.LENGTH_LONG).show();
                    } else {
                        //LOSS
                        msg.setText("-$"+ (dollarBet));
                        currentBalance = currentBalance - dollarBet;
                        curr_balance.setText("Current Balance: $" + currentBalance);
                        Toast.makeText(MainActivity.this, "TRY AGAIN!", Toast.LENGTH_LONG).show();
                    }

                    btn.setText("Start");
                    isStarted = false;


                } else { //else make reels run

                    //stop the mashing
                    btn.setEnabled(false);
                    Timer buttonTimer = new Timer();
                    buttonTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(() -> btn.setEnabled(true));
                        }
                    }, 3000); //3 sec delay


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
                            200, randomLong(300, 800)); //150,600 better odds

                    reel3.start();

                    btn.setText("Stop");
                    msg.setText("");
                    isStarted = true;
                }
            }
            else{ //notify to set a bet or balance too low
                if (dollarBet>=currentBalance){
                    Toast.makeText(MainActivity.this, "Balance is too low!", Toast.LENGTH_SHORT).show();
                    msg.setText("");
                    curr_bet.setText("Current Bet: $0");
                }
                else{
                    Toast.makeText(MainActivity.this, "Set a bet!", Toast.LENGTH_SHORT).show();
                    msg.setText("");
                }
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
            //show current bet
            dollarBet = Integer.parseInt(setBet.bet);
            if (dollarBet <= currentBalance){
                Toast.makeText(MainActivity.this, "Your bet has been set at : $" + setBet.bet, Toast.LENGTH_SHORT).show();
                isBetSet = true;
                curr_bet.setText("Current Bet: $" + setBet.bet);
            }
            else{
                Toast.makeText(MainActivity.this, "Balance is too low!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Convert DP to Pixel
    private int getPxFromDp(int dp){
        return (int) (dp * getResources().getDisplayMetrics().density);
    }



}