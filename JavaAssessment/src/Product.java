import java.io.*;
import java.util.Random;

public class Product implements Serializable {
    private ProductType type;
    private int salePrice;
    private String name;

    //Creating the product type enum
    public enum ProductType{
        BOOK,
        COIN,
        STAMP;
        private int  lastSalePrice,maxSalePrice;
        //constructor for enum
        ProductType(){
            this.lastSalePrice = 0;
            this.maxSalePrice = 0;
        }

        public void setLastSalePrice(int value){
            this.lastSalePrice = value;
            if(value > this.maxSalePrice){
                setMaxSalePrice(value);
            }
        }
        public void setMaxSalePrice(int value){
            this.maxSalePrice = value;

        }
        public int getLastSalePrice(){
            return this.lastSalePrice;
        }
        public int getMaxSalePrice(){
            return this.lastSalePrice;
        }
    }
    public ProductType getType(){
        return this.type;
    }

    public Product(ProductType type, String name){
        this.type = type;
        this.name = name;
    }

    //Method to change the product type randomly
    public static ProductType randomProductType(){
        double randInt = Math.random();
        randInt *= 3;

        if(randInt >= 2){
            return ProductType.BOOK;
        }
        else if(randInt >= 1){
            return ProductType.COIN;
        }
        else{
            return ProductType.STAMP;
        }

    }
    public void setSalePrice(int price){
        this.salePrice = price;
    }
    public int getSalePrice(){
        return this.salePrice;
    }
    public String toString(){
        return "----\nType = "+ type + "\nName = "+ name+"\nSale Price = "+ salePrice+"\n----";
    }

    public static void writeToFile(Product p,String FileName){
        String saveFileName = FileName+".ser";
        try{
            FileOutputStream fos = new FileOutputStream(saveFileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(p);
            oos.close();
            System.out.println("\n----Object written to file----");
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: Cannot find save file '"+saveFileName+"'");
        } catch (IOException e) {
            System.out.println("ERROR: Unable to write to save file'"+saveFileName+"'");
        }
    }
    public static Product readFromFile(String FileName){
        String saveFileName = FileName+".ser";
        try{
            FileInputStream fos = new FileInputStream(saveFileName);
            ObjectInputStream oos  = new ObjectInputStream(fos);
            Product product = (Product) oos.readObject();
            oos.close();
            System.out.println("\n----Object read from file----");
            return product;
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: Cannot find file '"+saveFileName+"'");
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read from file'"+saveFileName+"'");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Write to Product failed");
        }
        return null;
    }
}
