package clinic.ui.forms.Modify;

import clinic.ui.forms.Add.AddPatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ModifyFundController implements Initializable {

    @FXML
    private DatePicker date;

    @FXML
    private JFXButton modifyFund;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXTextField DID;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyFundActionPerformed(ActionEvent event) {
        if (amount.getText().equals("")
                || date.getValue() == null
                || DID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String year = Integer.toString(date.getValue().getYear());
                String month = Integer.toString(date.getValue().getMonthValue());
                String day = Integer.toString(date.getValue().getDayOfMonth());
                String APD = year + "-" + month + "-" + day;
                String sql = "update tbl_Fund set "
                        + "Amount='"
                        + amount.getText() + "',"
                        + "Date='"
                        + APD + "',"
                        + "DID='"
                        + DID.getText() + "' where FID=" + Integer.parseInt(ID.getText()) + ";";

                System.out.println(sql);

                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        clinic.generalclasses.Fund f = clinic.generalclasses.Fund.f;
        amount.setText(f.getAmount().toString());
        date.setValue(LocalDate.of((Integer.parseInt(f.getDate().toString().split("-")[0])), (Integer.parseInt(f.getDate().toString().split("-")[1])), (Integer.parseInt(f.getDate().toString().split("-")[2]))));
        ID.setText(f.getId().toString());
        ID.setEditable(!true);
        DID.setText(f.getDid().toString());
        DID.setEditable(!true);
    }

}
