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

import za.co.codetribe.quizzionarev3.BackgroundTask.Topic;

public class Questions extends AppCompatActivity
{
    String topic = "";

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    Topic top;

    String[] questions;
    String[] answer;

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

        questions = top.getQuestions(topic);
        answer = top.getAnswers(topic);
        
        list = new ArrayList<String>();
        String q = "";

        for(int x  = 0; x < questions.length;x++)
        {
            q = questions[x];

            list.add(q);
        }

        adapter = new ArrayAdapter<String>(Questions.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

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
