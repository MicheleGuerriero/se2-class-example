package beans;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author micheleguerriero
 */
@Stateless
public class AdditionBeanBasic {

    private Integer i, j, k;

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

    public Integer add() {
        if (i != null & j != null) {

            k = i + j;
            return k;
        } else {
            return null;
        }
    }
}
