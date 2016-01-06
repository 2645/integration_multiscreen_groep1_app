package be.ehb.funinthequeue.model;

/**
 * Created by Clara on 16/12/2015.
 */
public class Question {

    private int ID;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String answer;

    public Question() {
        ID = 0;
        question = "";
        optionA = "";
        optionB = "";
        optionC = "";
        answer = "";
    }

    public Question(int ID, String question, String optionA, String optionB, String optionC, String answer) {
        this.answer = answer;
        this.ID = ID;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.question = question;
    }

    public Question(String question, String optionA, String optionB, String optionC, String answer) {
        this.ID = 0;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.answer = answer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
