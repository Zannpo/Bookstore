/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore;

import java.time.LocalDate;
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
public class AlterInfoAboutProducts {    
        
    public void updateSoldBook(long id, int amountOfSoldBooks)
    {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();        
        int newAmount, oldAmount;                
          
         
          InStoreProduct product= (InStoreProduct)em.find(InStoreProduct.class, id);
          oldAmount = product.getAmount();
          try 
          {
               newAmount = oldAmount - amountOfSoldBooks;
               if(newAmount < 0)
               {
                   throw new WrongValueException();
               }
               else if(newAmount == 0)
               {
                   em
                    .createQuery("update InStoreProduct set amount ="+newAmount+" where id="+id)
                    .executeUpdate();
                   em
                    .createQuery("update InStoreProduct set status='soldOut' where amount=0 and id="+id)
                    .executeUpdate();  
               }
               else if(newAmount > 0)
               {
                   em
                    .createQuery("update InStoreProduct set amount ="+newAmount+" where id="+id)
                    .executeUpdate();
               }
          }               
          catch (WrongValueException e) {
                System.out.println(e.getMessage());
                }             
                    
          
        em.getTransaction().commit(); 
        em.close();
        emf.close();      
    }
        
        public void updateAvailableBook(long id, int amountOfTheNewProducts)
    {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();        
        int newAmount, oldAmount;                
          
         
          InStoreProduct product= (InStoreProduct)em.find(InStoreProduct.class, id);
          oldAmount = product.getAmount();
          newAmount = oldAmount + amountOfTheNewProducts;
          em
            .createQuery("update InStoreProduct set amount ="+newAmount+" where id="+id)
            .executeUpdate();
          
          if(newAmount > 0)
          {  
            em
            .createQuery("update InStoreProduct set status='available' where amount > 0 and id="+id)
            .executeUpdate();           
          }
                
        em.getTransaction().commit(); 
        em.close();
        emf.close();      
    }
        
           public void updateListAndStatusOfBooks()
    {   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydatabasecnn");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();         
                              
        em
            .createQuery("update InStoreProduct set status='soldOut' where amount = 0")
            .executeUpdate();        
                
        em
            .createQuery("update InStoreProduct set status='available' where amount > 0 and status='soldOut'")
            .executeUpdate();                   
                       
        
        TypedQuery <InStoreProduct> query = em.createQuery("select p from InStoreProduct p where p.status='available'", InStoreProduct.class);
        List<InStoreProduct> book = query.getResultList(); 
        if(listOfSoldOutBooks.contains(book))
        {
            listOfSoldOutBooks.remove(book);
        }
        TypedQuery <InStoreProduct> query2 = em.createQuery("select p from InStoreProduct p where p.status='SoldOut'", InStoreProduct.class);
        List<InStoreProduct> book2 = query2.getResultList();
        listOfSoldOutBooks.add(book2);
        
        em.getTransaction().commit(); 
        em.close();
        emf.close();    
        
    }
}