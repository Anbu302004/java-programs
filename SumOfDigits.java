import java.util.Scanner;

public class SumOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter a Value:");
        int val = sc.nextInt();
        
        int sumOfDigits = 0;  
        int mainval = val;  
         
        while(mainval != 0){
            sumOfDigits += mainval % 10; 
            mainval /= 10; 
        } 
        System.out.println("Sum of the Digits is: " + sumOfDigits);
        
        sc.close();
    }
}
