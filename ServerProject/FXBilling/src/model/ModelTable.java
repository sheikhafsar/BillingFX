package model;


public class ModelTable {
    String id,firstname,email,phone,address;

    public ModelTable(String user_id, String firstname, String email, String phone, String address){
        this.id = user_id;
        this.firstname = firstname;
        this.email= email;
        this.phone = phone;
        this.address =address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

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

    public void setAddress(String address) {
        this.address = address;
    }
}
