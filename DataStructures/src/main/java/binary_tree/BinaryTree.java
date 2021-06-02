package binary_tree;

public class BinaryTree {
    private Node root, node;

    public void addNode(String data) {
        //TODO implement method
        if (root == null) {
            root = node = new Node(data);
        } else {
            if (data.compareTo(node.getData()) < 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new Node(data));
                } else {
                    node = node.getLeft();
                    addNode(data);
                }
            }
            if (data.compareTo(node.getData()) > 0) {
                if (node.getRight() == null) {
                    node.setRight(new Node(data));
                } else {
                    node = node.getRight();
                    addNode(data);
                }
            }
        }
        node = root;
    }

    public boolean isContains(String data) {
        if (root == null) {
            return false;
        } else {
            if (data.compareTo(root.getData()) < 0 && root.getLeft() != null) {
                root = root.getLeft();
                isContains(data);
            }
            if (data.compareTo(root.getData()) > 0 && root.getRight() != null) {
                root = root.getRight();
                isContains(data);
            }
            if (data.compareTo(root.getData()) == 0) {
                return true;
            }
        }
        return false;
    }

    public Node getRoot() {
        return root;
    }
}
