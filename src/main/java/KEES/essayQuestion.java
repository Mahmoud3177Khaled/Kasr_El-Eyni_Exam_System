package KEES;

public class essayQuestion {
    private int id;
    private String qtext; 
    private String topic;
    private String ans;
    private int difficulty;

    public essayQuestion(int id, String qtext, String topic, String ans, int difficulty) {
        this.ans = ans;
        this.difficulty = difficulty;
        this.id = id;
        this.qtext = qtext;
        this.topic = topic;
    }

    public essayQuestion(String qtext, String topic, String ans, int difficulty) {
        this.ans = ans;
        this.difficulty = difficulty;
        this.id = 0;
        this.qtext = qtext;
        this.topic = topic;
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
