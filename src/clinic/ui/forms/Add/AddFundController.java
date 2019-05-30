package clinic.ui.forms.Add;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AddFundController implements Initializable {

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXButton addFund;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXTextField DID;

    @FXML
    void addFundActionPerformed(ActionEvent event) {
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
                String sql = "insert into tbl_Fund (Amount,Date,DID) values ("
                        + amount.getText() + ",'"
                        + APD + "',"
                        + Integer.parseInt(DID.getText()) + ")";
                System.out.println(sql);
                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                Logger.getLogger(AddFundController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public int getAutoID() {
        try {
            int id = -1;
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select max(FID) as max from tbl_Fund;");
            for (; rs.next();) {
                id = rs.getInt("max");
            }
            return id + 1;
        } catch (SQLException ex) {
            Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ID.setText(Integer.toString(getAutoID()));
        ID.setEditable(!true);

    }

}
