package tree;


public class Scratch {

	public static void main(String[] args) {
        Scratch s = new Scratch();
        TreeNode root = new TreeNode(0);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        
        root.right = one;
        one.right = two;
        two.right = three;
    }
}
