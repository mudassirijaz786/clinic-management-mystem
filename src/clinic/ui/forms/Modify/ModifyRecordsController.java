package clinic.ui.forms.Modify;

import clinic.ui.forms.Add.AddPatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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

public class ModifyRecordsController implements Initializable {

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXTextField patient_id;

    @FXML
    private JFXTextField appointment_Num;

    @FXML
    private DatePicker appointment_date;

    @FXML
    private JFXTextField room_no;

    @FXML
    private JFXButton modifyRecords;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyRecordsActionPerformed(ActionEvent event) {
        LocalDate ld = appointment_date.getValue();
        if (appointment_Num.getText().equals("")
                || ld == null
                || patient_id.getText().equals("")
                || room_no.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {
                String year = Integer.toString(appointment_date.getValue().getYear());
                String month = Integer.toString(appointment_date.getValue().getMonthValue());
                String day = Integer.toString(appointment_date.getValue().getDayOfMonth());
                String APD = year + "-" + month + "-" + day;
                String sql = "update tbl_Record set "
                        + "AppointmentDate='"
                        + APD + "',"
                        + "AppointmentNo="
                        + appointment_Num.getText() + ","
                        + "Room="
                        + room_no.getText() + ","
                        + "PID="
                        + patient_id.getText() + " where RID=" + Integer.parseInt(ID.getText()) + ";";
                System.out.println(sql);

                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clinic.generalclasses.Record r = clinic.generalclasses.Record.r;
        appointment_Num.setText(r.getAppointmentNo().toString());
        appointment_date.setValue(LocalDate.of((Integer.parseInt(r.getAppointmentDate().toString().split("-")[0])), (Integer.parseInt(r.getAppointmentDate().toString().split("-")[1])), (Integer.parseInt(r.getAppointmentDate().toString().split("-")[2]))));
        patient_id.setText(r.getPatientId().toString());
        room_no.setText(r.getRoom().toString());
        ID.setText(r.getId().toString());
        ID.setEditable(!true);
        patient_id.setEditable(!true);

    }

}
