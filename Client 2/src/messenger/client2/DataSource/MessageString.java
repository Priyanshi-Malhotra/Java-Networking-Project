package messenger.client2.DataSource;

public class MessageString {
    private String senderOrReceiver; // SENDER OR RECEIVER
    private String text;

    public MessageString(boolean senderCheck, String text) {
        if(senderCheck)
            this.senderOrReceiver = "Me :";
        else
            this.senderOrReceiver = "Yvette :";
        this.text = text;
    }

    public String getSenderOrReceiver() {
        return senderOrReceiver;
    }

    public String getText() {
        return text;
    }
}
