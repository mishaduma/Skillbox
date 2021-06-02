package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class RabinKarpExtended {
    private String text;
    private TreeMap<Integer, ArrayList<Integer>> number2position;
    private TreeSet<Character> alphabet;
    private int a = 53;
    private long m = 1_000_000_000 + 9;

    public RabinKarpExtended(String text) {
        this.text = text;
        createIndex();

        //Alphabet check
        alphabet = new TreeSet<>();
        for (int i = 0; i < text.length(); i++) {
            alphabet.add(text.charAt(i));
        }
        if (alphabet.size() != number2position.size()) {
            throw new IllegalArgumentException("Wrong alphabet size!");
        }

    }

    public List<Integer> search(String query) {
        ArrayList<Integer> indices = new ArrayList<>();
        //TODO: implement search algorithm

        //Empty string check
        if (text.length() == 0 || query.length() == 0) {
            throw new IllegalArgumentException("Empty text or query!");
        }

        Integer[] queryCharsHash = new Integer[query.length()];

        for (int i = 0; i < query.length(); i++) {
            long hash = charToLong(query.charAt(i)) * a % m;

            queryCharsHash[i] = (int) hash;
        }

        //Wrong char in query check
        for (int i = 0; i < query.length(); i++) {
            if (!number2position.containsKey(queryCharsHash[i])) {
                throw new IllegalArgumentException("Wrong char in query!");
            }
        }

        if (number2position.containsKey(queryCharsHash[0])) {
            List<Integer> charsPositions = number2position.get(queryCharsHash[0]);

            for (int i = 0; i < charsPositions.size(); i++) {
                boolean queryIsFound = true;
                for (int j = 0; j < query.length(); j++) {
                    if (query.charAt(j) != text.charAt(charsPositions.get(i) + j) || charsPositions.get(i) + j + query.length() > text.length()) {
                        queryIsFound = false;
                        break;
                    }
                }
                if (queryIsFound) {
                    indices.add(charsPositions.get(i));
                }
            }
        }
        return indices;
    }

    private void createIndex() {
        //TODO: implement text indexing
        number2position = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            ArrayList<Integer> positions = new ArrayList<>();
            long hash = charToLong(text.charAt(i)) * a % m;

            if (number2position.containsKey((int) hash)) {
                positions = number2position.get((int) hash);
            }
            positions.add(i);
            number2position.put((int) hash, positions);
        }
    }

    private static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }
}