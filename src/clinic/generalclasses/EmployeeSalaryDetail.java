package clinic.generalclasses;

import java.util.Date;

public class EmployeeSalaryDetail {
private static EmployeeSalaryDetail e;

    public static EmployeeSalaryDetail getE() {
        return e;
    }

    public static void setE(EmployeeSalaryDetail aE) {
        e = aE;
    }
    private Integer esid;
    private Double amount;
    private Date date;

    public EmployeeSalaryDetail(Integer esid, Double amount, Date date, Integer eid) {
        this.esid = esid;
        this.amount = amount;
        this.date = date;
        this.eid = eid;
    }
    private Integer eid;

    public Integer getEsid() {
        return esid;
    }

    public void setEsid(Integer esid) {
        this.esid = esid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
