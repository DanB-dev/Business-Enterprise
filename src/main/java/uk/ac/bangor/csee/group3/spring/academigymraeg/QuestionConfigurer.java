package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.Random;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Question;

public class QuestionConfigurer {
    //Design Flaws:
    //This configurer doesnt call nouns, translations and genders from the already established data fields,
    //but instead relies on internally set arrays.
    //This could be swapped in the future.

    ///Establish Possible Questions Data;
    //Questions
    public String[] queries = {
        "What is the English noun of: ",
        "What is the Welsh noun of: ",
        "What is the gender of: "
    };
    //All of the following must have the same "pointer" value when referenced to maintain aswer accuracy
    //Answers in English
    public String[] answersWel = {
        "Tatws. ",
        "Cyfrifiadur. ",
        "Brechdan. ",
        "Myfyriwr .",
        "Myfyrwraig ."
    };
    //Ansers in welsh
    public String[] answersEng = {
        "Potato. ",
        "Computer. ",
        "Sandwich. ",
        "Student. ",
        "Student. "
    };
    //Answers Gender
    public String[] answersGen = {
        "Feminine. ",
        "Masculine. ",
        "Feminine .",
        "Masculine .",
        "Feminine ."
    };


    ///

    //Misc variables
    public int pointerVal = 1;
    public String state = "null";

    //Generate questionPointerVal
    //If 0 Assigner state == English
    //If 1 Assigner state == Welsh
    
    //Contextual Answer to Question Assigner
    public void Assigner(){
        switch(state){
            //Welsh question with English Answer:
            case "WelshQ":

                break;

            //English question with Welsh Answer:
            case "EnglishQ":

                break;

        }
}

    //HARDCODED TEST
    //Question output = new Question(queries[pointerVal], );
    //Set up question
    Question output = new Question(queries[pointerVal], 
                                answersEng[pointerVal], 
                                answersWel[pointerVal]
                                
                                );


}
