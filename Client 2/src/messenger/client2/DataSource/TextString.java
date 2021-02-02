package messenger.client2.DataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class TextString {
    private static TextString instance=new TextString();
    private static String filename="TextString.txt";

    private ObservableList<MessageString> texts;
    private ObservableList<String> displayTexts;

    public static TextString getInstance(){
        return instance;
    }

    public void addTextString(MessageString message){
        texts.add(message);
        displayTexts.add(message.getSenderOrReceiver() + message.getText());
    }

    public ObservableList<String> getTexts() {
        displayTexts= FXCollections.observableArrayList();
        String displayText;
        Iterator<MessageString> iter=texts.iterator();
        while(iter.hasNext()){
            MessageString messageString=iter.next();
            displayText= messageString.getSenderOrReceiver() + messageString.getText();
            displayTexts.add(displayText);
        }
        return displayTexts;
    }

    public void loadTextString() throws IOException{
        texts= FXCollections.observableArrayList();
        Path path= Paths.get(filename);
        String inputText;
        BufferedReader br= Files.newBufferedReader(path);
        try{
            while((inputText=br.readLine())!=null){
                String[] textPieces= inputText.split(":",2);
                boolean senderCheck;
                if(textPieces[0].indexOf("Me")==0)
                    senderCheck=true;
                else
                    senderCheck=false;
                String textMessage=textPieces[1];
                MessageString newString=new MessageString(senderCheck,textMessage);
                texts.add(newString);
            }
        }finally {
            if(br!=null)
                br.close();
        }
    }

    public void storeTextString() throws IOException {
        Path path= Paths.get(filename);
        BufferedWriter bw= Files.newBufferedWriter(path);

        try{
            Iterator<MessageString> iter=texts.iterator();
            while(iter.hasNext()){
                MessageString messageString=iter.next();
                bw.write(String.format("%s%s",
                        messageString.getSenderOrReceiver(),
                        messageString.getText()));
                bw.newLine();
            }
        }finally {
            if(bw!=null)
                bw.close();
        }
    }
}
