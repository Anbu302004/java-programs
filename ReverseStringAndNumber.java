import java.util.Scanner;

public class ReverseStringAndNumber {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String:");
        String str = sc.nextLine();
        String strReversed = "";
        for(int i = str.length() -1; i>=0; i--){
            strReversed = strReversed + str.charAt(i);
        }
        System.out.println("Original String:" +str);
        System.out.println("Reversed String:" +strReversed);
        
        System.out.println("Enter a Number:");
        int num = sc.nextInt();
        int originalNum = num;
        int revesedNum = 0;

        while(num !=0){ 
            int digit = num % 10; 
            revesedNum = revesedNum * 10 + digit;
            num = num / 10;
        }
        System.out.println("Original Number:" + originalNum);
        System.out.println("Rversed Number:" + revesedNum);
        sc.close();
    }
    
}
