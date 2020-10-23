package Server;

public class Question {

    String content;

    String[] abcd = new String[4];

    String answer;

    public Question(String content, String a,String b,String c,String d, String answer) {
        this.content = content;
        this.abcd[0] = a;
        this.abcd[1] = b;
        this.abcd[2] = c;
        this.abcd[3] = d;
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAbcd() {
        return abcd;
    }

    public void setAbcd(String[] abcd) {
        this.abcd = abcd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
