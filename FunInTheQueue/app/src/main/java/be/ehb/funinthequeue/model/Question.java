package be.ehb.funinthequeue.model;

/**
 * Created by Clara on 16/12/2015.
 */
public class Question {

    private int ID;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String answer;

    public Question() {
        ID = 0;
        question = "";
        option1 = "";
        option2 = "";
        option3 = "";
        answer = "";
    }

    public Question(int ID, String question, String option1, String option2, String option3, String answer) {
        this.answer = answer;
        this.ID = ID;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.question = question;
    }

    public Question(String question, String option1, String option2, String option3, String answer) {
        this.ID = 0;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
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

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
