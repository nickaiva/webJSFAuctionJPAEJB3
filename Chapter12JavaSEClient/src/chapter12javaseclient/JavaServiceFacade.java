package chapter12javaseclient;

import actionbazaar.buslogic.BidException;

import actionbazaar.persistence.Bid;

import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Item;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JavaServiceFacade {
    private  EntityManagerFactory emf = Persistence.createEntityManagerFactory("actionBazaar");

    public JavaServiceFacade() {
    }
    
    public static void main(String [] args) {
      String userId= "idiot";
      Long itemId = new Long (1);
      Double bidPrice = 2001.50;
         
        final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
        //  TODO:  Call methods on javaServiceFacade here...
        javaServiceFacade.addBid(userId,itemId,bidPrice);
    }
  private  Long addBid(String userId, Long itemId, Double bidPrice) throws BidException {
        final EntityManager em = getEntityManager();
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

    private  EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = getEntityManager().createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    private Object _persistEntity(Object entity) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.persist(entity);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    entity = null;
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return entity;
    }

    public Bid persistBid(Bid bid) {
        return (Bid)_persistEntity(bid);
    }

    private Object _mergeEntity(Object entity) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                em.merge(entity);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    entity = null;
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return entity;
    }

    public Bid mergeBid(Bid bid) {
        return (Bid)_mergeEntity(bid);
    }

    public void removeBid(Bid bid) {
        final EntityManager em = getEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                bid = em.find(Bid.class, bid.getBidId());
                em.remove(bid);
                et.commit();
            } finally {
                if (et != null && et.isActive()) {
                    et.rollback();
                }
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /** <code>select o from Bid o</code> */
    public List<Bid> getBidFindAll() {
        return getEntityManager().createNamedQuery("Bid.findAll").getResultList();
    }
}
