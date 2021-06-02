package merge_sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeSortTest {

    @Test
    @DisplayName("Сортировка слиянием")
    public void mergeSortTest() {
        int[] array = {3, 5, 4, 8, 6, 9, 2, 1, 7};
        int[] exp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        MergeSort.mergeSort(array);
        Assertions.assertArrayEquals(exp, array);
    }
}