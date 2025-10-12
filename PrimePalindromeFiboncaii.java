 import java.util.Scanner;
 class PrimePalindromeFiboncaii{
    static boolean isPalindrome(int n){
        int num=n;
        int rev=0;
        while(n>0){
            int value= n%10;
            rev=rev*10+value;
            n/=10;
        }
        return num==rev;
    }
    static boolean isPrime(int n){
        if(n<=1)return false;
        for(int i=2;i<=n/2;i++){
            if(n%i==0)return false;
        }
        return true;
    }
    static boolean isFibonacci(int n){
        int a=0,b=1;
        while(b<n){
            int c=a+b;
            a=b;
            b=c;
        }
        return b==n;
    }
    public static void main(String [] args){
            Scanner sc= new Scanner(System.in);
        System.out.println("Enter a Value");
            int n = sc.nextInt();
        if(isPalindrome(n)){
            System.out.println("PALINDROME");
        }else{
            System.out.println("NOT A PALINDROME");
        }
        if(isPrime(n)){
            System.out.println("PRIME");
        }else{
            System.out.println("NOT A PRIME");
        }
        if(isFibonacci(n)){
            System.out.println("FIBONACII");
        }else{
            System.out.println("NOT A FIBONACCI");
        }
    }
 }