package clinic.ui.forms.Add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AddEmployeeController implements Initializable {

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXComboBox<String> type;

    @FXML
    private JFXTextField salary;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField CID;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private ToggleGroup gender = new ToggleGroup();

    @FXML
    private JFXTextField contactNo;

    @FXML
    private JFXButton addEmployee;

    @FXML
    void addEmployeeActionPerformed(ActionEvent event) {
        if (name.getText().equals("")
                || type.getSelectionModel().getSelectedItem() == null
                || address.getText().equals("")
                || age.getText().equals("")
                || CID.getText().equals("")
                || contactNo.getText().equals("")
                || salary.getText().equals("")
                || username.getText().equals("")
                || password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String gend;
                if (male.isSelected()) {
                    gend = "Male";
                } else {
                    gend = "Female";
                }
                String sql = "insert into tbl_Employee ( Name, Contact, Age,CID, Address, Type, Salary, Username, Password, Gender)values ('"
                        + name.getText() + "','"
                        + contactNo.getText() + "','"
                        + Integer.parseInt(age.getText()) + "','"
                        + Integer.parseInt(CID.getText()) + "','"
                        + address.getText() + "','"
                        + type.getSelectionModel().getSelectedItem() + "','"
                        + Float.parseFloat(salary.getText()) + "','"
                        + username.getText() + "','"
                        + password.getText() + "','"
                        + gend + "')";
                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");

                ((Stage) (((Node) (event.getSource())).getScene().getWindow())).close();
                System.gc();

            } catch (SQLException ex) {
                Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("doctor", "Admin", "Receptionist", "Store Assistent");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            male.setToggleGroup(gender);
            female.setToggleGroup(gender);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         ID.setText(Integer.toString(getAutoID()));
        ID.setEditable(!true);
    }
     public int getAutoID() {
        try {
            int id = -1;
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select max(EID) as max from tbl_Employee;");
            for (; rs.next();) {
                id = rs.getInt("max");
            }
            return id+1;
        } catch (SQLException ex) {
            Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
