package clinic.ui.screens;

import clinic.generalclasses.Account;
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

public class AccountsController implements Initializable {

    @FXML
    private JFXTextField search;

    @FXML
    private JFXButton modifyAccounts;

    @FXML
    private JFXButton addAccounts;
    @FXML
    private TableView<clinic.generalclasses.Account> allAccountsTable;
    @FXML
    private JFXButton removeAccounts;

    @FXML
    private TableColumn<clinic.generalclasses.Account, Integer> accountIDCol;

    @FXML
    private TableColumn<clinic.generalclasses.Account, Float> accountFIDCol;
    @FXML
    private TableColumn<clinic.generalclasses.Account, Float> accountMonthlyBalanceCol;
    @FXML
    private TableColumn<clinic.generalclasses.Account, Float> accountUtilityExpenseCol;

    public void setProperties() {
        accountIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        accountMonthlyBalanceCol.setCellValueFactory(new PropertyValueFactory<>("monthlyBalance"));
        accountFIDCol.setCellValueFactory(new PropertyValueFactory<>("fid"));
        accountUtilityExpenseCol.setCellValueFactory(new PropertyValueFactory<>("utilityExpense"));
    }

    @FXML
    void addAccountsActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddAccounts.fxml"));
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
                String sql = "select * from tbl_Account where AID=" + Integer.parseInt(text);
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allAccountsTable.getItems().clear();
                allAccountsTable.refresh();
                for (; rs.next();) {
                    Account a = new Account(rs.getInt("AID"), rs.getFloat("MonthlyBalance"), rs.getInt("FID"), rs.getFloat("UtilityExpense"));
                    allAccountsTable.getItems().add(a);
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
            String sql = "select * from tbl_Account";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allAccountsTable.getItems().clear();
            allAccountsTable.refresh();
            for (; rs.next();) {
                Account a = new Account(rs.getInt("AID"), rs.getFloat("MonthlyBalance"), rs.getInt("FID"), rs.getFloat("UtilityExpense"));
                allAccountsTable.getItems().add(a);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    void modifyAccountsActionPerformed(ActionEvent event) {
        try {
            if (allAccountsTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select an account to modify");
            } else {
                clinic.generalclasses.Account.a = allAccountsTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyAccounts.fxml"));
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
    void removeAccountsActionPerformed(ActionEvent event) {
        clinic.generalclasses.Account a = allAccountsTable.getSelectionModel().getSelectedItem();
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Select a Patient to Remove");
        } else {
            try {
                String sql = "delete from tbl_Account where AID=" + a.getId();
                DatabaseUtil.Database.statement.execute(sql);
                fillTable();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    void validate(KeyEvent event) {
        String c = event.getCharacter();
        if ("0123456789".contains(c)); else {
            event.consume();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        setProperties();
        fillTable();
    }

}
