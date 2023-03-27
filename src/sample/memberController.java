package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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

public class memberController implements Initializable {
    Connection cnx;
    public PreparedStatement st;
    public ResultSet result;


    @FXML
    private TableColumn<memberModel, String> emailM;

    @FXML
    private TableColumn<memberModel, Integer> idM;

    @FXML
    private TableColumn<memberModel, String> nameM;

    @FXML
    private TableColumn<memberModel, String> phoneM;

    @FXML
    private TableView<memberModel> table_member;

    @FXML
    private DatePicker txt_birth;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_search;

    public  ObservableList info = FXCollections.observableArrayList();


    @FXML
    void addMember(MouseEvent event) throws SQLException {
        String nameM = txt_name.getText();
        String phoneM = txt_phone.getText();
        String mailM = txt_email.getText();
        String rq = "insert into member(nameM,phoneM,mailM) values(?,?,?)";
        st = cnx.prepareStatement(rq);
        st.setString(1,nameM);
        st.setString(2,phoneM);
        st.setString(3,mailM);
        st.execute();
        txt_name.setText("");
        txt_phone.setText("");
        txt_email.setText("");
        txt_search.setText("");
        showMember();
    }



    @FXML
    void deleteMember(MouseEvent event) throws SQLException {
        String rq = "delete from member where idM ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        st.executeUpdate();
        txt_name.setText("");
        txt_phone.setText("");
        txt_email.setText("");
        txt_search.setText("");
        showMember();

    }

    @FXML
    void modifyMember(MouseEvent event) throws SQLException {
        String nameM = txt_name.getText();
        String phoneM = txt_phone.getText();
        String mailM = txt_email.getText();
        String rq = "update member set nameM=? ,phoneM=? , mailM=? where idM ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        st.setString(1,nameM);
        st.setString(2,phoneM);
        st.setString(3,mailM);
        st.executeUpdate();
        txt_name.setText("");
        txt_phone.setText("");
        txt_email.setText("");
        txt_search.setText("");
        showMember();

    }
    @FXML
    void displayMember(MouseEvent event) throws SQLException {
        memberModel member = table_member.getSelectionModel().getSelectedItem();
        String rq = "SELECT * FROM member where idM=?";
        st = cnx.prepareStatement(rq);
        st.setInt(1,member.getIdM());
        result=st.executeQuery();
        if (result.next()){
            txt_name.setText(result.getString("nameM"));
            txt_phone.setText(result.getString("phoneM"));
            txt_email.setText(result.getString("mailM"));
            txt_search.setText(result.getString("idM"));
        }

    }

    @FXML
    void searchMember(MouseEvent event) throws SQLException {
        table_member.getItems().clear();
        String rq = "select idM, nameM , phoneM , mailM from member where idM ='"+txt_search.getText()+"'";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        if (result.next()){
            txt_name.setText(result.getString("nameM"));
            txt_phone.setText(result.getString("phoneM"));
            txt_email.setText(result.getString("mailM"));

        }

    }
    public void showMember() throws SQLException {
        table_member.getItems().clear();
        String rq = "SELECT * FROM member";
        st = cnx.prepareStatement(rq);
        result= st.executeQuery();
        while(result.next()){
            info.add(new memberModel(result.getInt("idM"),result.getString("nameM"),result.getString("phoneM"),result.getString("mailM")));
        }
        idM.setCellValueFactory(new PropertyValueFactory<memberModel, Integer>("idM"));
        nameM.setCellValueFactory(new PropertyValueFactory<memberModel, String>("nameM"));
        phoneM.setCellValueFactory(new PropertyValueFactory<memberModel, String>("phoneM"));
        emailM.setCellValueFactory(new PropertyValueFactory<memberModel, String>("mailM"));
        table_member.setItems(info);


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnx=base.connDB();
        try {
            showMember();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
