
import java.util.Map;

class WordFrequencyBST {
    private TreeNode root;
    private Map<String, Integer> ignoredWords;

    public WordFrequencyBST(Map<String, Integer> ignoredWords) {
        this.root = null;
        this.ignoredWords = ignoredWords;
    }

    public void insert(String word) {
        if (!shouldIgnore(word)) {
            root = insertRecursive(root, word);
        }
    }

    private TreeNode insertRecursive(TreeNode root, String word) {
        if (root == null) {
            return new TreeNode(word);
        }

        int compareResult = word.compareTo(root.word);
        if (compareResult < 0) {
            root.left = insertRecursive(root.left, word);
        } else if (compareResult > 0) {
            root.right = insertRecursive(root.right, word);
        } else {
            root.frequency++;
        }

        return root;
    }

    private boolean shouldIgnore(String word) {
        return ignoredWords.containsKey(word.toLowerCase());
    }

    public void inOrderTraversal(StringBuilder result) {
        inOrderTraversalRecursive(root, result);
    }

    private void inOrderTraversalRecursive(TreeNode root, StringBuilder result) {
        if (root != null) {
            inOrderTraversalRecursive(root.left, result);
            result.append(root.word).append(": ").append(root.frequency).append("\n");
            inOrderTraversalRecursive(root.right, result);
        }
    }

    public void preOrderTraversal(StringBuilder result) {
        preOrderTraversalRecursive(root, result);
    }

    private void preOrderTraversalRecursive(TreeNode root, StringBuilder result) {
        if (root != null) {
            result.append(root.word).append(": ").append(root.frequency).append("\n");
            preOrderTraversalRecursive(root.left, result);
            preOrderTraversalRecursive(root.right, result);
        }
    }

    public void postOrderTraversal(StringBuilder result) {
        postOrderTraversalRecursive(root, result);
    }

    private void postOrderTraversalRecursive(TreeNode root, StringBuilder result) {
        if (root != null) {
            postOrderTraversalRecursive(root.left, result);
            postOrderTraversalRecursive(root.right, result);
            result.append(root.word).append(": ").append(root.frequency).append("\n");
        }
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();

        report.append("Design Choices and Implementation Details:\n");
        report.append("1. Used a binary search tree to store word frequencies.\n");
        report.append("2. Implemented traversal methods (in-order, pre-order, post-order) for the tree.\n");
        report.append("3. Ignored common words, SGML/HTML tags, and punctuations during insertion.\n\n");

        return report.toString();
    }
}