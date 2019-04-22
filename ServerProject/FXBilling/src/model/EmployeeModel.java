package model;


import java.util.UUID;

public class EmployeeModel {
    UUID id;
    String username,password,firstname,lastname,email,phone,address;

    public EmployeeModel() { }

    public EmployeeModel(UUID id, String username, String password, String firstname, String lastname, String email, String phone, String address){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email= email;
        this.phone = phone;
        this.address =address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }
}
