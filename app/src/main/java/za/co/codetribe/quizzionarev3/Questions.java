package za.co.codetribe.quizzionarev3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    RadioGroup group;
    RadioButton rb;
    TextView header_title;

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

    String correct_answer="";

    int points = 0;
    int count_ansewerd = 0;
    int correct_count  = 0;

    String test_selected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        header_title = (TextView) findViewById(R.id.topic);

        lv = (ListView) findViewById(R.id.question_list);
        group = (RadioGroup) findViewById(R.id.grp_answers);

        Animation main_button_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_up);
        lv.setAnimation(main_button_animation);

        Intent i = getIntent();
        topic = i.getStringExtra("title");

        header_title.setText(topic);

        top = new Topic();
        random = new Randomize();

        questions = top.getQuestions(topic);
        answer = top.getAnswers(topic);

        ran_ques = random.randomize(topic,questions,answer);
        ran_answ = random.getRandomAnswers();

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
                questionID = i;

               // Toast.makeText(Questions.this,"ID = " + questionID,Toast.LENGTH_LONG).show();

               showRadioButtonDialog();

               // lv.getChildAt(i).setEnabled(false);



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

        correct_answer = sp.getString("Answer Q " + questionID,null);

        //Toast.makeText(Questions.this,correct_answer,Toast.LENGTH_LONG).show();

        answer_radio_list = new String[3];

        answer_radio_list[0] = correct_answer;

        for(int x = 1; x < other_answers.length;x++)
        {

            answer_radio_list[x] = other_answers[x];

        }

        //Shuffle answers
        Collections.shuffle(Arrays.asList(answer_radio_list));

        // custom dialog
        final Dialog dialog = new Dialog(Questions.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.rad_answers);

        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.grp_answers);


        for(int i=0;i<answer_radio_list.length;i++)
        {
            rb = new RadioButton(Questions.this); // dynamically creating RadioButton and adding to RadioGroup.

            rb.setId(i+i);

            Log.d("Created ID : ",""+rb.getId());

            String temp = answer_radio_list[i];

            rb.setText(temp);
            rg.addView(rb);
        }

        dialog.show();
        dialog.setCanceledOnTouchOutside(false);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton rad = (RadioButton) radioGroup.findViewById(i);

                test_selected = rad.getText().toString();

                if(test_selected.equalsIgnoreCase(correct_answer))
                {
                    points +=2;
                    correct_count+=1;
                    count_ansewerd+=1;
                    lv.getChildAt(questionID).setEnabled(false);
                }
                else
                {
                    points+=0;
                    count_ansewerd+=1;
                    lv.getChildAt(questionID).setEnabled(false);

                }

                Handler handler = new Handler();

                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        dialog.dismiss();
                    }
                },1000);

                if(count_ansewerd == 5)
                {
                    int wrong = 5 - correct_count;

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Questions.this);
                    builder.setTitle("Results");
                    builder.setMessage("You got " + correct_count + " correct answers \n and " + wrong + " incorrect answers");
                    builder.create();
                    builder.show();

                    builder.setOnDismissListener(new DialogInterface.OnDismissListener()
                    {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface)
                        {
                            //builder.

                            Intent i = new Intent(Questions.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });

                }

                Toast.makeText(Questions.this,"answered : " + count_ansewerd,Toast.LENGTH_LONG).show();

            }
        });

    }

}
