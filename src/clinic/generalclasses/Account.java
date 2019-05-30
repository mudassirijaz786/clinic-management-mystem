
package clinic.generalclasses;

public class Account {

    public Account(Integer id, Float monthlyBalance, Integer fid, Float utilityExpense) {
        this.id = id;
        this.monthlyBalance = monthlyBalance;
        this.fid = fid;
        this.utilityExpense = utilityExpense;
    }
    public static Account a;


    private Integer id;
    private Float monthlyBalance;

    private Integer fid;
    private Float utilityExpense;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMonthlyBalance() {
        return monthlyBalance;
    }

    public void setMonthlyBalance(Float monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }



    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Float getUtilityExpense() {
        return utilityExpense;
    }

    public void setUtilityExpense(Float utilityExpense) {
        this.utilityExpense = utilityExpense;
    }
}
