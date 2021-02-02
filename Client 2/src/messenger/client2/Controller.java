package messenger.client2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import messenger.client2.DataSource.MessageString;
import messenger.client2.DataSource.TextString;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {
    private ObservableList<MessageString> texts;
    private ObservableList<String> displayTexts;

    @FXML
    private ListView listView;
    @FXML
    private TextArea textArea;
    @FXML
    private Button sendButton;

    public void initialize(){
        listView.setItems(displayTexts= TextString.getInstance().getTexts());
    }

    public void setTexts(ObservableList<MessageString> texts) {
        this.texts = texts;
    }

    @FXML
    public void handleSendButton(){
        String message=textArea.getText();
        try(Socket socket=new Socket("localhost",5000)){
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            pw.println(message);
            MessageString messageString=new MessageString(true,message);
            TextString.getInstance().addTextString(messageString);
            textArea.clear();
        }catch(IOException e){
            System.out.println("Could not connect to the server...." + e.getMessage());
        }
    }
}