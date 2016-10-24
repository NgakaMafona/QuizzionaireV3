package za.co.codetribe.quizzionarev3.BackgroundTask;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Codetribe on 9/29/2016.
 */

public class Randomize
{
    private String[] random_Qs = new String[5];
    private String[] random_Ans = new String[5];

    private String[] selected_answers = new String[5];

    public Randomize()
    {

    }

    public String[] randomize(String topic,String[] questions, String[] answers)
    {
        Topic top = new Topic();
        String[] index = top.getIndex();

        //count for array length
        Collections.shuffle(Arrays.asList(index));

        for(int x = 0; x < 5;x++)
        {
           random_Qs[x] = top.getQuestion(Integer.parseInt(index[x]),topic);
           random_Ans[x] = top.getAnswer(Integer.parseInt(index[x]),topic);

            selected_answers[x] = ""+index[x];
        }

        return random_Qs;
    }

    public String[] getRandomAnswers()
    {
        return random_Ans;
    }

    public String[] otherRandomAnswers(String topic)
    {
        Topic top = new Topic();
        String[] index = top.getIndex();
        String[] a = new String[3];

        String retuened_index = "";
        String selected_index = "";


        //count for array length
        Collections.shuffle(Arrays.asList(index));
        for(int x = 0; x < 3;x++)
        {
            retuened_index = index[x];
            selected_index = selected_answers[x];

            if(retuened_index.equals(selected_index))
            {
                a[x] = top.getAnswer(Integer.parseInt(index[x+1]),topic);
            }
            else
            {
                a[x] = top.getAnswer(Integer.parseInt(index[x]),topic);
            }

        }

        return a;
    }
}
