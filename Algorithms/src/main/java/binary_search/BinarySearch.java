package binary_search;

import com.google.common.collect.Ordering;

import java.util.ArrayList;

public class BinarySearch {
    private ArrayList<String> list;

    public BinarySearch(ArrayList<String> list) {
        this.list = list;
    }

    public int search(String query) {
        return search(query, 0, list.size() - 1);
    }

    private int search(String query, int from, int to) {
        //TODO: write code here
        if (Ordering.natural().isOrdered(list)) {
            if (list.size() != 0) {
                int middle = (from + to) / 2;
                if (middle >= 1) {
                    int comparison = query.compareTo(list.get(middle));
                    if (comparison == 0) {
                        return middle;
                    }
                    if (comparison > 0) {
                        return search(query, middle, to);
                    }
                    if (comparison < 0) {
                        return search(query, from, middle);
                    }
                }
            }
        }
        return -1;
    }
}
