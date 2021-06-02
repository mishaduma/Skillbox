package array_max_value;

public class ArrayMaxValue {
    public static int getMaxValue(int[] values) {
        int maxValue = Integer.MIN_VALUE;
        if (values.length == 0 || values == null) {
            throw new IllegalArgumentException("Array is empty or null!");
        }
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}