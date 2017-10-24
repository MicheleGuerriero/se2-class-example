package it.polimi.classexample.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.polimi.classexample.entities.Item;

@Stateless
public class AdminBean {

    @PersistenceContext(unitName = "online-store")
    private EntityManager em;
    
    public AdminBean() {

    }
    
    public Item getFromId(String itemId) {
        try {
            return (Item) this.em.createQuery("SELECT i FROM Item i WHERE i.itemId=:itemId")
                    .setParameter("itemId", itemId).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public Item insertItem(String description, Integer quantity, Double pricePerUnit) {
        Item i = new Item();
        i.setDescription(description);
        i.setQuantity(quantity);
        i.setPricePerUnit(pricePerUnit);
        em.persist(i);
        return i;
    }
    
    public Item updateItemQuantity(String itemId, Integer newQuantity) {
        Item toUpdate = getFromId(itemId);
        if (toUpdate != null) {
            toUpdate.setQuantity(newQuantity);
            em.persist(toUpdate);
            return toUpdate;
        } else {
            return null;
        }
    }
    
    public List<Item> selectExpiringProducts(Integer quantityLowerBound) {
        List<Item> toReturn = new ArrayList<Item>();
        Item temp;
        Iterator<Item> iter = getItemList().iterator();
        while(iter.hasNext()){
            temp = iter.next();
            if(temp.getQuantity() < quantityLowerBound){
                toReturn.add(temp);
            }
        }
        return toReturn;
    }
    
    public List<Item> getItemList() {
        try {
            return (List<Item>) this.em.createQuery("SELECT i FROM Item i").getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<Item>();
        }
    }

}
