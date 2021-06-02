package quick_sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    @DisplayName("Быстрая сортировка")
    public void quickSortTest() {
        int[] array = {3, 5, 4, 8, 6, 9, 2, 1, 7};
        int[] exp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        QuickSort.sort(array);
        assertArrayEquals(exp, array);
    }
}