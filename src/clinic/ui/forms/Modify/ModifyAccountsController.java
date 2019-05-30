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

public class ModifyAccountsController implements Initializable {

    @FXML
    private JFXTextField FID;

    @FXML
    private JFXButton modifyAccounts;

    @FXML
    private JFXTextField utility_exp;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXTextField monthly_balance;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyAccountsActionPerformed(ActionEvent event) {
        if (FID.getText().equals("")
                || monthly_balance.getText().equals("")
                || utility_exp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String sql = "update tbl_Account set "
                        + "MonthlyBalance="
                        + monthly_balance.getText() + ","
                        + "UtilityExpense="
                        + utility_exp.getText() + ""
                        + " where AID=" + Integer.parseInt(ID.getText()) + ";";
                System.out.println(sql);
                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                //Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            clinic.generalclasses.Account a = clinic.generalclasses.Account.a;
            monthly_balance.setText(a.getMonthlyBalance().toString());
            utility_exp.setText(a.getUtilityExpense().toString());
            FID.setText(a.getFid().toString());
            ID.setText(a.getId().toString());
            ID.setEditable(!true);
            FID.setText(a.getFid().toString());
            FID.setEditable(!true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
