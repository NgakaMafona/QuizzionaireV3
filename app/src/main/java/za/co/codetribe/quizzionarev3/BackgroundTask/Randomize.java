package za.co.codetribe.quizzionarev3.BackgroundTask;

import java.util.Random;

/**
 * Created by Codetribe on 9/29/2016.
 */

public class Randomize
{
    private String[] random_Qs;
    private String[] random_Ans;

    public Randomize()
    {

    }

    public String[] randomize(String[] questions, String[] answers)
    {
        //count for array length
        int count = 0;

        int random_number = 0;

        Random random = new Random();

        for (int x = 0; x < questions.length;x++)
        {
            count+=1;
        }

        for (int x = 0; x < count; x++)
        {
            random_number = (random.nextInt(count - 1) + 1)+1;

            if(x <= 4)
            {
                random_Qs[x] = questions[random_number];
                random_Ans[x] = answers[random_number];
            }
            else
                break;

        }

        return random_Qs;
    }

    public String[] getRandomAnsers()
    {
        return random_Ans;
    }
}
