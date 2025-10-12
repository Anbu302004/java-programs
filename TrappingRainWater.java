public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxleft = 0, maxright = 0;
        int watertrapped = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxleft) {
                    maxleft = height[left];
                } else {
                    watertrapped += maxleft - height[left];
                }
                left++; // move left pointer
            } else {
                if (height[right] >= maxright) {
                    maxright = height[right];
                } else {
                    watertrapped += maxright - height[right];
                }
                right--; // move right pointer
            }
        }

        return watertrapped;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println("Trapped Rainwater: " + solution.trap(height));
    }
}
