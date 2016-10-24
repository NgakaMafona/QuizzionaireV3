package za.co.codetribe.quizzionarev3.BackgroundTask;

/**
 * Created by Codetribe on 9/29/2016.
 */

public class Topic
{
    private String topic;

    private String[] index = {"0","1","2","3","4","5","6","7","8","9"};

    //Movies Q&A
    private String[] m_questions = {"What's the highest grossing movie of all time?",
                                    "Whats the most expensive production movie?",
                                    "Which universe does Deadpool belong to?",
                                    "How many Harry Potter movies are there?",
                                    "Which movie trilogy stared Keano Reeves as Neo?",
                                    "Which monster terrorised Tokyo?",
                                    "Which creature was Dracula?",
                                    "Which Gorge Miller movie won numerous awards?",
                                    "Which anime adaptation sucked way too much?",
                                    "Whats the most successful video game movie to date?"};
    private String[] m_answers = {"Avatar",
                                  "Pirates of the Caribbean: At Worlds End",
                                  "Marvel",
                                  "7",
                                  "The Matrix trilogy",
                                  "Godzilla",
                                  "Vampire",
                                  "Mad Max: Fury Road",
                                  "Dragon Ball Z",
                                  "Warcraft"};

    //Cars Q&A
    private String[] c_questions = {"Whats the fastest production car?",
                                    "Which car was the first to be called a hyper car?",
                                    "Which car is considered to have the most fuel economical V8?",
                                    "Which group does Jaguar belong to?",
                                    "Whats the smallest car ever manufactured?",
                                    "Which brand is best for rally racing?",
                                    "Which manufacturer only produces construction truck?",
                                    "Which car is refereed to as Godzilla?",
                                    "Which car did Victoria Beckem be a design consultant for?",
                                    "Which manufacturer ony produces electric cars?"};
    private String[] c_answers =   {"Hennesy Vennom GT",
                                    "Pagani Huayra",
                                    "Corvette C7",
                                    "Tata",
                                    "Peele 50",
                                    "Subaru",
                                    "CAT",
                                    "Nissan GTR",
                                    "Range Rover E-Vogue",
                                    "Tesla"};

    //Tech Q&A
    private String[] t_questions = {"Which company made the first mag-lev hoverboard?",
                                    "Which company has the widely used Operating System?",
                                    "Which company produces slim gaming laptops?",
                                    "Which company uses the slogan 'Think different'?",
                                    "Which company made the first ever wireless phone?",
                                    "Which giant tech company is known to connect friends and family?",
                                    "Which company has the best search engine?",
                                    "Which gaming console first came 4K support?",
                                    "Whats the best gaming laptop?",
                                    "Which company creates processors for computers?"};
    private String[] t_answers = {"Lexus",
                                    "Microsoft",
                                    "Razor",
                                    "Apple",
                                    "Motorola",
                                    "Facebook",
                                    "Google",
                                    "Sony Play Station 4",
                                    "Alienware",
                                    "Intel"};

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

    public String[] getIndex()
    {
        return index;
    }

    //get single questions based on recieved random index
    public String getQuestion(int i, String topic)
    {
        String q = "";

        if(topic.equalsIgnoreCase("movies"))
        {
            q = m_questions[i];
        }
        else if(topic.equalsIgnoreCase("cars"))
        {
            q = c_questions[i];
        }
        else if(topic.equalsIgnoreCase("tech"))
        {
            q = t_questions[i];
        }

        return q;
    }

    public String getAnswer(int i, String topic)
    {
        String a = "";

        if(topic.equalsIgnoreCase("movies"))
        {
            a = m_answers[i];
        }
        else if(topic.equalsIgnoreCase("cars"))
        {
            a = c_answers[i];
        }
        else if(topic.equalsIgnoreCase("tech"))
        {
            a = t_answers[i];
        }

        return a;
    }
}
