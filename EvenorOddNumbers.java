import java.util.Scanner;
public class EvenorOddNumbers{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Value to Find Even or Odd:");
        int a = sc.nextInt();
        if (a % 2==0) {
            System.out.println("The Given value is a Even Number:" +a);
        }else{
            System.out.println("The Given value is a odd number:" +a);
        } 
    }
}