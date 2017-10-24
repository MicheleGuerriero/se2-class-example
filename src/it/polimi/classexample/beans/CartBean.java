package it.polimi.classexample.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.classexample.entities.Item;

@Stateful
public class CartBean {
    
    @PersistenceContext(unitName = "online-store")
    private EntityManager em;

    List<Item> cart = new ArrayList<Item>();

    public void addItem(Integer itemId) {
        Item tmp = (Item) this.em.createQuery("SELECT i FROM Item i WHERE i.itemId=:itemId")
                .setParameter("itemId", itemId).getSingleResult();

        if(tmp != null)
            cart.add(tmp);
    }

    public void removeElement(Integer itemId) {
        int objectIndex = -1;
        
        for(Item i: cart){
            if(i.getItemId().equals(itemId)){
                objectIndex = cart.indexOf(i);
            }
        }
        
        if(objectIndex != -1){
            cart.remove(cart.get(objectIndex));
        }
    }

    public List<Item> getElements() {
        return cart;
    }
    
    public Double sumElements() {
        
        Double sum = new Double(0.0);
        for (Item i : cart) {
            sum = sum + i.getPricePerUnit();
        }   
        
        return sum;
    }
    
    public Integer getNumberOfItemsInCart() {
        return cart.size();
    }
    
}
