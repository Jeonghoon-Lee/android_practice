package ca.masoudbozorgi.johnabbott_ipd.model;


public class Flower {

    private String category;
    private double price;
    private String instructions;
    private String photo;
    private String name;
    private long productId;

    public Flower(){}

    public Flower(long productId, String category, String name, String instructions, double price, String photo) {

        this.productId = productId;
        this.category = category;
        this.name = name;
        this.instructions = instructions;
        this.price = price;
        this.photo = photo;
    }

    public long getProductId() {return productId;}
    public void setProductId(long productId) {this.productId = productId;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getInstructions() {return instructions;}
    public void setInstructions(String instructions) {this.instructions = instructions;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public String getPhoto() {return photo;}
    public void setPhoto(String photo) {this.photo = photo;}

    @Override
    public String toString() {
        return name + " " + price + " $\n";
    }
}
