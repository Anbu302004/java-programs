 import java.util.Scanner;
 class PrimePalindromeFiboncaiiSwitchCase{
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
    System.out.println("Choose the Operation:");
    System.out.println("1.PALINDROME");
    System.out.println("2.PRIME");
    System.out.println("3.FIBONACCI");
    int option= sc.nextInt();
    
    System.out.println("Enter a Value:");
    int n = sc.nextInt();
    
        switch(option){
            case 1:
                if(isPalindrome(n)){
                    System.out.println("PALINDROME");
                }else{
                    System.out.println("NOT A PALINDROME");
                }
            break;
            case 2:
                if(isPrime(n)){
                    System.out.println("PRIME");
                }else{
                    System.out.println("NOT A PRIME");
                }
            break;
            case 3:
                if(isFibonacci(n)){
                    System.out.println("FIBONACCI");
                }else{
                    System.out.println("NOT A FIBONACCI");
                }
            break;
            default:
            System.out.println("INVALID>>>>>");
            break;
        }
            
    }
 }