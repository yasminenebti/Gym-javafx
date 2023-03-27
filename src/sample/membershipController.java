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

public class membershipController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;

    @FXML
    private TextField startDate;


    @FXML
    private Button btn_add;

    @FXML
    private TableColumn<membershipModel, String> coach;

    @FXML
    private TableColumn<membershipModel, String> idEnd;

    @FXML
    private TableColumn<membershipModel, Integer> idMc;

    @FXML
    private TableColumn<membershipModel, String> idStart;

    @FXML
    private TableColumn<membershipModel, String> member;

    @FXML
    private TextField endDate;

    @FXML
    private TableView<membershipModel> table_membership;

    @FXML
    private TextField txt_coach;

    @FXML
    private TextField txt_mmbr;

    @FXML
    private TextField txt_search;
    public ObservableList<membershipModel> data = FXCollections.observableArrayList();

    @FXML
    void addMembership(MouseEvent event) throws SQLException {
        String idMb = txt_mmbr.getText();
        String idCo = txt_coach.getText();
        String start_date = startDate.getText();
        String end_date = endDate.getText();
        String rq = "insert into membership(start_date,end_date,idCo,idMb) values(?,?,?,?)";
        st = cnx.prepareStatement(rq);
        st.setString(1,start_date);
        st.setString(2,end_date);
        st.setString(3,idCo);
        st.setString(4,idMb);
        st.execute();
        txt_mmbr.setText("");
        txt_coach.setText("");
        startDate.setText("");
        endDate.setText("");
        txt_search.setText("");
        showMembership();

    }

    @FXML
    void clickSession(MouseEvent event) {

    }

    @FXML
    void searchMembership(MouseEvent event) throws SQLException {
        String rq = "select idMc, start_date , end_date , idCo , idMb from membership where idMc ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        if (result.next()){
            txt_coach.setText(result.getString("idCo"));
            txt_mmbr.setText(result.getString("idMb"));
        }

    }
    public void showMembership() throws SQLException {
        table_membership.getItems().clear();
        String rq = "SELECT * FROM membership";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        while (result.next()){
            data.add(new membershipModel(result.getInt("idMc"),result.getString("start_date"),result.getString("start_date"),result.getString("idCo"),result.getString("idMb")));
        }
        idMc.setCellValueFactory(new PropertyValueFactory<membershipModel,Integer>("idMc"));
        idStart.setCellValueFactory(new PropertyValueFactory<membershipModel,String>("start_date"));
        idEnd.setCellValueFactory(new PropertyValueFactory<membershipModel,String>("end_date"));
        coach.setCellValueFactory(new PropertyValueFactory<membershipModel,String>("idCo"));
        member.setCellValueFactory(new PropertyValueFactory<membershipModel,String>("idMb"));
        table_membership.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=base.connDB();
        try {
            showMembership();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
