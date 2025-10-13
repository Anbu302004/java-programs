import java.util.Scanner;
import java.util.Arrays;

public class ReverseStringArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of String:");
        int n = sc.nextInt();
        String arr[] = new String[n];
        System.out.println("Enter the String:");
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextLine();     
        }
         System.out.println("\nReverse String Array:");
    for(int i = n-1; i>=0; i--){
        System.out.println(arr[i] + " ");
    }
    System.out.println("\nArray in String format: " + Arrays.toString(arr));
    sc.close();

    }
    
}
    

