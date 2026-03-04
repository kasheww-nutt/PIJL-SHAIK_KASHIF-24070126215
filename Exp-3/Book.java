import java.time.LocalDate;

public class Book  {
    String title;
    String author;
    double price;
    int stockCount;
    String ISBN;
    String genre;
    LocalDate dateofPub;

    // Default constructor
    public Book() {
        title = "Some title";
        price = 100.00;
        genre = "Default Genre";
        author = "xyz";
        ISBN = "IDB000000";
        dateofPub = LocalDate.of(1999, 2, 11);
    }

    // 2-param constructor — throws InvalidBookException
    public Book(String t, double p) throws InvalidBookException {
        if ("".equals(t))
            throw new InvalidBookException("Title cannot be blank.");
        title = t;

        if (p < 0)
            throw new InvalidBookException("Price cannot be negative.");
        price = p;
    }

    // 4-param constructor — throws InvalidPriceException for negative price
    public Book(String t, double p, String i, String g) throws InvalidPriceException, InvalidBookException {
        if ("".equals(t))
            throw new InvalidBookException("Title cannot be blank.");
        title = t;

        if (p < 0)
            throw new InvalidPriceException("Invalid price! Price cannot be negative: " + p);
        price = p;

        ISBN = i;

        if (!g.equalsIgnoreCase("Fiction") && !g.equalsIgnoreCase("Action") && !g.equalsIgnoreCase("Technology") && !g.equalsIgnoreCase("Autobiography"))
            throw new InvalidBookException("Invalid genre. Choose: Fiction, Action, Technology, or Autobiography.");
        genre = g;
    }

    // Copy constructor
    public Book(Book b) {
        title = b.title;
        price = b.price;
        ISBN = b.ISBN;
        genre = b.genre;
        dateofPub = b.dateofPub;
        author = b.author;
        stockCount = b.stockCount;
    }

    // toString for easy printing
    @Override
    public String toString() {
        return "Title: " + title + " | Author: " + author + " | Price: Rs." + price + " | Genre: " + genre + " | ISBN: " + ISBN;
    }
}
