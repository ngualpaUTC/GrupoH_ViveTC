package com.grupoh_vivetc;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends ActionBarActivity {
	
	//codigo para crear slider
	private ImageSwitcher imageSwitcher;
	//aqui se hace un vector de imagenes
    private int[] gallery = { R.drawable.cotopaxi, R.drawable.cotopaxi2, R.drawable.quilotoa,
            R.drawable.saquisili, R.drawable.salcedo, R.drawable.pujili};
 
    private int position;
 
    private static final Integer DURATION = 2500;
 
    private Timer timer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewFactory() {
 
            public View makeView() {
                return new ImageView(MainActivity.this);
            }
        });
 
        // Set animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.abc_fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);
        
        startSlider();
		
	}
	//codigo para los ciclos del slider
	public void startSlider() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
 
            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(gallery[position]);
                        position++;
                        if (position == gallery.length) {
                            position = 0;
                        }
                    }
                });
            }
 
        }, 1000, DURATION);//duracion de cada imagen
    }

 // Stops the slider when the Activity is going into the background
    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            startSlider();
        }
 
    }
}
