package messenger.client2.Server;

import messenger.client2.DataSource.MessageString;
import messenger.client2.DataSource.TextString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AddReply extends Thread{
    private Socket socket;

    public AddReply(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String reply= br.readLine().trim();
            System.out.println("Received : " + reply);
            MessageString messageString=new MessageString(false,reply);
            TextString.getInstance().addTextString(messageString);
        } catch (IOException e) {
            System.out.println("Exception while adding reply...." + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Could not close socket connection...." + e.getMessage());
            }
        }
    }
}
