package za.co.codetribe.quizzionarev3;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    SharedPreferences answer_pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.question_list);

        Animation main_button_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);
        lv.setAnimation(main_button_animation);

        Intent i = getIntent();
        topic = i.getStringExtra("title");

        top = new Topic();
        random = new Randomize();

        questions = top.getQuestions(topic);
        answer = top.getAnswers(topic);

        ran_ques = random.randomize(topic,questions,answer);
        ran_answ = random.getRandomAnsers();

        list = new ArrayList<String>();
        String q = "";
        String a = "";

        answer_pref = getSharedPreferences("answers",0);
        edit = answer_pref.edit();

        for(int x  = 0; x < ran_ques.length;x++)
        {
            q = ran_ques[x];

            a = ran_answ[x];

            edit.putString("Answer Q " + x,a);

            edit.commit();

            list.add(q);
        }

        adapter = new ArrayAdapter<String>(Questions.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                RadioGroup answer_group = new RadioGroup(Questions.this);

                AlertDialog.Builder alert = new AlertDialog.Builder(Questions.this);
                alert.setTitle("Try your luck");

                RadioButton rb = new RadioButton(Questions.this);
                rb.setText("Try this one");
                answer_group.addView(rb);

                alert.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Reset", Snackbar.LENGTH_LONG).setAction("Here", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        answer_pref.edit();
                        edit.clear();
                        edit.commit();

                        Intent i = new Intent(Questions.this,MainActivity.class);
                        startActivity(i);
                    }
                }).show();
            }
        });
    }


}
