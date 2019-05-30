/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic.generalclasses;

import java.util.Date;

/**
 *
 * @author ijazm
 */
public class Fund {
   public static Fund f;
   private Integer id;
   private Float amount;

    public Fund(Integer id, Float amount, Date date, Integer did) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.did = did;
    }
   private Date date;
   private Integer did;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
   
}
