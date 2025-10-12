import java.util.Scanner;
import java.util.ArrayList;

public class VowelsAndConsonants {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a String:");
        String str = sc.nextLine();
        int vowels = 0, consonants = 0;
        ArrayList<Character> vowel = new ArrayList<>();
        ArrayList<Character> consonant = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (Character.isLetter(ch)) {
                ch = Character.toLowerCase(ch);
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                    vowel.add(ch);
                } else { 
                    consonants++;
                    consonant.add(ch);
                } 
            }
        }
        sc.close();
        System.out.println("\nNumber of vowels: " + vowels); 
        System.out.println("Letters in vowels present in given string:" + vowel);
        System.out.println("Number of consonants: " + consonants);  
        System.out.println("Letters in consonants present in given string:" + consonant);
    }
}
