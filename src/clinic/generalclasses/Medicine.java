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
public class Medicine {
    public static Medicine m;
    private Integer id;
    private Integer quantityInStock;

    public Medicine( Integer id, String name, String description, Integer quantityInStock) {
  
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantityInStock = quantityInStock;
    }
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    
}
