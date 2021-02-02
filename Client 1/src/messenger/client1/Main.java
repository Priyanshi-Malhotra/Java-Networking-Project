package messenger.client1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import messenger.client1.DataSource.TextString;
import messenger.client1.Server.Server;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Client 1");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        new Server().start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        try{
            TextString.getInstance().loadTextString();
        }catch(IOException e){
            System.out.println("Could not load text messages...." + e.getMessage());
        }
    }

    @Override
    public void stop() throws Exception {
        try{
            TextString.getInstance().storeTextString();
        }catch(IOException e){
            System.out.println("Could not store text messages...." + e.getMessage());
        }
    }
}
