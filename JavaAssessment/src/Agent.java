import java.util.ArrayList;

public class Agent {
    private String name;
    private ArrayList<Product> products = new ArrayList<Product>();
    private int currentSize,money;
    private final static int MAX_SIZE = 100;
    private static Product.ProductType currentProductType = Product.ProductType.BOOK;

    public Agent(String nameTemp){
        this.name = nameTemp;
    }
    public String toString(){
        return name;
    }

    public int getMoney(){
        return this.money;
    }
    public int getCurrentSize(){return this.currentSize;}
    public ArrayList<Product> getProducts(){
        return this.products;
    }
    public void setCurrentSize(int i){
        this.currentSize= i;
    }
    public void setMoney(int i){
        this.money = i;
    }
    public static void setCurrentProductType(Product.ProductType pt){
        currentProductType = pt;
    }

    public Product offerProduct(Product.ProductType pt){
        if(pt != null){
            currentProductType = pt;
        }
        boolean setP = false;
        for(Product p:this.products){
            if(p.getType() == currentProductType){
                setP = true;
                return p;
            }

        }
        if(setP==false){
            System.out.println("No object of type: "+ currentProductType +" in your products\nPicking another Product instead");
            Product[] productArray = new Product[this.products.size()];
            productArray = this.products.toArray(productArray);
            return productArray[0];
        }


        return null;
    }

    public int[] countProducts(){
        int bookCount = 0;
        int coinCount = 0;
        int stampCount = 0;


        for(Product p: this.products){
            if(p.getType() == Product.ProductType.BOOK){
                bookCount++;
            }
            if(p.getType() == Product.ProductType.COIN){
                coinCount++;
            }
            if(p.getType() == Product.ProductType.STAMP){
                stampCount++;
            }
        }
        int[] array = {bookCount,coinCount,stampCount};

        return array;
    }

    public int makeBid(Product p){
        int[] amountOfProducts = this.countProducts();
        System.out.println(this.toString()+ " has "+  amountOfProducts[0]+" Book(s), "+amountOfProducts[1]+" Coin(s), and "+amountOfProducts[2]+ " Stamps.");
        if(p.getType() == Product.ProductType.BOOK){
            if(amountOfProducts[0] == 0){

                return this.money/4;
            }
            else if(amountOfProducts[0] == 1){

                return this.money/5;
            }
            else if(amountOfProducts[0] == 2){
                return this.money/6;
            }
            else{
                return this.money/7;
            }

        }
        else if(p.getType() == Product.ProductType.COIN){
            if(amountOfProducts[1] == 0){
                return this.money/4;
            }
            else if(amountOfProducts[1] == 1){
                return this.money/5;
            }
            else if(amountOfProducts[1] == 2){
                return this.money/6;
            }
            else{
                return this.money/7;
            }
        }
        else if(p.getType() == Product.ProductType.STAMP){
            if(amountOfProducts[2] == 0){
                return this.money/4;
            }
            else if(amountOfProducts[2] == 1){
                return this.money/5;
            }
            else if(amountOfProducts[2] == 2){
                return this.money/6;
            }
            else{
                return this.money/7;
            }
        }
        else{
            return 0;
        }
    }

    public void addProducts(ArrayList<Product> array){
        for(Product p: array){
            if(this.products.size() < MAX_SIZE){
                this.products.add(p);
            }
            else{
                System.out.println("ERROR: Maximum amount of products reached");
            }
        }
        this.currentSize = this.products.size();
    }

    public boolean sellProduct(Double price, Product p){

        for(Product product : this.products){
            if(product == p){
                this.products.remove(p);

                this.currentSize = this.products.size();
                this.money += price;
                return true;
            }
        }
        return false;
    }
    public boolean buyProduct(Double price, Product p){
        //checks array isn't full
        if(this.products.size() < MAX_SIZE){
            this.products.add(p);
            this.currentSize += this.products.size();
            if(this.money >= price){

                this.money = (int)(this.money -  price);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            System.out.println("ERROR: Maximum amount of products reached");
            return false;
        }
    }


}
