package suffix_tree;

import java.util.*;

public class SuffixTree {
    private String text;
    private ArrayList<Node> nodes;
    private Node root;

    private ArrayList<String> uniqueWords;
    private HashMap<String, ArrayList<Integer>> wordsAndPos;
    private TreeSet<String> prefixes;
    private ArrayList<String> checkPrefixes;

    public SuffixTree(String text) {
        this.text = text;
        nodes = new ArrayList<>();
    }

    public void build() {
        //TODO

        //create map of words and it's positions
        if (!text.equals("") && !text.isEmpty()) {
            String[] words = text.split(" ");
            wordsAndPos = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                ArrayList<Integer> wordPos = new ArrayList<>();
                int nextIndex = text.indexOf(words[i]);
                while (nextIndex != -1) {
                    wordPos.add(nextIndex);
                    nextIndex = text.indexOf(words[i], nextIndex + 1);
                }
                wordsAndPos.put(words[i], wordPos);
            }

            uniqueWords = new ArrayList<>(wordsAndPos.keySet());

            //create all prefixes
            prefixes = new TreeSet<>();
            for (int i = 0; i < uniqueWords.size(); i++) {
                for (int j = i + 1; j < uniqueWords.size(); j++) {
                    if (!commonPrefix(uniqueWords.get(i), uniqueWords.get(j)).equals("")) {
                        prefixes.add(commonPrefix(uniqueWords.get(i), uniqueWords.get(j)));
                    }
                }
            }

            //check for unique first letters in prefixes
            checkPrefixes = new ArrayList<>(prefixes);

            for (int i = 0; i < checkPrefixes.size(); i++) {
                for (int j = i + 1; j < checkPrefixes.size(); j++) {
                    boolean prefIsOk = checkPrefixes.get(i).length() > checkPrefixes.get(j).length() ?
                            checkPrefixes.get(i).contains(checkPrefixes.get(j)) : checkPrefixes.get(j).contains(checkPrefixes.get(i));
                    if (prefIsOk) {
                        if (checkPrefixes.get(i).length() > checkPrefixes.get(j).length()) {
                            prefixes.remove(checkPrefixes.get(i));
                        } else {
                            prefixes.remove(checkPrefixes.get(j));
                        }
                    }
                }
            }

            root = new Node(null, 0);
            nodes.add(root);

            //create nodes from prefixes
            while (!prefixes.isEmpty()) {
                Node node = new Node(prefixes.pollFirst(), nodes.size());
                root.addNode(node);
                nodes.add(node);
            }

            for (String word : uniqueWords) {
                for (int i = 1; i <= root.getNextNodes().size(); i++) {
                    if (word.substring(0, nodes.get(i).getFragment().length()).equals(nodes.get(i).getFragment())) {
                        Node node = new Node(word.substring(nodes.get(i).getFragment().length()), nodes.size());
                        nodes.get(i).addNode(node);
                        nodes.add(node);
                    }
                }
            }
        }
    }

    private String commonPrefix(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.substring(0, i);
            }
        }
        return a.substring(0, minLength);
    }

    public List<Integer> search(String query) {
        ArrayList<Integer> positions = new ArrayList<>();
        //TODO
        if (!query.equals("") && !query.isEmpty()) {
            for (String word : uniqueWords) {
                if (word.contains(query)) {
                    for (int pos : wordsAndPos.get(word))
                        positions.add(pos + word.indexOf(query));
                }
            }
        }
        return positions;
    }

    public int getUniqueWordsCount() {
        return uniqueWords.size();
    }

    public int getNodesCount(){
        return nodes.size();
    }
}