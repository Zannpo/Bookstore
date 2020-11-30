/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author Zanpo
 */

@Entity
@Data
public class InStoreProduct implements Serializable  {
   @Id
   @GeneratedValue
   protected long id;   
   protected double price;
   protected LocalDate dateOfPublicity;
   protected int amount;
   protected String status;
   
   @OneToOne(cascade = CascadeType.ALL)// fetch = FetchType.LAZY)
   protected Book book;
    
}
