/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.podolska.zaneta.bookstore.entities.Book;
import pl.podolska.zaneta.bookstore.entities.InStoreProduct;
/**
 *
 * @author Zanpo
 */
public class CreateProducts {
    
        
     public void createProduct(String author, String title, String genre,LocalDate dateOfPublicity, Double price, int amount, String status){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        
        InStoreProduct product = new InStoreProduct();              
        product.setPrice(price);
        product.setAmount(amount);
        product.setDateOfPublicity(dateOfPublicity);
        product.setStatus(status);   
        
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setGenre(genre);  
        
        product.setBook(book);       
                
        em.getTransaction().begin();
        em.persist(product);
        
        em.getTransaction().commit(); 
        
        
        em.close();
        emf.close(); 
        
    }     
     
}
