import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import suffix_tree.SuffixTree;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование суффиксного дерева")
public class SuffixTreeTest {
    private SuffixTree suffixTree;
    private String text = "this text tightly some terrible sophisticated text";

    @BeforeEach
    void setUp() {
        suffixTree = new SuffixTree(text);
        suffixTree.build();
    }

    @Test
    @DisplayName("Проверка метода build")
    void buildingTree() {
        assertEquals(6, suffixTree.getUniqueWordsCount());
        assertEquals(9, suffixTree.getNodesCount());
    }

    @Test
    @DisplayName("Поиск фрагмента")
    void searchFragment() {
        assertEquals(text.indexOf("tl"), suffixTree.search("tl").get(0));
    }

    @Test
    @DisplayName("Поиск повторяющегося фрагмента")
    void repeatedFragment() {
        assertEquals("[6, 47]", suffixTree.search("ext").toString());
    }
}
