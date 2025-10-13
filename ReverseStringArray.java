import java.util.Scanner;
import java.util.Arrays;

public class ReverseStringArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of String:");
        int n = sc.nextInt();
        sc.nextLine();
        String arr[] = new String[n];   
        System.out.println("Enter the Strings one by one :");
        for(int i = 0; i<n; i++){
            System.err.println("String " + (i+1) + ":"); //print sting line by line
            arr[i] = sc.nextLine().trim();  // Used trim to remvoe spaces before and after the string    
        }
         System.out.println("\nReverse String Array:");
    for(int i = n-1; i>=0; i--){
        System.out.println(arr[i] + " ");
    }
    System.out.println("\nArray in String format: " + Arrays.toString(arr));
    String rev[] = new String[n];
    // for(int i = 0; i<n; i++){
    //     rev[i] = arr[n-1-i];  
    // }   this will also work but created a new array
    for (int i = 0; i < n / 2; i++) {
    String temp = arr[i];
    arr[i] = arr[n - 1 - i];
    arr[n - 1 - i] = temp;
}
rev = arr;
    System.out.println("\nReversed Array in String format:" + Arrays.toString(rev));
     
    sc.close();

    }
    
}
    

