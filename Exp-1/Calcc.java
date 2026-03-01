import java.util.Scanner;

public class Calcc {
    
    // Addition function
    public double add(double n1, double n2) {
        return n1 + n2;
    }

    // Subtraction function
    public double sub(double n1, double n2) {
        return n1 - n2;
    }

    // Multiplication function
    public double mul(double n1, double n2) {
        return n1 * n2;
    }

    // Division function
    public double div(double n1, double n2) {
        return n1 / n2;
    }

    // Modulus function
    public double mod(double n1, double n2) {
        return n1 % n2;
    }

    // Main class definition
    public static void main(String[] args) {
        Calcc calcc = new Calcc();
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter the first number: ");
        double n1 = scan.nextDouble();
        
        System.out.print("Enter the second number: ");
        double n2 = scan.nextDouble();
        
        // Error handling for division/modulus by zero
        if (n2 == 0) {
            System.out.println("Error: Cannot divide or modulus by zero. Please enter a non-zero second number.");
            scan.close();
            return;
        }
        
        System.out.print("Enter operation (1=Add, 2=Sub, 3=Mul, 4=Div, 5=Mod): ");
        int choice = scan.nextInt();
        
        double result = 0;
        String operation = "";
        
        switch (choice) {
            case 1:
                result = calcc.add(n1, n2);
                operation = "+";
                break;
            case 2:
                result = calcc.sub(n1, n2);
                operation = "-";
                break;
            case 3:
                result = calcc.mul(n1, n2);
                operation = "*";
                break;
            case 4:
                result = calcc.div(n1, n2);
                operation = "/";
                break;
            case 5:
                result = calcc.mod(n1, n2);
                operation = "%";
                break;
            default:
                System.out.println("Invalid choice! Please run again and enter 1-5.");
                scan.close();
                return;
        }
        
        System.out.printf("%.2f %s %.2f = %.2f%n", n1, operation, n2, result);
        
        scan.close();
    }
}
