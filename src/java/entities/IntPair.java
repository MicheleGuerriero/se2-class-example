package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author micheleguerriero
 */
@Entity
public class IntPair implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    private Integer firstOperand;

    @Column(nullable = false)
    private Integer secondOperand;

    @Column(nullable = false)
    private Integer sumOfOperands;

    public Integer getSumOfOperands() {
        return sumOfOperands;
    }

    public void setSumOfOperands(Integer sumOfOperands) {
        this.sumOfOperands = sumOfOperands;
    }

    public Integer getFirstOperand() {
        return this.firstOperand;
    }

    public void setFirstOperand(Integer firstOperand) {
        this.firstOperand = firstOperand;
    }

    public Integer getSecondOperand() {
        return this.secondOperand;
    }

    public void setSecondOperand(Integer secondOperand) {
        this.secondOperand = secondOperand;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntPair)) {
            return false;
        }
        IntPair other = (IntPair) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IntPair[ id=" + id + " ]";
    }

}
