package ir.parto.crm.utils.transientObject;

import java.util.List;

public class ValidateObject {
    private String result;
    private List<String> messages;
    private int count;

    public ValidateObject(String result, List<String> messages, int count) {
        this.result = result;
        this.messages = messages;
        this.count = count;
    }

    public ValidateObject() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
