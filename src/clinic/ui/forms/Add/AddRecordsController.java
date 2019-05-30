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

public class AddRecordsController implements Initializable {

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXButton addRecords;

    @FXML
    private JFXTextField patientID;

    @FXML
    private JFXTextField appointmentNo;
    @FXML
    private DatePicker appointmentDate;

    @FXML
    private JFXTextField roomNo;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void addRecordsActionPerformed(ActionEvent event) {

        if (appointmentDate.getValue() == null
                || appointmentNo.getText().equals("")
                || patientID.getText().equals("")
                || roomNo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String year = Integer.toString(appointmentDate.getValue().getYear());
                String month = Integer.toString(appointmentDate.getValue().getMonthValue());
                String day = Integer.toString(appointmentDate.getValue().getDayOfMonth());
                String APD = year + "-" + month + "-" + day;
//                System.out.println(APD);

                //System.out.println(appointmentDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")));
                String sql = "insert into tbl_Record ( AppointmentDate,AppointmentNo,Room, PID) values ('"
                        + APD + "',"
                        + Integer.parseInt(appointmentNo.getText()) + ","
                        + Integer.parseInt(roomNo.getText()) + ","
                        + Integer.parseInt(patientID.getText()) + ")";
//                System.out.println(sql);
                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Stage) (((Node) (event.getSource())).getScene().getWindow())).close();
            } catch (SQLException ex) {
                Logger.getLogger(AddRecordsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public int getAutoID() {
        try {
            int id = -1;
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select max(RID) as max from tbl_Record;");
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ID.setText(Integer.toString(getAutoID()));
        ID.setEditable(!true);
    }
}
