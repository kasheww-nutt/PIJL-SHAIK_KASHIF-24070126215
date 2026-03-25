import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // --- First Vector ---
            System.out.print("Enter dimension for Vector 1 (2 or 3): ");
            int dim1 = sc.nextInt();
            double[] comp1 = new double[dim1];
            System.out.println("Enter " + dim1 + " components for Vector 1:");
            for (int i = 0; i < dim1; i++) {
                System.out.print("  Component " + (i + 1) + ": ");
                comp1[i] = sc.nextDouble();
            }
            Vector v1 = new Vector(comp1);

            // --- Second Vector ---
            System.out.print("\nEnter dimension for Vector 2 (2 or 3): ");
            int dim2 = sc.nextInt();
            double[] comp2 = new double[dim2];
            System.out.println("Enter " + dim2 + " components for Vector 2:");
            for (int i = 0; i < dim2; i++) {
                System.out.print("  Component " + (i + 1) + ": ");
                comp2[i] = sc.nextDouble();
            }
            Vector v2 = new Vector(comp2);

            // --- Menu ---
            System.out.println("\nChoose operation:");
            System.out.println("  1. Add");
            System.out.println("  2. Subtract");
            System.out.println("  3. Dot Product");
            System.out.print("Enter choice (1/2/3): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Result = ");
                    v1.add(v2).display();
                    break;
                case 2:
                    System.out.print("Result = ");
                    v1.subtract(v2).display();
                    break;
                case 3:
                    System.out.println("Dot Product = " + v1.dotProduct(v2));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } catch (VectorException e) {
            System.out.println("VectorException: " + e.getMessage());
        }

        sc.close();
    }
}
