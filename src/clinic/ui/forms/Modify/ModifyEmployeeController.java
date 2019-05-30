package clinic.ui.forms.Modify;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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

public class ModifyEmployeeController implements Initializable {

    @FXML
    private JFXButton modifyEmployee;

    @FXML
    private JFXTextField address;

    @FXML
    private ToggleGroup gender = new ToggleGroup();

    @FXML
    private JFXTextField name;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;

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
    private JFXTextField contactNo;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyEmployeeActionPerformed(ActionEvent event) {
        if (name.getText().equals("")
                || address.getText().equals("")
                || type.getSelectionModel().getSelectedItem() == null
                || age.getText().equals("")
                || CID.getText().equals("")
                || contactNo.getText().equals("")
                || username.getText().equals("")
                || password.getText().equals("")
                || salary.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String gend;
                if (male.isSelected()) {
                    gend = "Male";
                } else {
                    gend = "Female";
                }
                String sql = "update tbl_Employee set "
                        + "Name='"
                        + name.getText() + "',"
                        + "Contact='"
                        + contactNo.getText() + "',"
                        + "Age='"
                        + age.getText() + "',"
                        + "CID='"
                        + CID.getText() + "',"
                        + "Address='"
                        + address.getText() + "',"
                        + "Type='"
                        + type.getSelectionModel().getSelectedItem() + "',"
                        + "Username='"
                        + username.getText() + "',"
                        + "Password='"
                        + password.getText() + "',"
                        + "Salary='"
                        + salary.getText() + "',"
                        + "Gender='"
                        + gend + "'where EID=" + Integer.parseInt(ID.getText()) + ";";
                System.out.println(sql);
                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                Logger.getLogger(ModifyEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        clinic.generalclasses.Employee e = clinic.generalclasses.Employee.e;
        System.out.println(e.toString());
        age.setText(e.getAge().toString());
        CID.setText(e.getCid().toString());
        contactNo.setText(e.getContact());
        name.setText(e.getName());
        address.setText(e.getAddress());
        username.setText(e.getUsername());
        password.setText(e.getPassword());
        salary.setText(e.getSalary().toString());
        type.getItems().addAll("Doctor", "Admin", "Receptionist", "Store attandent");
        fillTypeField(e.getType());
        ID.setText(e.getId().toString());
        ID.setEditable(!true);
        CID.setText(e.getCid().toString());
        CID.setEditable(!true);
        ToggleGroup g = new ToggleGroup();
        male.setToggleGroup(g);
        female.setToggleGroup(g);
        if (e.getGender().equals("male")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
    }

    public void fillTypeField(String typ) {
        switch (typ) {
            case "Doctor":
                type.getSelectionModel().select(0);
            case "Admin":
                type.getSelectionModel().select(1);
            case "Receptionist":
                type.getSelectionModel().select(2);
            default:
                type.getSelectionModel().select(3);

        }

    }
}
