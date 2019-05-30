package clinic.generalclasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ijazm
 */
public class Patient {

    public static Patient p;

    public static Patient getP() {
        return p;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", address=" + address + ", age=" + age + '}';
    }

    public static void setP(Patient aP) {
        p = aP;
    }
    private Integer id;
    private String name;
    private String phoneNumber;
    private String gender;
    private String address;
    private Integer age;

    public Patient(Integer id, String name, String phoneNumber, String gender, String address, Integer age) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
