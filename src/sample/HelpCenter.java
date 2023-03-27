package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));

        TextArea area = new TextArea();
        TextField field = new TextField();

        VBox vbox = new VBox(area,field);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        root.setCenter(vbox);

        Scene scene = new Scene(root,500,200);
        stage.setTitle("Help Center");

        stage.setScene(scene);
        stage.show();

    }
}
