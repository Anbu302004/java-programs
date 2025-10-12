public class SecondLargestElement {

    public static void main(String[] args) {
        int[] arr1 = { 4, 5, 6, 1, 2 };
        int[] arr2 = {};
        int[] arr3 = { 5, 5, 5 };
        int[] arr4 = { 5, 5, 4 };
        int[] arr5 = { -1, -2, -3, 10, 10 };
        int[] arr6 = { -1, -1, -1 };
        System.out.println(findsecondlargestelement(arr1));
        System.out.println(findsecondlargestelement(arr2));
        System.out.println(findsecondlargestelement(arr3));
        System.out.println(findsecondlargestelement(arr4));
        System.out.println(findsecondlargestelement(arr5));
        System.out.println(findsecondlargestelement(arr6));
    }

    private static int findsecondlargestelement(int[] arr) {
        if (arr.length == 0 || arr.length == 1)
            return -1;
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int num : arr) {
            if (max < num) {
                secondMax = max;
                max = num;
            } else if (secondMax < num && num != max) {
                secondMax = num;
            }
        }
        if (secondMax == Integer.MIN_VALUE) {
            return max;
        }
        return secondMax;
    }
}