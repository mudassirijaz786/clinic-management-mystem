package clinic.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    @FXML
    private JFXButton patient;
    @FXML
    private JFXButton employee;
    @FXML
    private JFXButton accounts;
 

    @FXML
    private JFXButton medicines;
    @FXML
    private JFXButton fund;
    @FXML
    private JFXButton donor;
    @FXML
    private JFXButton records;
    @FXML
    private AnchorPane dashboardContent;

    public void localFxml(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginByLabel.setText(clinic.ui.login.LoginController.USERNAME + "(" + clinic.ui.login.LoginController.TYPE + ")");
    }
    @FXML
    Text loginByLabel;

    @FXML
    void patientActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Patient.fxml"));
            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().add(root);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void employeeActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Employee.fxml"));
            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void accountsActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Accounts.fxml"));

            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().addAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void recordsActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Records.fxml"));

            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().addAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void medicinesActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Medicine.fxml"));
            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().addAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void fundActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Fund.fxml"));
            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().addAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void donorActionPerformed(ActionEvent event) {
        try {
            dashboardContent.getChildren();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/screens/Donor.fxml"));
            AnchorPane root = loader.load();
            dashboardContent.getChildren().clear();
            dashboardContent.getChildren().addAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutActionPerformed(ActionEvent event) {
        try {
            Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
            s.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/clinic/ui/login/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage st = new Stage();
            st.setScene(scene);
            st.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
