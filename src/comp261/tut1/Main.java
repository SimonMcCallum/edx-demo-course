package comp261.tut1;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Graph graph;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // load the lanugage file - supported "en" English and "mi" Maori
        Locale locale = new Locale("mi", "NZ");
        ResourceBundle bundle = ResourceBundle.getBundle("comp261/tut1/strings", locale);
        
        // load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MapView.fxml"),bundle);
        Parent root = loader.load();

        primaryStage.setTitle(bundle.getString("title")); //uses the localisation for the string. 
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });

        
        graph = new Graph(new File("D:/git/comp261/2022/code/Tutorials/Tut-1/data/node.csv"), new File("D:/git/comp261/2022/code/Tutorials/Tut-1/data/edge.csv"));
        //           loader.getController().drawGraph();  // we have to cast to get to the Controller code
        ((GraphController)loader.getController()).drawGraph();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
