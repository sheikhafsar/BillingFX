package model;

public class ProductModel {
    int id;
    String name;
    String barcode;
    int quantity;
    double price;
    double total;
    int count;

    public ProductModel(int id, String name, String barcode, int quantity, double price, double total, int count) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.count =count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
