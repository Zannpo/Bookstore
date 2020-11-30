/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import static pl.podolska.zaneta.bookstore.Bookstore.listOfSoldOutBooks;
import pl.podolska.zaneta.bookstore.entities.InStoreProduct;
/**
 *
 * @author Zanpo
 */
public class ShowProducts {   
    
     public List<InStoreProduct> getEverything()
    {           
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();        
        
        TypedQuery <InStoreProduct> query = em.createQuery("select p from InStoreProduct p", InStoreProduct.class);
        List<InStoreProduct> book = query.getResultList(); 
        
        em.getTransaction().commit(); 
        em.close();
        emf.close();
        return book;
    }
     
    
     public List<InStoreProduct> getEveryFutureBook()
    {   EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();        
        
        TypedQuery <InStoreProduct> query = em.createQuery("select p from InStoreProduct p where p.status='planned'", InStoreProduct.class);
        List<InStoreProduct> book = query.getResultList(); 
        
        em.getTransaction().commit(); 
        em.close();
        emf.close();
        return book;
    }
      
       public List<InStoreProduct> getEveryAvailableBook()
    {   EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();     
        em.getTransaction().begin();      
        
        TypedQuery <InStoreProduct> query = em.createQuery("select p from InStoreProduct p where p.status='available'", InStoreProduct.class);
        List<InStoreProduct> book = query.getResultList();       
              
        em.getTransaction().commit(); 
        em.close();
        emf.close();
        return book;
    }
       
        public List<InStoreProduct> getSoldBooks()
    {   EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();     
        em.getTransaction().begin();                           
        
        TypedQuery <InStoreProduct> query = em.createQuery("select p from InStoreProduct p where p.amount=0", InStoreProduct.class);
        List<InStoreProduct> book = query.getResultList();       
        listOfSoldOutBooks = (ArrayList) book;
        listOfSoldOutBooks.add(book);
        
        em.getTransaction().commit(); 
        em.close();
        emf.close();
        return book;
    }
       
      
}
