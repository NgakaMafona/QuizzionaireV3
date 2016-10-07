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

    public Randomize()
    {

    }

    /*public String[] randomize(String[] questions, String[] answers)
    {
        //count for array length
        int count = 0;

        int random_number = 0;

        Random random = new Random();

        for (int x = 0; x < questions.length;x++)
        {
            count+=1;

            Log.d("Count : ", ""+count);
        }

        for (int x = 0; x < count; x++)
        {

            Log.d("x : ", ""+x);

            random_number = (random.nextInt(count - 1) + 1)+1;

            Log.d("Random Number : ", ""+random_number);

            if(x <= 4)
            {
                Log.d("Index : " + x, "inserting");

                random_Qs[x] = questions[random_number];

                Log.d("Index : " + x, "inserting");

                random_Ans[x] = answers[random_number];
            }
            else
                break;

        }

        return random_Qs;
    }*/

    public String[] randomize(String[] questions, String[] answers)
    {
        //count for array length

        Collections.shuffle(Arrays.asList(questions,answers));

        for(int x = 0; x < 5;x++)
        {

            random_Qs[x] = questions[x];
            random_Ans[x] = answers[x];
        }

        return random_Qs;
    }

    public String[] getRandomAnsers()
    {

        return random_Ans;
    }
}
