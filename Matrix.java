public class Matrix {
    public static void main(String[] args) {
        int arr[][] = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        int n = arr.length;
        int uppersum = 0, lowersum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j <= n - i - 1) {
                    uppersum += arr[i][j];
                }
                if (j >= n - i - 1) {
                    lowersum += arr[i][j];
                }
            }
        }
        System.out.println("upper Triangle sum: " + uppersum);
        System.out.println("lower Triangle sum: " + lowersum);
        int maxsum;
        if (uppersum > lowersum) {
            maxsum = lowersum;
        } else {
            maxsum = lowersum;
        }
        System.out.println("Maximum Sum: " + maxsum);
    }
}