package com.CarlosJimenez.SlotsApp;

public class Reel extends Thread{

    interface ReelListener{
        void newImage(int img);
    }

    private static int[] imgs = {R.drawable.slot1, R.drawable.slot2, R.drawable.slot3, R.drawable.slot4,
            R.drawable.slot5, R.drawable.slot6};

    //current image
    public int currentIndex;
    //notify when image changes
    private ReelListener reelListener;
    //duration of image
    private long frameDuration;
    //reel start delay
    private long startIn;
    private boolean isStarted;

    public Reel(ReelListener reelListener, long frameDuration, long startIn) {
        this.reelListener = reelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        isStarted = true;
    }

    public void nextImg() {
        currentIndex++;

        if (currentIndex == imgs.length) {
            currentIndex = 0;
        }
    }

    @Override
    public void run() {
        try {
            //pause in ms
            Thread.sleep(startIn);
        } catch (InterruptedException e) {
        }

        while(isStarted) {
            try {
                //leave image for set duration
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {
            }

            //go to next image
            nextImg();

            if (reelListener != null) {
                //loop all images
                reelListener.newImage(imgs[currentIndex]);
            }
        }
    }

    public void stopReel() {
        isStarted = false;
    }
}




