package clinic.ui.forms.Add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AddPatientController implements Initializable {

    @FXML
    private JFXTextField address;

    @FXML
    private JFXButton addPatient;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXListView<String> listOfMedicines;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXComboBox<String> MID;

    @FXML
    private ToggleGroup gender = new ToggleGroup();

    @FXML
    private JFXTextField age;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXTextField contactNo;

    public boolean checkGend(JFXRadioButton mal, JFXRadioButton fmale) {
        if (mal.isSelected() || fmale.isSelected()) {
            return true;
        } else {
            return false;
        }
    }
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void addPatientActionPerfomed(ActionEvent event) {
        if (address.getText().equals("")
                || age.getText().equals("")
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
                String sql = "insert into tbl_Patient ( Name,Contact,Age, Address, Gender) values ('"
                        + name.getText() + "','"
                        + contactNo.getText() + "','"
                        + Integer.parseInt(age.getText()) + "','"
                        + address.getText() + "','"
                        + gend + "')";
                List<String> list = listOfMedicines.getItems();
                if (save(sql, list)) {
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    JOptionPane.showMessageDialog(null, "Saved");
                }
            } catch (Exception ex) {
                Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean save(String sql, List<String> list) {
        if (list.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Plz Select a medicine to add !");
            alert.setHeaderText("Error 304!");
            alert.setTitle("Error!");
            alert.showAndWait();
            return false;
        } else {
            try {
                int i = 0;
                DatabaseUtil.Database.statement.execute(sql);
                while (i < list.size()) {
                    int pid = 0;
                    ResultSet res = DatabaseUtil.Database.statement.executeQuery("select max (PID) as max from tbl_Patient");
                    while (res.next()) {
                        pid = res.getInt("max");
                    }
                    DatabaseUtil.Database.statement.execute("insert into tbl_Medicine_Patient (PID,MID,Quantity) values ("
                            + pid + ","
                            + list.get(i).split("-")[0] + ","
                            + list.get(i).split("-")[2] + ")");
                    ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select QuantityInStock from tbl_Medicine where MID=" + list.get(i).split("-")[0]);
                    int quan = 0;
                    while (rs.next()) {
                        quan = rs.getInt("QuantityInStock");
                    }
                    DatabaseUtil.Database.statement.execute("update tbl_Medicine set QuantityInStock=" + (quan - Integer.parseInt(list.get(i).split("-")[2])) + " where MID=" + list.get(i).split("-")[0]);
                    i++;
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    public int getAutoID() {
        try {
            int id = -1;
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select max(PID) as max from tbl_Patient;");
            for (; rs.next();) {
                id = rs.getInt("max");
            }
            return id + 1;
        } catch (SQLException ex) {
            Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @FXML
    public void addMedicineToList(ActionEvent event) {
        if (MID.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select a medicine to add !");
            alert.setHeaderText("Error 304!");
            alert.setTitle("Error!");
            alert.showAndWait();
        } else {
            addMedicine();
        }
    }

    @FXML
    public void addMedicineToListKeyAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (MID.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Select a medicine to add !");
                alert.setHeaderText("Error 304!");
                alert.setTitle("Error!");
                alert.showAndWait();
            } else {
                addMedicine();
            }
        }
    }

    public void addMedicine() {
        try {
            String medicine = MID.getSelectionModel().getSelectedItem();
            Integer quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity"));
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select QuantityInStock from tbl_Medicine where MID=" + medicine.split("-")[0]);
            int q = 0;
            while (rs.next()) {
                q = rs.getInt("QuantityInStock");
            }
            if (quantity > q) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Medicine Quantity is Short in Stock !");
                alert.setHeaderText("Error 304!");
                alert.setTitle("Error!");
                alert.showAndWait();
            } else {
                listOfMedicines.getItems().add(medicine + "-" + quantity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println(location.toString());
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try {
                male.setToggleGroup(gender);
                female.setToggleGroup(gender);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            ID.setText(Integer.toString(getAutoID()));
            ID.setEditable(!true);

            ResultSet rs = DatabaseUtil.Database.statement.executeQuery("select MID, Name from tbl_Medicine");
            for (; rs.next();) {
                Integer id = rs.getInt("MID");
                String name = rs.getString("Name");
                MID.getItems().add(id + "-" + name);
            }
            male.setSelected(true);
        } catch (SQLException ex) {
            Logger.getLogger(AddPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
