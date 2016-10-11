package za.co.codetribe.quizzionarev3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ImageButton imb_movies;
    ImageButton imb_cars;
    ImageButton imb_tech;

    String title = "";
    Handler handler;

    Animation main_button_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imb_movies = (ImageButton) findViewById(R.id.imb_movies);
        imb_cars = (ImageButton) findViewById(R.id.imb_cars);
        imb_tech = (ImageButton) findViewById(R.id.imb_tech);

        imb_movies.setOnClickListener(this);
        imb_cars.setOnClickListener(this);
        imb_tech.setOnClickListener(this);

        Animation main_button_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        imb_movies.setAnimation(main_button_animation);
        imb_cars.setAnimation(main_button_animation);
        imb_tech.setAnimation(main_button_animation);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();



        if(id == R.id.imb_movies)
        {
            title = "Movies";

        }
        else if(id == R.id.imb_cars)
        {
            title = "Cars";
        }
        else if(id == R.id.imb_tech)
        {
            title = "Tech";
        }


        main_button_animation = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        imb_tech.startAnimation(main_button_animation);
        imb_cars.startAnimation(main_button_animation);
        imb_movies.startAnimation(main_button_animation);

        handler = new Handler();

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(MainActivity.this,Questions.class);
                i.putExtra("title",title);
                startActivity(i);
            }
        },1200);




    }
}
