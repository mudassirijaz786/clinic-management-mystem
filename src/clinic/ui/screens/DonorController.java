package clinic.ui.screens;

import clinic.generalclasses.Donor;
import clinic.generalclasses.Employee;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DonorController implements Initializable {

    @FXML
    private JFXButton removeDonor;

    @FXML
    private TableColumn<clinic.generalclasses.Donor, Integer> donorIDCol;

    @FXML
    private TableView<clinic.generalclasses.Donor> allDonorTable;

    @FXML
    private JFXTextField search;

    @FXML
    private TableColumn<clinic.generalclasses.Donor, String> donorNameCol;

    @FXML
    private JFXButton modifyDonor;

    @FXML
    private JFXButton addDonor;

    public void setProperties() {
        donorIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        donorNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    @FXML
    void removeDonorActionPerformed(ActionEvent event) {
        clinic.generalclasses.Donor d = allDonorTable.getSelectionModel().getSelectedItem();
        if (d == null) {
            JOptionPane.showMessageDialog(null, "Select a Donor to Remove");
        } else {
            try {
                String sql = "delete from tbl_Donor where DID=" + d.getId();
                DatabaseUtil.Database.statement.execute(sql);
                fillTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    void modifyDonorActionPerformed(ActionEvent event) {
        try {
            if (allDonorTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select an donor to modify");
            } else {
                clinic.generalclasses.Donor.d = allDonorTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyDonor.fxml"));
                Stage dashboardAdmin = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardAdmin.setScene(scene);
                dashboardAdmin.showAndWait();
                fillTable();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addDonorActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddDonor.fxml"));
            Stage dashboardAdmin = new Stage();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardAdmin.setScene(scene);
            dashboardAdmin.showAndWait();
            fillTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchActionPerformed(KeyEvent event) {
        String text = search.getText();
        if (!text.equals("")) {
            try {
                String sql = "select * from tbl_Donor where Name like '%" + text + "%'";
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allDonorTable.getItems().clear();
                allDonorTable.refresh();
                for (; rs.next();) {
                    Donor d = new Donor(rs.getInt("DID"), rs.getString("Name"));
                    allDonorTable.getItems().add(d);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            fillTable();
        }
    }

    public void fillTable() {
        try {
            String sql = "select * from tbl_Donor";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allDonorTable.getItems().clear();
            allDonorTable.refresh();
            for (; rs.next();) {
                Donor d = new Donor(rs.getInt("DID"), rs.getString("Name"));
                allDonorTable.getItems().add(d);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setProperties();
        fillTable();
    }

}
