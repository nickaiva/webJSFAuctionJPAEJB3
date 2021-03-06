package chapter12javaseclient;


import actionbazaar.buslogic.BidException;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Item;

import java.util.Hashtable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PlaceBidBeanJavaSE {
  private static EntityManagerFactory emf;
  private static EntityManager em;
  private static Hashtable emProps = new Hashtable();
     public static void main(String[] args) {
                String userId= "idiot";
                Long itemId = new Long (2);
                Double bidPrice = 2002.50;
        try {
                if (emf == null){
                  emf = Persistence.createEntityManagerFactory("actionBazaar");
                  System.out.println("EntityManagerFactory created!");
                  }
                getEntityManager();
                System.out.println("EntityManager created!");
                addBid(userId,itemId,bidPrice);
                commitTransaction();

          } finally {        
                  // close the EntityManager when done
                  em.close();
                  emf.close();
              }
    }
      private static void getEntityManager() {

        try {
            
            System.out.println("Creating entity manager");
            em = emf.createEntityManager(emProps);//if argument is missing exception is thrown!
            em.getTransaction().begin();
        } catch (Exception ne) {
            // TODO: Add catch code
            ne.printStackTrace();
        }

       }
       private static void commitTransaction() {
        em.getTransaction().commit();
       }

       private static Long addBid(String userId, Long itemId, Double bidPrice) throws BidException {
           Item item = em.find(Item.class,itemId);
           if (item == null) 
              throw new BidException("Invalid Item Id");

           Bidder bidder = em.find(Bidder.class,userId);
           if (bidder == null) 
              throw new BidException("Invalid Bidder Id");
           Bid bid = new Bid();
           bid.setItem(item);
           bid.setBidBidder(bidder);
           bid.setBidPrice(bidPrice);
           em.persist(bid);
           return bid.getBidId();
           }
    
}
