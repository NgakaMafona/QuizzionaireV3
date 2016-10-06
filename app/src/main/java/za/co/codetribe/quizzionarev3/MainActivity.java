package za.co.codetribe.quizzionarev3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ImageButton imb_movies;
    ImageButton imb_cars;
    ImageButton imb_tech;

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

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        String title = "";

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

        Intent i = new Intent(MainActivity.this,Questions.class);
        i.putExtra("title",title);
        startActivity(i);
    }
}
