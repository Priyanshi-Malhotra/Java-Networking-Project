package messenger.client1.DataSource;

public class MessageString {
    private String senderOrReceiver; // SENDER OR RECEIVER
    private String text;

    public MessageString(boolean senderCheck, String text) {
        if(senderCheck)
            this.senderOrReceiver = "Me :";
        else
            this.senderOrReceiver = "John :";
        this.text = text;
    }

    public String getSenderOrReceiver() {
        return senderOrReceiver;
    }

    public String getText() {
        return text;
    }
}
