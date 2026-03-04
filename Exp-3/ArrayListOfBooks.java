import java.util.ArrayList;
public class ArrayListOfBooks {

    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();

        // Add valid Book objects
        try {
            Book b1 = new Book("Java Programming", 500.00, "ISBN001", "Technology");
            bookList.add(b1);

            Book b2 = new Book("The Great Gatsby", 300.00, "ISBN002", "Fiction");
            bookList.add(b2);

            Book b3 = new Book("Clean Code", 600.00, "ISBN003", "Technology");
            bookList.add(b3);

            bookList.add(new Book("To Kill a Mockingbird", 350.00, "ISBN004", "Fiction"));
            bookList.add(new Book("Data Structures", 550.00, "ISBN005", "Technology"));
            bookList.add(new Book("Pride and Prejudice", 280.00, "ISBN006", "Fiction"));

        } catch (InvalidBookException | InvalidPriceException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }

        // Try creating a Book with negative price — triggers InvalidPriceException
        System.out.println("=== Testing InvalidPriceException ===");
        try {
            Book badBook = new Book("Bad Book", -50.00, "ISBN999", "Fiction");
            bookList.add(badBook);
        } catch (InvalidPriceException e) {
            System.out.println("Caught InvalidPriceException: " + e.getMessage());
        } catch (InvalidBookException e) {
            System.out.println("Caught InvalidBookException: " + e.getMessage());
        }

        // Display all books
        System.out.println("\n=== All Books ===");
        bookList.forEach(b -> System.out.println(b));

        // Calculate and display average price
        double total = 0;
        for (Book b : bookList) {
            total += b.price;
        }
        double average = total / bookList.size();
        System.out.printf("%n=== Average Price of Books ===%nAverage Price: Rs.%.2f%n", average);

        // Display only Fiction books using forEach()
        System.out.println("\n=== Fiction Books ===");
        bookList.forEach(b -> {
            if (b.genre.equalsIgnoreCase("Fiction")) {
                System.out.println(b);
            }
        });  // ← lambda closes HERE

        // List info
        System.out.println("\nIs the list Empty: " + bookList.isEmpty());
        System.out.println("Size of the list: " + bookList.size());

        // Second ArrayList
        ArrayList<Book> bookList2 = new ArrayList<>();

        try {
            Book b7 = new Book("Ol", 370, "LIFJAF343", "Non-Fiction");
            bookList2.add(b7);
        } catch (InvalidPriceException | InvalidBookException e) {
            System.out.println("Error creating b7: " + e.getMessage());
        }

        try {
            Book b8 = new Book("", 450, "LIFJAF343", "Fiction");
            bookList2.add(b8);
        } catch (InvalidPriceException | InvalidBookException e) {
            System.out.println("Error creating b8: " + e.getMessage());
        }

        // Merge bookList2 into bookList
        bookList.addAll(bookList2);

        // Display updated list
        System.out.println("\n=== Book details after adding b7 and b8 ===");
        bookList.forEach(b -> System.out.println(b));
    }
}


// TRIAL 2

// public class ArrayListOfBooks {

//     public static void main(String[] args) {

//         ArrayList<Book> bookList = new ArrayList<>();

//         // Add valid Book objects
//         try {

//             // Way one (Create an object then add)

//             Book b1= new Book("Java Programming", 500.00, "ISBN001", "Technology");
//             bookList.add(b1);

//             Book b2= new Book("The Great Gatsby", 300.00, "ISBN002", "Fiction");
//             bookList.add(b2);
  
//             Book b3= new Book("Clean Code", 600.00, "ISBN003", "Technology");
//             bookList.add(b3);
        

//             //Way two (directly add the object)
//             bookList.add(new Book("To Kill a Mockingbird", 350.00, "ISBN004", "Fiction"));
//             bookList.add(new Book("Data Structures", 550.00, "ISBN005", "Technology"));
//             bookList.add(new Book("Pride and Prejudice", 280.00, "ISBN006", "Fiction"));
//         } catch (InvalidBookException | InvalidPriceException e) {
//             System.out.println("Error adding book: " + e.getMessage());
//         }

//         // Try creating a Book with negative price — triggers InvalidPriceException
//         System.out.println("=== Testing InvalidPriceException ===");
//         try {
//             Book badBook = new Book("Bad Book", -50.00, "ISBN999", "Fiction");
//             bookList.add(badBook);
//         } catch (InvalidPriceException e) {
//             System.out.println("Caught InvalidPriceException: " + e.getMessage());
//         } catch (InvalidBookException e) {
//             System.out.println("Caught InvalidBookException: " + e.getMessage());
//         }

//         // Display all books
//         System.out.println("\n=== All Books ===");
//         bookList.forEach(b -> System.out.println(b));

//         // Calculate and display average price
//         double total = 0;
//         for (Book b : bookList) {
//             total += b.price;
//         }
//         double average = total / bookList.size();
//         System.out.printf("%n=== Average Price of Books ===%nAverage Price: Rs.%.2f%n", average);

//         // Display only Fiction books using forEach()
//         System.out.println("\n=== Fiction Books ===");
//         bookList.forEach(b -> {
//             if (b.genre.equalsIgnoreCase("Fiction")) {
//                 System.out.println(b);
//             }
//         });
//         //List Size

//         System.out.println("Is the list Empty:"+ bookList.isEmpty());
//         System.out.println("Size of the book:"+ bookList.size());
//         ArrayList<Book> bookList2 = new ArrayList<>();

//             // CORRECT — wrap in try-catch
//         try {
//             Book b7 = new Book("Ol", 370, "LIFJAF343", "Non-Fiction");
//             bookList2.add(b7);
//         } 
//         catch (InvalidPriceException | InvalidBookException e) 
//         {
//             System.out.println("Error creating b7: " + e.getMessage());
//         }

//         try 
//         {
//             Book b8 = new Book("", 450, "LIFJAF343", "Fiction");
//             bookList2.add(b8);
//         }
//         catch (InvalidPriceException | InvalidBookException e) 
//         {
//             System.out.println("Error creating b8: " + e.getMessage());
//         }
//             bookList.addAll(bookList2);
        

//         // catch(InvalidBookException ib){
//         //     System.out.println(ib.getMessage());
//         // }
//         // Displaying the updated list
//         System.out.println("book detauls from the arraylist after adding b7 and b8");
//         bookList.forEach(b->System.out.println(b));
//     };   

// }