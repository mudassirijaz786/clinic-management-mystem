package clinic.ui.screens;

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

public class EmployeeController implements Initializable {

    @FXML
    private JFXButton modifyEmployee;

    @FXML
    private JFXButton viewEmployeeSalaryDetail;

    @FXML
    private JFXButton viewEmployee;

    @FXML
    private JFXButton removeEmployee;

    @FXML
    private JFXButton addEmployee;
    @FXML
    private JFXTextField search;
    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeeTypeCol;

    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeeContactNoCol;
    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeeGenderCol;

    @FXML
    private TableView<clinic.generalclasses.Employee> allEmployeeTable;
    @FXML
    private TableColumn<clinic.generalclasses.Employee, Integer> employeeAgeCol;
    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeeNameCol;

    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeePasswordCol;
    @FXML
    private TableColumn<clinic.generalclasses.Employee, Float> employeeSalaryCol;

    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeeAddressCol;

    @FXML
    private TableColumn<clinic.generalclasses.Employee, String> employeeUsernameCol;

    @FXML
    private TableColumn<clinic.generalclasses.Employee, Integer> employeeIDCol;

    @FXML
    private TableColumn<clinic.generalclasses.Employee, Integer> employeeCIDCol;

    public void setProperties() {
        employeeIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeCIDCol.setCellValueFactory(new PropertyValueFactory<>("cid"));
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeContactNoCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        employeeAgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        employeeGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        employeeAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        employeeTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        employeeUsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        employeePasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        employeeSalaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }

    @FXML
    void searchActionPerformed(KeyEvent event) {
        String text = search.getText();
        if (!text.equals("")) {
            try {
                String sql = "select * from tbl_Employee where Name like '%" + text + "%'";
                ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
                allEmployeeTable.getItems().clear();
                allEmployeeTable.refresh();
                for (; rs.next();) {
                    Employee e = new Employee(rs.getInt("EID"), rs.getString("Name"), rs.getInt("Age"), rs.getInt("CID"), rs.getString("Address"), rs.getFloat("Salary"), rs.getString("Contact"), rs.getString("Type"), rs.getString("Gender"), rs.getString("Username"), rs.getString("Password"));
                    allEmployeeTable.getItems().add(e);
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
            String sql = "select * from tbl_Employee";
            ResultSet rs = DatabaseUtil.Database.statement.executeQuery(sql);
            allEmployeeTable.getItems().clear();
            allEmployeeTable.refresh();
            for (; rs.next();) {
                Employee e = new Employee(rs.getInt("EID"), rs.getString("Name"), rs.getInt("Age"), rs.getInt("CID"), rs.getString("Address"), rs.getFloat("Salary"), rs.getString("Contact"), rs.getString("Type"), rs.getString("Gender"), rs.getString("Username"), rs.getString("Password"));
                allEmployeeTable.getItems().add(e);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    void viewEmployeeSalaryDetailActionPerformed(ActionEvent event) {
        try {
            if (allEmployeeTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Select an Employee to View Record");
            } else {
                Employee.e = allEmployeeTable.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/View/EmployeeSalaryDetail.fxml"));
                Stage dashboardAdmin = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardAdmin.setScene(scene);
                dashboardAdmin.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addEmployeeActionPerformed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Add/AddEmployee.fxml"));
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
    void modifyEmployeeActionPerformed(ActionEvent event) {
        try {
            if (allEmployeeTable.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "select an employee to modify");
            } else {
                clinic.generalclasses.Employee.e = allEmployeeTable.getSelectionModel().getSelectedItem();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/forms/Modify/ModifyEmployee.fxml"));
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
    void removeEmployeeActionPerformed(ActionEvent event) {
        clinic.generalclasses.Employee e = allEmployeeTable.getSelectionModel().getSelectedItem();
        if (e == null) {
            JOptionPane.showMessageDialog(null, "Select an Employee to Remove");
        } else {
            try {
                String sql = "delete from tbl_Employee where EID=" + e.getId();
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
