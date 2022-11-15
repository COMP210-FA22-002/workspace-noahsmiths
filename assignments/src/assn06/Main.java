package assn06;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        avl_bst = avl_bst.insert(20);
        avl_bst = avl_bst.insert(11);
        avl_bst = avl_bst.insert(50);
        avl_bst = avl_bst.insert(4);
        avl_bst = avl_bst.insert(6);
        avl_bst = avl_bst.insert(15);
        avl_bst = avl_bst.insert(3);
        avl_bst = avl_bst.insert(16);
        avl_bst = avl_bst.insert(17);
        avl_bst = avl_bst.insert(2);

        // System.out.println(avl_bst.height());

        Queue<SelfBalancingBST<Integer>> queue = new LinkedList<>();

        queue.add(avl_bst);

        while (queue.size() != 0) {
            SelfBalancingBST<Integer> current = queue.poll();

            if (!current.getLeft().isEmpty()) {
                queue.add(current.getLeft());
            }

            if (!current.getRight().isEmpty()) {
                queue.add(current.getRight());
            }

            System.out.print(current.getValue() + " ");
        }

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post insertion root that is
        // returned might change because of the insertion

        // for (int i=0; i<50; i++) {
        //     avl_bst = avl_bst.insert((int) (Math.random()*100));
        // }

        // System.out.println(avl_bst.height());

        // // Now insert 50 integers in increasing order which would
        // // cause a simple BST to become very tall but for our
        // // self-balancing tree won't be too bad.

        // for (int i=0; i<50; i++) {
        //     avl_bst = avl_bst.insert(i);
        // }

        // System.out.println(avl_bst.height());
    }
}