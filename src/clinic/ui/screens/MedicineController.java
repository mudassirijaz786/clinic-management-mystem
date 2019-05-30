package clinic.ui.screens;

import clinic.generalclasses.Medicine;
import clinic.generalclasses.Patient;

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

public class MedicineController implements Initializable {

    @FXML
    private JFXButton modifyMedicine;

    @FXML
    private TableView<clinic.generalclasses.Medicine> allMedicineTable;

    @FXML
    private JFXTextField search;

    @FXML
    private JFXTextField search1;

    @FXML
    private JFXButton removeMedicine;

    @FXML
    private TableColumn<clinic.generalclasses.Medicine, Integer> medicineIDCol;

    @FXML
    private TableColumn<clinic.generalclasses.Medicine, String> medicineNameCol;

    @FXML
    private JFXButton addMedicines;

    @FXML
    private TableColumn<clinic.generalclasses.Medicine, String> medicineDescCol;

    @FXML
    private TableColumn<clinic.generalclasses.Medicine, Integer> medicineQuantityInStockCol;

    public void setProperties() {
        medicineIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        medicineNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        medicineDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        medicineQuantityInStockCol.setCellValueFactory(new PropertyValueFactory<>("quantityInStock"));

    }

    @FXML
    void removeMedicinesActionPerformed(ActionEvent event) {
        clinic.generalclasses.Medicine m = allMedicineTable.getSelectionModel().getSelectedItem();
        if (m == null) {
            JOptionPane.showMessageDialog(null, "Select a Medicine to Remove");
        } else {
            try {
                String sql = "delete from tbl_Medicine where MID=" + m.getId();
                DatabaseUtil.Database.statement.execute(sql);
                fillTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    void modifyMedicinesActionPerformed(ActionEvent event) {
        try {
            if (allMedicineTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select a medicine to modify");
            } else {
                clinic.generalclasses.Medicine.m = allMedicineTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyMedicines.fxml"));
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
    void addMedicinesActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddMedicines.fxml"));
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
    void searchActionPerformedById(KeyEvent event) {
        Integer text = Integer.parseInt(search.getText());
//        System.out.println(text+"TEXT");
        if (text != null) {
            try {
                String sql = "select * from tbl_Medicine where MID=" + text;
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allMedicineTable.getItems().clear();
                allMedicineTable.refresh();
                for (; rs.next();) {
                    Medicine m = new Medicine(rs.getInt("MID"), rs.getString("Name"), rs.getString("Description"), rs.getInt("QuantityInStock"));
                    allMedicineTable.getItems().add(m);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            fillTable();
        }
    }

    @FXML
    void searchActionPerformedByName(KeyEvent event) {
        String text = search.getText();
        if (!text.equals("")) {
            try {
                String sql = "select * from tbl_Medicine where Name like '%" + text + "%'";
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allMedicineTable.getItems().clear();
                allMedicineTable.refresh();
                for (; rs.next();) {
                    Medicine m = new Medicine(rs.getInt("MID"), rs.getString("Name"), rs.getString("Description"), rs.getInt("QuantityInStock"));
                    allMedicineTable.getItems().add(m);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            fillTable();
        }
    }

    @FXML
    void validate(KeyEvent event) {
        String c = event.getCharacter();
        if ("0123456789".contains(c)); else {
            event.consume();
        }
    }

    public void fillTable() {
        try {
            String sql = "select * from tbl_Medicine";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allMedicineTable.getItems().clear();
            allMedicineTable.refresh();
            for (; rs.next();) {
                Medicine m = new Medicine(rs.getInt("MID"), rs.getString("Name"), rs.getString("Description"), rs.getInt("QuantityInStock"));
                allMedicineTable.getItems().add(m);
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
