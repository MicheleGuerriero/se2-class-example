package it.polimi.classexample.entities;

import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

@Entity(name = "IntPair")
public class IntPair implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public IntPair() {
		super();
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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}