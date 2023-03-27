package sample;

import com.mysql.cj.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class sessionController implements Initializable {
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
    private TableColumn<sessionModel, String> duration;

    @FXML
    private TableColumn<sessionModel, Integer> id;

    @FXML
    private TableColumn<sessionModel, String> name;


    @FXML
    private TableView<sessionModel> table_session;

    @FXML
    private TextField txt_dur;

    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_search;

    public ObservableList<sessionModel> data = FXCollections.observableArrayList();

    @FXML
    void addSession(MouseEvent event) throws SQLException {
        String nameA = txt_name.getText();
        String Duration = txt_dur.getText();
        String rq = "insert into Activity(nameA,duration) values(?,?)";
        st = cnx.prepareStatement(rq);
        st.setString(1,nameA);
        st.setString(2,Duration);
        st.execute();
        txt_name.setText("");
        txt_dur.setText("");
        txt_search.setText("");
        showSession();

    }
    @FXML
    void clickSession(MouseEvent event) throws SQLException {
        sessionModel session = table_session.getSelectionModel().getSelectedItem();
        String rq = "SELECT * FROM activity where idA=?";
        st = cnx.prepareStatement(rq);
        st.setInt(1,session.getIdA());
        result=st.executeQuery();
        if (result.next()){
            txt_name.setText(result.getString("nameA"));
            txt_dur.setText(result.getString("duration"));
            txt_search.setText(result.getString("idA"));
        }

    }

    @FXML
    void deleteSession(MouseEvent event) throws SQLException {
        String rq = "delete from Activity where idA ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        st.executeUpdate();
        txt_name.setText("");
        txt_dur.setText("");
        txt_search.setText("");
        showSession();
    }

    @FXML
    void modifySession(MouseEvent event) throws SQLException {
        String nameA = txt_name.getText();
        String Duration = txt_dur.getText();
        String rq = "update Activity set nameA=? ,duration=? where idA ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        st.setString(1,nameA);
        st.setString(2,Duration);
        st.executeUpdate();
        txt_name.setText("");
        txt_dur.setText("");
        txt_search.setText("");
        showSession();


    }
    @FXML
    void searchSession(MouseEvent event) throws SQLException {
        table_session.getItems().clear();
        String rq = "select idA, nameA , duration from Activity where idA ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        if (result.next()){
            txt_name.setText(result.getString("nameA"));
            txt_dur.setText(result.getString("duration"));
        }

    }
    public void showSession() throws SQLException {
        table_session.getItems().clear();
        String rq = "SELECT * FROM activity";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        while (result.next()){
            data.add(new sessionModel(result.getInt("idA"),result.getString("nameA"),
                    result.getString("Duration")));
        }
        id.setCellValueFactory(new PropertyValueFactory<sessionModel,Integer>("idA"));
        name.setCellValueFactory(new PropertyValueFactory<sessionModel,String>("nameA"));
        duration.setCellValueFactory(new PropertyValueFactory<sessionModel,String>("Duration"));
        table_session.setItems(data);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=base.connDB();
        try {
            showSession();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
