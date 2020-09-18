import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class TheGUI extends Application{

    public static void main(String[] args) throws RemoteException, NotBoundException {
        launch(args); // method inside Application class, that setups the program as an Javafx application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {  //method start
        primaryStage.setTitle("GUI"); // Title of the window

        TextField input = new TextField(); // make new TextField object called "input" and initialize it
        Button sendButton = new Button(); // make new Button object called "sendButton" and initialise it

        sendButton.setText("send"); // set button name to "send"

        AnchorPane layout = new AnchorPane(); // create a new AnchorPane object called "layout" for the GUI

        HBox hbox = new HBox(5, input, sendButton); // create a new HBox object, that lays out its children in a single horizontal row
        layout.getChildren().addAll(hbox); // add input and sendButton to layout pane
        AnchorPane.setTopAnchor(hbox, 10d); // anchor hbox to the top

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { // EventHandler for when send button pressed
            public void handle(ActionEvent e)
            {
                String message = input.getText(); // text from input TextField gets saved to a String variable called message
                input.setText(""); // clear text from input TextField

            }
        };

        sendButton.setOnAction(event); // When button clicked do the Action event

        Scene scene = new Scene(layout, 300, 250); // new Scene object called "scene" which determines the scale of the GUI
        primaryStage.setScene(scene); // use the Scene object which we just created as our window
        primaryStage.show(); // display to user
    }
}
