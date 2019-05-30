package clinic.ui.forms.Modify;

import clinic.ui.forms.Add.AddPatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ModifyMedicinesController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXButton modifyMedicines;

    @FXML
    private JFXTextField Id;

    @FXML
    private JFXTextField Description;

    @FXML
    private JFXTextField quantityInStock;
    @FXML
    void cancelActionPerformed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void modifyMedicinesActionPerformed(ActionEvent event) {
        if (Description.getText().equals("")
                || name.getText().equals("")
                || quantityInStock.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "fill all fields");
        } else {
            try {

                String sql = "update tbl_Medicine set "
                        + "Name='"
                        + name.getText() + "',"
                        + "QuantityInStock="
                        + quantityInStock.getText() + ","
                        + "Description='"
                        + Description.getText() + "' where MID=" + Integer.parseInt(Id.getText()) + ";";
                System.out.println(sql);
                DatabaseUtil.Database.statement.execute(sql);
                JOptionPane.showMessageDialog(null, "Saved");
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (SQLException ex) {
                    Logger.getLogger(ModifyMedicinesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        clinic.generalclasses.Medicine m = clinic.generalclasses.Medicine.m;
        name.setText(m.getName());
        Description.setText(m.getDescription());
        quantityInStock.setText(m.getQuantityInStock().toString());
        Id.setText(m.getId().toString());
        Id.setEditable(!true);
    }

}
