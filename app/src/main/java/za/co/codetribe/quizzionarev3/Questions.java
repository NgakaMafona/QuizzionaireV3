package za.co.codetribe.quizzionarev3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import za.co.codetribe.quizzionarev3.BackgroundTask.Randomize;
import za.co.codetribe.quizzionarev3.BackgroundTask.Topic;

public class Questions extends AppCompatActivity
{
    String topic = "";

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    Topic top;
    Randomize random;

    String[] questions;
    String[] answer;

    String[] ran_ques;
    String[] ran_answ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.question_list);

        Intent i = getIntent();
        topic = i.getStringExtra("title");

        top = new Topic();
        random = new Randomize();

        questions = top.getQuestions(topic);
        answer = top.getAnswers(topic);

        ran_ques = random.randomize(questions,answer);
        ran_answ = random.getRandomAnsers();

        list = new ArrayList<String>();
        String q = "";
        String a = "";

        for(int x  = 0; x < ran_ques.length;x++)
        {
            q = ran_ques[x];

            a = ran_answ[x];

            list.add(q);
            list.add(a);
        }

        adapter = new ArrayAdapter<String>(Questions.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);


       /* lv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
