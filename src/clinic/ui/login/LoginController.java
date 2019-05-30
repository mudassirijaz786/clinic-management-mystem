package clinic.ui.login;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField password;

    //@FXML
    //private JFXButton login;
    @FXML
    private JFXTextField username;

    @FXML
    private JFXComboBox<String> type;

    @FXML
    private Text errorMsg;

    public static String USERNAME;
    public static Integer ID;
    public static String TYPE;

    public boolean validateLogin() {
        if (username.getText().equals("")
                || password.getText().equals("")
                || type.getSelectionModel().getSelectedItem() == null) {
            errorMsg.setText("Error");
        } else {
            try {
                String sql = "select EID ,Username,Type,Password from tbl_Employee where Username='" + username.getText() + "' and Type='" + type.getSelectionModel().getSelectedItem() + "' and Password='" + password.getText() + "'";

                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);

                if (rs.next()) {
                    ID = rs.getInt("EID");
                    TYPE = rs.getString("Type");
                    USERNAME = rs.getString("Username");

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/dashboard/dashboard.fxml"));
                    Stage dashboardAdmin = new Stage();
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    dashboardAdmin.setScene(scene);
                    dashboardAdmin.show();
                    return true;
                } else {
                    errorMsg.setText("User Not Found");
                    return false;
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @FXML
    void loginActionPerfomed(ActionEvent event) {
        if (validateLogin()) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("doctor", "Admin", "Receptionist", "Store Assistent");
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void enterKeyPressedLogin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (validateLogin()) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
            }
        }
    }
}
