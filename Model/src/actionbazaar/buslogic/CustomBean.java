package actionbazaar.buslogic;

import javax.annotation.Resource;

import javax.ejb.SessionContext;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class CustomBean {
    
    protected Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    @PersistenceContext(unitName="Model")
    protected EntityManager em;
    @Resource
    protected SessionContext context;

    public CustomBean() {
        super();
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
}
