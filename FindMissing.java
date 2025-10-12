public class FindMissing {
    public static void main(String[] args) {
        int[] arr = { 3, 0, 1 };
        int[] arr1 = {};
        int[] arr2 = { 1, 2, 3, 0 };
        int[] arr3 = { 1, 2 };
        System.out.println(FindMissingElement(arr));
        System.out.println(FindMissingElement(arr1));
        System.out.println(FindMissingElement(arr2));
        System.out.println(FindMissingElement(arr3));
    }

    private static int FindMissingElement(int[] arr) {
        int length = arr.length;
        if (length == 0) {
            return 0;
        }
        int total = length * (length + 1) / 2;
        for (int num : arr) {
            total -= num;
        }
        return total;
    }
}
