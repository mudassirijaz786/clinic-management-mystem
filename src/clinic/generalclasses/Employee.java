package clinic.generalclasses;

public class Employee {

    public static Employee e;
    private Integer id;
    private Integer cid;
    private String name;
    private Integer age;

    private String address;

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", cid=" + cid + ", name=" + name + ", age=" + age + ", address=" + address + ", salary=" + salary + ", contact=" + contact + ", type=" + type + ", gender=" + gender + ", username=" + username + ", password=" + password + '}';
    }

    public Employee(Integer id, String name, Integer age, Integer cid, String address, Float salary, String contact, String type, String gender, String username, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cid = cid;
        this.address = address;
        this.salary = salary;
        this.contact = contact;
        this.type = type;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }
    private Float salary;
    private String contact;
    private String type;
    private String gender;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
