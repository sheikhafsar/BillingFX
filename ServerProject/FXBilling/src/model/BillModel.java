package model;

//SELECT bill_id,customer_name,date,amount,username FROM bill,employee WHERE bill.emp_id=employee.id

public class BillModel {

    public BillModel(int bill_id, String date, String customerName, Double amount, String employeeName) {
        this.bill_id = bill_id;
        this.date = date;
        this.customerName = customerName;
        this.amount = amount;
        this.employeeName = employeeName;
    }

    int bill_id;
    String date;
    String customerName;
    Double amount;
    String employeeName;

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


}
