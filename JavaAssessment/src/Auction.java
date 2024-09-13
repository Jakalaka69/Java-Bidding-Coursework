import java.util.HashSet;
import java.util.Random;

public class Auction {
    private int numAgents;
    private static HashSet<Agent> agents = new HashSet<>();
    private static HashSet<Agent> bids = new HashSet<>();
    private static Triple<Agent,Product,Double> highestBid = new Triple<>();


    public int getNumAgents(){
        return this.numAgents;
    }

    public static HashSet<Agent> getAgents() {
        return agents;
    }

    public HashSet<Agent> getBids() {
        return bids;
    }

    public Triple getHighestBid() {
        return highestBid;
    }

    static class Triple<first,second,third>{
        private first Agent;
        private second Product;
        private third bid;
        public void setAgent(first a){
            this.Agent = a;

        }
        public void setProduct(second a){
            this.Product = a;
        }
        public void setBid(third a){
            this.bid = a;
        }


    }
    public Auction(int numOfAgents,int numStartingProducts){
        this.numAgents = numOfAgents;
        for(Agent a: agents){


            a.setCurrentSize(numStartingProducts);
            Random random = new Random();
            int moneyTemp = random.nextInt(500);
            int moneyFinal = 500 + moneyTemp;
            a.setMoney(moneyFinal);

        }
    }
    public static void singleAuction(Agent a){
        Product.ProductType type = Product.randomProductType();

        Agent.setCurrentProductType(type);
        Product p = a.offerProduct(null);

        double topBidSoFar = 0;
        for(Agent agent: agents){
            if(agent == a){
                continue;
            }
            double moneyBid = agent.makeBid(p);

            Triple<Agent, Product, Double> triple = new Triple<>();
            triple.setAgent(agent);
            triple.setProduct(p);
            triple.setBid(moneyBid);

            Auction.bids.add(agent);

            //If two agents have the same bid it will take whoever bid first
            if(moneyBid > topBidSoFar){
                topBidSoFar = moneyBid;
                highestBid.setAgent(agent);

                highestBid.setProduct(p);
                highestBid.setBid(moneyBid);;
            }


        }

        if(!highestBid.Agent.buyProduct(highestBid.bid,p)){
            System.out.println("Agent has insufficient money to buy this product");
        };

        a.sellProduct(highestBid.bid, p);
        
    }
    public void multipleAuctionSimulation(Agent a) {
        if (a.getCurrentSize() == 0) {
            System.out.println("Agent has no products...");
        } else {
            singleAuction(a);

            System.out.println("WINNER OF THE PRODUCT: \n" + highestBid.Product + "\n is " + highestBid.Agent.toString() + " with a winning bid of: " + highestBid.bid);

        }
    }
}
