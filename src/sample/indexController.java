package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class indexController implements Initializable {
    private Parent fxml;
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private Pane root;



    @FXML
    void coach(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("coach.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);

    }

    @FXML
    void member(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("member.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);

    }

    @FXML
    void membership(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("membership.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);

    }

    @FXML
    void session(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("session.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxml);

    }
    @FXML
    void index(MouseEvent event) throws IOException {
        fxml = FXMLLoader.load(getClass().getResource("index.fxml"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=base.connDB();


    }
}
