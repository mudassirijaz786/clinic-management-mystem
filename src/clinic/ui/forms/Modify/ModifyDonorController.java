package clinic.ui.forms.Modify;

import clinic.ui.forms.Add.AddPatientController;
import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ModifyDonorController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXButton modifyDonor;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyDonorActionPerformed(ActionEvent event) {
        if (name.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String sql = "update tbl_Donor set "
                        + "Name='"
                        + name.getText() + "' where DID=" + Integer.parseInt(ID.getText()) + ";";
                System.out.println(sql);

                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Stage)(((Node) (event.getSource())).getScene().getWindow())).close();
            } catch (SQLException ex) {
                Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        clinic.generalclasses.Donor d = clinic.generalclasses.Donor.d;
        name.setText(d.getName());
        ID.setText(d.getId().toString());
        ID.setEditable(!true);
    }

}
