/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zanpo
 */
public class DeleteProducts {
            
     public void deleteSelectedBook(int id)
    {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();        
                              
        em
            .createQuery("delete from InStoreProduct where id="+id)
            .executeUpdate();      
         
        em.getTransaction().commit(); 
        em.close();
        emf.close();    
        
    }    
         public void deleteSoldOutBooks()
    {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();        
                              
        em
            .createQuery("delete from InStoreProduct where status='soldOut'")
            .executeUpdate();      
         
        em.getTransaction().commit(); 
        em.close();
        emf.close();    
        
    }
    
}