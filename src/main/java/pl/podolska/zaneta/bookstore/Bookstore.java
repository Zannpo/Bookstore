/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static pl.podolska.zaneta.bookstore.Bookstore.getDate;
import pl.podolska.zaneta.bookstore.entities.InStoreProduct;

/**
 *
 * @author Zanpo
 */
public class Bookstore {

    /**
     * @param args the command line arguments
     */
    static ArrayList listOfSoldOutBooks = new ArrayList();
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        ShowProducts show = new ShowProducts();
        AlterInfoAboutProducts alter = new AlterInfoAboutProducts();
        DeleteProducts delete = new DeleteProducts();
        String decision;  
        long id;
        
        OUTER:
        while (true) {            
            System.out.println("A)Add a new title to the store B)Show every title from the store");            
            System.out.println("C)Show every available title from the store  D)Show every future title from the store");
            System.out.println("E)Decrease amount of a selected title F)Increase amount of a selected title");
            System.out.println("G)Delete sold out titles from the store's database H)Show sold out titles");
            System.out.println("I)Automatically update the list and statuses of the titles J)Delete selected title");
            System.out.println("X)Close program");
            decision = scan.next();
            
            switch (decision) {
                case "X":
                    break OUTER;
                case "A": // Adding a new title
                    String title,author,genre,status;
                    Double price;
                    int amount, year,month,day;
                    LocalDate dateOfPublicity, todaysDate;
                    todaysDate = LocalDate.now();
                    
                    System.out.println("Enter the author's name");
                    author = scan.next();
                    scan.nextLine();
                    
                    System.out.println("Enter the title of the book");
                    title = scan.nextLine();
                    
                    System.out.println("Enter the genre of the book");
                    genre = scan.nextLine();
                    
                    System.out.println("Enter the price of the book");
                    price = scan.nextDouble();
                    
                    System.out.println("Enter the amount of the book");
                    amount = scan.nextInt();  
                    
                    System.out.println("Enter the year of the publicity of the book");
                    year = scan.nextInt();
                    
                    System.out.println("Enter the month of the publicity of the book");
                    month = scan.nextInt();
                    
                    System.out.println("Enter the day of the publicity of the book");
                    day = scan.nextInt();
                    
                    dateOfPublicity = getDate(year,month,day);
                    if(amount == 0 || author == null || title == null || genre == null
                            || price == 0|| year == 0|| month ==0|| day == 0)
                    {
                        System.out.println("Invalid data!");
                    }                    
                    else                       
                    if(todaysDate.compareTo(dateOfPublicity) >= 0)
                    {
                        CreateProducts create = new CreateProducts();
                        status = "available";
                        create.createProduct(author, title, genre, dateOfPublicity, price, amount, status);
                                                
                    }
                    else if(todaysDate.compareTo(dateOfPublicity) < 0)
                    {
                        CreateProducts create = new CreateProducts();
                        status = "planned";                        
                        create.createProduct(author, title, genre, dateOfPublicity, price, amount, status);
                    }     break;
                case "B": //Show everything
                    {                        
                        List<InStoreProduct> products = show.getEverything();
                        System.out.println("Show every title from the store:");
                        products.stream().forEach(p->System.out.println(p.toString()));                        
                        break;
                    }
                case "C": //Show available products
                    {                        
                        List<InStoreProduct> products = show.getEveryAvailableBook();
                        System.out.println("Show available titles:");
                        products.stream().forEach(p->System.out.println(p.toString()));
                        break;
                        
                    }
                case "D": //Show future products
                    {                        
                        List<InStoreProduct> books = show.getEveryFutureBook();
                        System.out.println("Show every planned book:");
                        books.stream().forEach(p->System.out.println(p.toString()));
                        break;
                    }
                case "E": //Decrease the amount
                    {                        
                        int amountOfSoldOutBooks;                        
                        System.out.println("Enter id of the title which you want to alter.");
                        id = scan.nextLong();
                        System.out.println("Enter the amount of which you want to change the amount");
                        amountOfSoldOutBooks = scan.nextInt();
                        if(id == 0 || amountOfSoldOutBooks == 0)
                        {
                            System.out.println("Invalid data!");
                        }
                        else
                        alter.updateSoldBook(id,amountOfSoldOutBooks);
                        break;
                    }
                case "F": //Increase the amount
                    {                        
                        int amountOfNewBooks;                        
                        System.out.println("Enter id of the title which you want to alter.");
                        id = scan.nextLong();
                        System.out.println("Enter the amount of which you want to change the amount");
                        amountOfNewBooks = scan.nextInt();
                        if(id == 0 || amountOfNewBooks == 0)
                        {
                            System.out.println("Invalid data!");
                        }
                        alter.updateAvailableBook(id, amountOfNewBooks);
                        break;
                    }
                case "G": //Delete sold out titles
                    System.out.println("Are you sure that you want to delete sold out titles?");
                    decision = scan.next();
                    if(decision.equals("yes"))
                    {                      
                      delete.deleteSoldOutBooks();
                    }
                     else if(decision.equals("no")==false||decision.isEmpty()==true)
                    {
                        System.out.println("There is no such an option!");
                    } 
                                             
                    System.out.println("Do you want to delete these titles from the list?");
                    decision = scan.next();
                    if(decision.equals("yes"))
                    {                        
                        listOfSoldOutBooks.clear();
                    }
                    else if(decision.equals("no")==false||decision.isEmpty()==true)
                    {
                        System.out.println("There is no such an option!");
                    }    break;
                case "H": // Show the list of Sold Out products
                    
                    if(listOfSoldOutBooks.isEmpty()==true)
                    {
                        System.out.println("The list is empty.");
                    }   
                    System.out.println(listOfSoldOutBooks);
                    break;
                    
                case "I": //Update of list and statuses
                    
                    alter.updateListAndStatusOfBooks();                   
                    
                    show.getSoldBooks();
                    
                    break;
                case "J": //Delete single product
                    {                      
                        int productId;
                        System.out.println("Enter id of the title which you want to delete");
                        productId = scan.nextInt();
                        
                        if(productId == 0)
                        {
                            System.out.println("Invalid data!");
                        }
                        else
                        {                           
                            delete.deleteSelectedBook(productId);                            
                        }                       
                        break;
                    }

                default:
                    System.out.println("Wrong option!");
                    break;
            }

        }
    }
         
       public static LocalDate getDate(int year, int month, int day) {
               
           LocalDate dateOfPublicity;
           
           dateOfPublicity = LocalDate.of(year, month, day);
           
        return dateOfPublicity;
     }
      
}
    

