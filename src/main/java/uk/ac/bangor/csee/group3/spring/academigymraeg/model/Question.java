package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

public class Question {
    //Set up variables
    private String phrase;

    private String translateable;

    private String answer;

    //Constructor:
    public Question(String inPhrase,String inTranslateable, String inAnswer){
        phrase = inPhrase;
        translateable = inTranslateable;
        answer = inAnswer;
    } 

    //Set up setters
    public void setPhrase(String phrase){
        this.phrase = phrase;
    }

    public void setTransalateable(String translateable){
        this.translateable = translateable;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    //Set up getters
    public String getPhrase(){
        return phrase;
    }

    public String getTranslateable(){
        return translateable;
    }

    public String getAnswer(){
        return answer;
    }

    //Get Whole to test
    public String getWhole(){
        return phrase + " "+ translateable + "is " + answer ;
    }

}
