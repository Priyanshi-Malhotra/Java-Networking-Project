package messenger.client1.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread{
    @Override
    public void run(){
        try(ServerSocket serverSocket=new ServerSocket(5000)){
            while(true){
                new AddReply(serverSocket.accept()).start();
            }
        }catch(IOException e){
            System.out.println("Could not connect to the endpoint...." + e.getMessage());
        }
    }
}
