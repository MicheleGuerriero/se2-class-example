package beans;

import entities.IntPair;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author micheleguerriero
 */
@Stateless
public class AdditionBean {

    private Integer i, j, k;

    @PersistenceContext(unitName = "class-example")
    private EntityManager em;

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public Integer getK() {
        return k;
    }

    public IntPair add() {
        if (i != null & j != null) {
            IntPair toPersist = new IntPair();

            k = i + j;

            toPersist.setFirstOperand(i);
            toPersist.setSecondOperand(j);
            toPersist.setSumOfOperands(k);
            em.persist(toPersist);
            return toPersist;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<IntPair> getPairs() {
        try {
            return (List<IntPair>) this.em.createQuery("SELECT p FROM IntPair p").getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<IntPair>();
        }
    }
}
