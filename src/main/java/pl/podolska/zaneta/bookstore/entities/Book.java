/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
//import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 *
 * @author Zanpo
 */
@Data
//@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class Book implements Serializable {  
   @Id
   @GeneratedValue
   protected long id;
   protected String author;
   protected String title;
   protected String genre;
   
   /*@OneToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   protected InStoreProduct product;
   */
   
}

