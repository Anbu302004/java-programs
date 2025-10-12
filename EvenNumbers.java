 import java.util.Scanner;
 public class EvenNumbers{
     public static void main(String[] args){
         Scanner sc= new Scanner(System.in);
    System.out.print("Enter a number:");
    int num =sc.nextInt();
    System.out.println("Next 10 even numbers from " +num+ " :");
    if (num % 2!=0){
        num++;
    }
    for (int i =num; i<num+20; i+=2)
    System.out.println(i+ "");
     }
 }