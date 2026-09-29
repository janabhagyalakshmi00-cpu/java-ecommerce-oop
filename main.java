import java.util.*;
class Product{
    private int productID;
    private String name;
    private double price;
    private String category;
    private int stock;

    Product(int productID,String name,double price,String category,int stock){
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    //getter methods
    public int getProductID(){
        return productID;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public String getcategory(){
        return category;
    }
    public int getstock(){
        return stock;
    }

    //setter methods
    public void setPrice(double price){
        this.price = price;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

    //display method
    public void displayProduct(){
        System.out.println(getProductID());
        System.out.println(getName());
        System.out.println(getcategory());
        System.out.println(getPrice());
        System.out.println(getstock());
    }
}

class User{
    private int userId;
    private String name;
    private String email;

    //constructor
    User(int userId,String name,String email){
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    //getter methods
    public int getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    //display user method
    public void displayUser(){
        System.out.println("user Id: "+getUserId());
        System.out.println("user name: "+getName());
        System.out.println("user email: "+getEmail());
    }
}
class Customer extends User{
    private String address;
    private String phoneNumber;

    Customer(int userId,String name,String email,String address,String phoneNumber){
        super(userId, name, email);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //getter customer methods
    public String getAddress(){
        return address;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    @Override 
    public void displayUser(){
        System.out.println("user Id: "+getUserId());
        System.out.println("user name: "+getName());
        System.out.println("user email: "+getEmail());
        System.out.println("user address: "+getAddress());
        System.out.println("user phone number: "+getPhoneNumber());
    }
}
class Admin extends User{
    private String role;

    //constructor
    Admin(int userId,String name,String email,String role){
        super(userId, name, email);
        this.role = role;
    }

    //getter methods
    public String getRole(){
        return role;
    }

    @Override 
    public void displayUser(){
        System.out.println("user Id: "+getUserId());
        System.out.println("user name: "+getName());
        System.out.println("user email: "+getEmail());
        System.out.println("user role: "+getRole());
    }
}
//cart
class Cart{
    private int userId;
    private ArrayList<Product> products;
    Cart(int userId){
        this.userId = userId;
        products = new ArrayList<>();
    }
    //add to cart method
    public void addProduct(Product product){
        products.add(product);
    }
    //remove from cart
    public void removeProduct(Product product){
        products.remove(product);
    }
    //view cart
    public void viewCart(){
        for(int i = 0;i < products.size();i++){
         products.get(i).displayProduct(); 
        }
    }
    //calculate total
    public double getTotal(){
        double total = 0;
        for(int i = 0;i < products.size();i++){
            total = total+products.get(i).getPrice();
        }
        return total;
    }
    //getter method for products
    public ArrayList<Product> getProductList(){
        return products;
    }
    //method for clearing cart
    public void clearCart(){
        products.clear();
    }
}
//order
class Order{
    private int orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private String status;
    //constructor
    Order(int orderId,Customer customer,String status){
        this.orderId = orderId;
        this.customer = customer;
        this.status = status;
        products = new ArrayList<>();
    }
    //addproduct
    public void addProduct(Product product){
        products.add(product);
    }
    //calculating total
    public double getTotal(){
       double total = 0; 
       for(int i=0;i<products.size();i++){
        total = total+products.get(i).getPrice();
       }
       return total;
    }
    //update status
    public void updateStatus(String status){
        this.status = status;
    }
    //display order
    public void displayOrder(){
        System.out.println("Order ID: "+orderId);
        System.out.println("Customer: ");
        customer.displayUser();
        System.out.println("Products: ");
        if (products.isEmpty()) {
            System.out.println("No products in order.");
        } 
        else {
            for(int i=0;i<products.size();i++){
                products.get(i).displayProduct();
            }
        }
        System.out.println("Total Amount: "+getTotal());
        System.out.println("Status: "+status);
    }
}
public class main {

    public static void main(String[] args) {
        Product p1 = new Product(101, "Laptop", 55000, "Electronics", 10);
        p1.displayProduct();
        System.out.println();
        Customer user1 = new Customer(101, "Alice", "alice@gmail.com", "Hyderabad", "9876543210");
        User user2 = new Admin(102, "Bob", "bob@gmail.com", "Product manager");
        user1.displayUser();
        System.out.println();
        user2.displayUser();
        Cart cart1 = new Cart(101);
        cart1.addProduct(p1);
        System.out.println();
        System.out.println("----- CART -----");
        cart1.viewCart();
        System.out.println();
        System.out.println("Total Price: " + cart1.getTotal());
        cart1.removeProduct(p1);
        System.out.println();
        System.out.println("----- CART AFTER REMOVE -----");
        cart1.viewCart();
        System.out.println("Total Price: " + cart1.getTotal());
        System.out.println();
        Order O1 = new Order(1,user1, "Placed");
        for(int i=0;i<cart1.getProductList().size();i++){
            O1.addProduct(cart1.getProductList().get(i));
        }
        O1.displayOrder();
        O1.updateStatus("Shipped");
        System.out.println();
        System.out.println("----- UPDATED ORDER -----");
        O1.displayOrder();
        cart1.clearCart();
        cart1.viewCart();
        Cart cart2 = new Cart(101);
        Product p2 = new Product(102, "Mouse", 1000, "Accessories", 5);
        cart2.addProduct(p1);
        cart2.addProduct(p2);
        System.out.println();
        System.out.println("----- MULTIPLE PRODUCTS -----");
        cart2.viewCart();
        System.out.println("Total Price: " + cart2.getTotal());
        cart2.clearCart();
        System.out.println();
        System.out.println("----- CART AFTER CLEAR -----");
        cart2.viewCart();
        System.out.println("Total Price: " + cart2.getTotal());
    }
}