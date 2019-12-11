/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.valo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Jimmy
 */
public class QuantityItem extends Item implements Serializable{
    private int quantity;

    public QuantityItem() {
    }
    
    public QuantityItem(Item other) {
        setItemId(other.getItemId());
        setItemDescription(other.getItemDescription());
        setBrand(other.getBrand());
        setPrice(other.getPrice());
        setPoints(other.getPoints());
        
        quantity = 0;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public BigDecimal getTotalPrice() {
        return getPrice().multiply(new BigDecimal(quantity));
    }
    
    public int getTotalPoints() {
        return getPoints() * quantity;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        return super.equals(obj);
    }
}
