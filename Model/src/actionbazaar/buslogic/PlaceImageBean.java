package actionbazaar.buslogic;


import actionbazaar.persistence.Image;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless(name = "PlaceImage", mappedName = "ejb3inaction-Model-PlaceImage")
public class PlaceImageBean extends CustomBean implements PlaceImage, PlaceImageLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public PlaceImageBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @RolesAllowed(value = { "SELLER", "ADMINISTRATOR" })
    public int placeImage(int objectId,
                                 String description,
                                 byte[] image) {
        
         Image im = new Image();
         im.setDescription(description);
         im.setObjectId(objectId);
         im.setImage(image);
                              try {
                                  em.persist(im);
                                  //em.flush();
                                  if (logger.isDebugEnabled())
                                      logger.debug("imageId " + im.getId() + " persisted");
                                  return im.getId();
                              } catch (Exception e) {
                                  context.setRollbackOnly();
                                  logger.info(e.getMessage());
                              }
                          return 0;
               }
    
    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    @Override
    public List<byte[]> getImagesByItemId(Long itemId) {
        return (List<byte[]>)em.createNamedQuery("Image.getImagesByItemId").setParameter("itemId",
                                                                                 itemId).getResultList();
    }
   

    public Image persistImage(Image image) {
        em.persist(image);
        return image;
    }

    public Image mergeImage(Image image) {
        return em.merge(image);
    }

    public void removeImage(Image image) {
        image = em.find(Image.class, image.getId());
        em.remove(image);
    }

    /** <code>select o from Image o</code> */
    public List<Image> getImageFindAll() {
        return em.createNamedQuery("Image.findAll").getResultList();
    }
}
