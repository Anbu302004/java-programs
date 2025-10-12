import java.util.Scanner;

public class PrimeAndPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter a value:");
        String str = sc.next();
        
        String rev = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);
        }
        
        if (str.equals(rev)) {
            System.out.println("This is a Palindrome");
        } else {
            System.out.println("This is not a Palindrome");
        }

        boolean isPrime = true;
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                num = num * 10 + (str.charAt(i) - '0');
            } else {
                isPrime = false;
                break;
            }
        }
        
        if (isPrime) {
            if (num <= 1) {
                isPrime = false;
            } else {
                for (int i = 2; i * i <= num; i++) {
                    if (num % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            if (isPrime) {
                System.out.println("Prime");
            } else {
                System.out.println("Not Prime");
            }
        } else {
            System.out.println(" Not valid.");
        }
    }
}
