package za.co.codetribe.quizzionarev3.BackgroundTask;

/**
 * Created by Codetribe on 9/29/2016.
 */

public class Topic
{
    private String topic;

    //Movies Q&A
    private String[] m_questions = {"M1_Q","M2_Q","M3_Q","M4_Q","M5_Q","M6_Q","M7_Q","M8_Q","M9_Q","M10_Q"};
    private String[] m_answers = {"M1_A","M2_A","M3_A","M4_A","M5_A","M6_A","M7_A","M8_A","M9_A","M10_A"};

    //Cars Q&A
    private String[] c_questions = {"C1_Q","C2_Q","C3_Q","C4_Q","C5_Q","C6_Q","C7_Q","C8_Q","C9_Q","C10_Q"};
    private String[] c_answers = {"C1_A","C2_A","C3_A","C4_A","C5_A","C6_A","C7_A","C8_A","C9_A","C10_A"};

    //Tech Q&A
    private String[] t_questions = {"T1_Q","T2_Q","T3_Q","T4_Q","T5_Q","T6_Q","T7_Q","T8_Q","T9_Q","T10_Q"};
    private String[] t_answers = {"T1_A","T2_A","T3_A","T4_A","T5_A","T6_A","T7_A","T8_A","T9_A","T10_A"};

    public Topic()
    {

    }

    public String[] getQuestions(String topic)
    {
        this.topic = topic;

        if(this.topic.equalsIgnoreCase("movies"))
        {
            return m_questions;
        }
        else if(this.topic.equalsIgnoreCase("cars"))
        {
            return c_questions;
        }
        else if(this.topic.equalsIgnoreCase("tech"))
        {
            return t_questions;
        }

        return null;
    }

    public String[] getAnswers(String topic)
    {
        this.topic = topic;

        if(this.topic.equalsIgnoreCase("movies"))
        {
            return m_answers;
        }
        else if(this.topic.equalsIgnoreCase("cars"))
        {
            return c_answers;
        }
        else if(this.topic.equalsIgnoreCase("tech"))
        {
            return t_answers;
        }

        return null;
    }
}
