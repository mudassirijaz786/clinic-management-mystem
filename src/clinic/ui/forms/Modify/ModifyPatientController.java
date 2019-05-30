package clinic.ui.forms.Modify;

import clinic.ui.forms.Add.AddPatientController;
import com.jfoenix.controls.JFXButton;
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

public class ModifyPatientController implements Initializable {

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField gender;

    @FXML
    private JFXButton addPatient;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField id;

//    @FXML
//    private JFXTextField mid;
    @FXML
    private JFXTextField age;

    @FXML
    private JFXTextField contactNo;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyPatientActionPerfomed(ActionEvent event) {
        if (address.getText().equals("")
                || age.getText().equals("")
                //                || mid.getText().equals("")
                || contactNo.getText().equals("")
                || name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String gend;
                if (male.isSelected()) {
                    gend = "Male";
                } else {
                    gend = "Female";
                }
                String sql = "update tbl_Patient set "
                        + "Name='"
                        + name.getText() + "',"
                        + "Contact='"
                        + contactNo.getText() + "',"
                        //                        + "MID='"
                        //                        + mid.getText() + "',"
                        + "Age="
                        + Integer.parseInt(age.getText()) + ","
                        + "Address='"
                        + address.getText() + "',"
                        + "Gender='"
                        + gend + "' where PID=" + Integer.parseInt(id.getText()) + ";";
                System.out.println(sql);

                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    @FXML
    JFXRadioButton male;
    @FXML
    JFXRadioButton female;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clinic.generalclasses.Patient p = clinic.generalclasses.Patient.getP();
        age.setText(p.getAge().toString());
        contactNo.setText(p.getPhoneNumber());
        name.setText(p.getName());
        address.setText(p.getAddress());
//        mid.setText(p.getMid().toString());
        id.setText(p.getId().toString());
        id.setEditable(!true);
//                mid.setText(p.getMid().toString());
//        mid.setEditable(!true);
        ToggleGroup g = new ToggleGroup();
        male.setToggleGroup(g);
        female.setToggleGroup(g);
        if (p.getGender().equals("male")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
    }

}
