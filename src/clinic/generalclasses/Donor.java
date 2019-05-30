/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic.generalclasses;

/**
 *
 * @author ijazm
 */
public class Donor {
    public static Donor d;
    private Integer id;

    public Donor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
