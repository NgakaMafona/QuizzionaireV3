package za.co.codetribe.quizzionarev3;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    int questionID = 0;

    String[] answer_radio_list;

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
                questionID = i +1;

               // Toast.makeText(Questions.this,"ID = " + questionID,Toast.LENGTH_LONG).show();

               showRadioButtonDialog();
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

    public void showRadioButtonDialog()
    {
        Randomize ran = new Randomize();

        String[] other_answers = ran.otherRandomAnswers(topic);

        SharedPreferences sp = getSharedPreferences("answers",0);

        String correct_answer = sp.getString("Answer Q " + questionID,null);

        Toast.makeText(Questions.this,correct_answer,Toast.LENGTH_LONG).show();

        answer_radio_list = new String[4];

        answer_radio_list[0] = correct_answer;

        for(int x = 1; x < other_answers.length;x++)
        {
            answer_radio_list[x] = other_answers[x];
        }

        Collections.shuffle(Arrays.asList(answer_radio_list));

        // custom dialog
        final Dialog dialog = new Dialog(Questions.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.rad_answers);

        List<String> stringList=new ArrayList<>();  // here is list

        for(int i=0;i<4;i++)
        {
            stringList.add("RadioButton " + (i + 1));
        }
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.grp_answers);


        for(int i=0;i<stringList.size();i++)
        {
            RadioButton rb = new RadioButton(Questions.this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(String.valueOf(answer_radio_list));
            rg.addView(rb);
        }

        dialog.show();

    }


}
