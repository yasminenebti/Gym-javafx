package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class coachController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_dlt;

    @FXML
    private Button btn_mod;

    @FXML
    private TableColumn<coachModel, String> email;

    @FXML
    private TableColumn<coachModel, Integer> id;

    @FXML
    private TableColumn<coachModel, String> name;

    @FXML
    private TableColumn<coachModel, Integer> phone;

    @FXML
    private TableView<coachModel> table_coach;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_search;

    public ObservableList data2 = FXCollections.observableArrayList();

    @FXML
    void addCoach(MouseEvent event) throws SQLException {
        String nameC = txt_name.getText();
        String phoneC = txt_phone.getText();
        String mailC = txt_email.getText();
        String rq = "insert into Coach(nameC,phoneC,mailC) values(?,?,?)";
        st = cnx.prepareStatement(rq);
        st.setString(1,nameC);
        st.setString(2,phoneC);
        st.setString(3,mailC);
        st.execute();
        txt_name.setText("");
        txt_phone.setText("");
        txt_email.setText("");
        txt_search.setText("");
        showCoach();
    }

    @FXML
    void deleteCoach(MouseEvent event) throws SQLException {
        String rq = "delete from coach where idC ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        st.executeUpdate();
        txt_name.setText("");
        txt_phone.setText("");
        txt_email.setText("");
        txt_search.setText("");
        showCoach();

    }

    @FXML
    void modifyCoach(MouseEvent event) throws SQLException {
        String nameC = txt_name.getText();
        String phoneC = txt_phone.getText();
        String mailC = txt_email.getText();
        String rq = "update coach set nameC=? ,phoneC=? , mailC=? where idC ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        st.setString(1,nameC);
        st.setString(2,phoneC);
        st.setString(3,mailC);
        st.executeUpdate();
        txt_name.setText("");
        txt_phone.setText("");
        txt_email.setText("");
        txt_search.setText("");
        showCoach();

    }
    @FXML
    void displayCoach(MouseEvent event) throws SQLException {
        coachModel coach = table_coach.getSelectionModel().getSelectedItem();
        String rq = "SELECT * FROM coach where idC=?";
        st = cnx.prepareStatement(rq);
        st.setInt(1,coach.getIdC());
        result=st.executeQuery();
        if (result.next()){
            txt_name.setText(result.getString("nameC"));
            txt_phone.setText(result.getString("phoneC"));
            txt_email.setText(result.getString("mailC"));
            txt_search.setText(result.getString("idC"));
        }
    }

    @FXML
    void searchCoach(MouseEvent event) throws SQLException {
        table_coach.getItems().clear();
        String rq = "select idC, nameC , phoneC , mailC from Coach where idC ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        if (result.next()){
            txt_name.setText(result.getString("nameC"));
            txt_phone.setText(result.getString("phoneC"));
            txt_email.setText(result.getString("mailC"));

        }

    }
    public void showCoach() throws SQLException {
        table_coach.getItems().clear();
        String rq = "SELECT * FROM coach";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        while (result.next()){
            data2.add(new coachModel(result.getInt("idC"),result.getString("nameC"),result.getString("phoneC"),result.getString("mailC")));
        }
        id.setCellValueFactory(new PropertyValueFactory<coachModel,Integer>("idC"));
        name.setCellValueFactory(new PropertyValueFactory<coachModel,String>("nameC"));
        phone.setCellValueFactory(new PropertyValueFactory<coachModel,Integer>("phoneC"));
        email.setCellValueFactory(new PropertyValueFactory<coachModel,String>("mailC"));


        table_coach.setItems(data2);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=base.connDB();
        try {
            showCoach();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
