public class FirstNonRepeating {
    public static void main(String[] args) {
        String str = "abcdabd";
        String str1 = "Anbu";
        System.out.println(findfirstnonrepeating(str));
        System.out.println(findfirstnonrepeating(str1));
    }

    private static Character findfirstnonrepeating(String str) {
        int length = str.length();
        if (length <= 1) {
            return null;
        }
        int[] count = new int[256];
        for (int i = 0; i < length; i++) {
            count[str.charAt(i)]++;
        }
        for (int i = 0; i < length; i++) {
            if (count[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }
        return null;
    }
}