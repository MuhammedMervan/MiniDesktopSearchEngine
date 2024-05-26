class TreeNode {
    String word;
    int frequency;
    TreeNode left;
    TreeNode right;

    public TreeNode(String word) {
        this.word = word;
        this.frequency = 1;
        this.left = null;
        this.right = null;
    }
}