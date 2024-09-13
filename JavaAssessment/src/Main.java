import java.awt.print.Book;
import java.util.ArrayList;
import java.util.concurrent.locks.StampedLock;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Product product1 = new Product(Product.ProductType.BOOK,"CHERUB");
        Product product2 = new Product(Product.ProductType.COIN,"Jay");
        Product product3 = new Product(Product.ProductType.STAMP,"New Zealand");
        Product product4 = new Product(Product.ProductType.BOOK,"Normal People");
        Product product5 = new Product(Product.ProductType.STAMP,"Spain");
        Product product6 = new Product(Product.ProductType.STAMP,"South Africa");

        Product.writeToFile(product1,"product1");
        Product.writeToFile(product2,"product2");
        Product.writeToFile(product3,"product3");

        product1.setSalePrice(100);
        product2.setSalePrice(50);
        product3.setSalePrice(200);



        System.out.println(Product.readFromFile("product1"));
        System.out.println(Product.readFromFile("product2"));
        System.out.println(Product.readFromFile("product3"));

        //Creating new products to be sold


        //Creating new agent and giving it 6 products and 1000 money
        Agent myAgent = new Agent("Barry");
        myAgent.setMoney(1000);
        myAgent.buyProduct(50.0,product1 );
        myAgent.buyProduct(50.0,product4 );
        myAgent.buyProduct(50.0,product2 );
        myAgent.buyProduct(50.0,product3 );
        myAgent.buyProduct(50.0,product5 );
        myAgent.buyProduct(50.0,product6 );

        //Printing out all the BOOKs this agent has
        System.out.println("=====BOOKS=====");
        for(Product p: myAgent.getProducts()){
            if(p.getType() == Product.ProductType.BOOK){
                System.out.println(p);
            }
        }

        //displaying which Stamp is sold for question 2.9.c
        System.out.println("=====STAMP SOLD=====");
        Product temp = myAgent.offerProduct(Product.ProductType.STAMP);
        System.out.println(temp);

        //sells the stamp
        System.out.println(myAgent.sellProduct(100.0,temp));
        System.out.println("=====STAMPS LEFT=====");

        //Prints all the stamps the agent has left
        for(Product p:myAgent.getProducts()){

            if(p.getType() == Product.ProductType.STAMP){
                System.out.println(p);
            }
        }

        //AUCTION


        //Creating agents for the auction
        Agent agent1 = new Agent("John");
        Agent agent2 = new Agent("Batholomew");
        Agent agent3 = new Agent("Mr Risk");
        Agent agent4 = new Agent("Loser Larry");



        //adding agents to array of agents
        Auction.getAgents().add(agent1);
        Auction.getAgents().add(agent2);
        Auction.getAgents().add(agent3);
        Auction.getAgents().add(agent4);

        //giving each agent different products
            ArrayList<Product> productArray = new ArrayList<>();
            productArray.add(product1);
            productArray.add(product2);
            productArray.add(product3);
            productArray.add(product4);
            agent1.addProducts(productArray);

            productArray = new ArrayList<>();
            productArray.add(product1);
            productArray.add(product5);
            productArray.add(product6);
            productArray.add(product4);
            agent2.addProducts(productArray);

            productArray = new ArrayList<>();
            productArray.add(product1);
            productArray.add(product5);
            productArray.add(product3);
            productArray.add(product4);
            agent3.addProducts(productArray);

            productArray = new ArrayList<>();
            productArray.add(product2);
            productArray.add(product6);
            productArray.add(product3);
            productArray.add(product1);
            agent4.addProducts(productArray);


        //for each agent, have them auction off one of their products
        int count = 0;
        for(Agent a:Auction.getAgents()){
            count++;
            System.out.println("\n+++++AUCTION "+count+ "+++++\nAgent offering up their item: "+ a.toString());
            Auction auction = new Auction(4,4);
            auction.multipleAuctionSimulation(a);
        };
    }
}