import java.util.Scanner;

public class BinaryToDecimalConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
       
        System.out.print("Enter a binary number as a String: ");
        String binaryString = scanner.nextLine();
        
       
        if (isBinary(binaryString)) {
            
            int decimal = convertBinaryToDecimal(binaryString);
            System.out.println("Decimal equivalent: " + decimal);
        } else {
            System.out.println("Invalid binary number. Please enter only 0s and 1s.");
        }

        scanner.close();
    }

    private static boolean isBinary(String binaryString) {
        return binaryString.matches("[01]+"); 
    }

    private static int convertBinaryToDecimal(String binaryString) {
        return Integer.parseInt(binaryString, 2); 
    }
}
