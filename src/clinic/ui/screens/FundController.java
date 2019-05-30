
package clinic.ui.screens;

import clinic.generalclasses.Fund;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FundController implements Initializable {

    @FXML
    private TableColumn<clinic.generalclasses.Fund, Date> fundDateCol;

    @FXML
    private JFXButton modifyFund;

    @FXML
    private JFXTextField search;

    @FXML
    private TableColumn<clinic.generalclasses.Fund, Integer> fundIDCol;

    @FXML
    private TableColumn<clinic.generalclasses.Fund, Float> fundAmountCol;

    @FXML
    private JFXButton addFun;

    @FXML
    private TableView<clinic.generalclasses.Fund> allFundTable;

    @FXML
    private JFXButton removeFund;

    @FXML
    private TableColumn<clinic.generalclasses.Fund, Integer> fundDIDCol;

    public void setProperties() {
        fundIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fundAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        fundDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        fundDIDCol.setCellValueFactory(new PropertyValueFactory<>("did"));

    }

    @FXML
    void searchActionPerformed(ActionEvent event) {
        String text = search.getText();
        if (!text.equals("")) {
            try {
                String sql = "select * from tbl_Fund where FID like '%" + text + "%'";
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allFundTable.getItems().clear();
                allFundTable.refresh();
                for (; rs.next();) {
                    Fund f = new Fund(rs.getInt("FID"), rs.getFloat("Amount"), rs.getDate("Date"), rs.getInt("DID"));
                    allFundTable.getItems().add(f);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "select * from tbl_Fund";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allFundTable.getItems().clear();
            allFundTable.refresh();
            for (; rs.next();) {
                Fund f = new Fund(rs.getInt("FID"), rs.getFloat("Amount"), rs.getDate("Date"), rs.getInt("DID"));
                allFundTable.getItems().add(f);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    void addFundActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddFund.fxml"));
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
    void modifyFundActionPerformed(ActionEvent event) {
        try {
            if (allFundTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select a fund to modify");
            } else {
                clinic.generalclasses.Fund.f = allFundTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyFund.fxml"));
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
    void removeFundActionPerformed(ActionEvent event) {
        clinic.generalclasses.Fund f = allFundTable.getSelectionModel().getSelectedItem();
        if (f == null) {
            JOptionPane.showMessageDialog(null, "Select a fund to Remove");
        } else {
            try {
                String sql = "delete from tbl_Fund where FID=" + f.getId();
                DatabaseUtil.Database.statement.execute(sql);
                fillTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setProperties();
        fillTable();
    }

}
