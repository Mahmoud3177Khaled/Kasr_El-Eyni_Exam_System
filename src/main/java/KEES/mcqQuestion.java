package KEES;

public class mcqQuestion {
    private int id;
    private String qtext;
    private String c1; 
    private String c2; 
    private String c3; 
    private String c4; 
    private String topic;
    private String ans;
    private int difficulty;

    public mcqQuestion(int id, String qtext, String c1, String c2, String c3, String c4, String topic, String ans, int difficulty) {
        this.ans = ans;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.id = id;
        this.qtext = qtext;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public mcqQuestion(String qtext, String c1, String c2, String c3, String c4, String topic, String ans, int difficulty) {
        this.ans = ans;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.id = 0;
        this.qtext = qtext;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
