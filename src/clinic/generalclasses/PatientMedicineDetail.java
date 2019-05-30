/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic.generalclasses;

public class PatientMedicineDetail {
    private Integer PID;
    private Integer MID;
    private String PName;
    private String MName;

    public PatientMedicineDetail(Integer PID, Integer MID, String PName, String MName, Integer Quantity) {
        this.PID = PID;
        this.MID = MID;
        this.PName = PName;
        this.MName = MName;
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "PatientMedicineDetail{" + "PID=" + PID + ", MID=" + MID + ", PName=" + PName + ", MName=" + MName + ", Quantity=" + Quantity + '}';
    }
    private Integer Quantity;

    public Integer getPID() {
        return PID;
    }

    public void setPID(Integer PID) {
        this.PID = PID;
    }

    public Integer getMID() {
        return MID;
    }

    public void setMID(Integer MID) {
        this.MID = MID;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }
    
}
