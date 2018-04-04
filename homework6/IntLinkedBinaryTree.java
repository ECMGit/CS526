package hw6;


import net.datastructures.Position;

import java.util.*;

public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer> {

    // define necessary instance variables and methods, including a constructor(s)
    private int size = 0;
    private int height = 0;

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }

    public IntLinkedBinaryTree() {
    }

    /**
     * Add a new node with e to the tree rooted at position p
     *
     * @param p The root of the tree to which new node is added
     * @param e The element of the new node
     * @return If a node with e does not exist, a new node with e is added and
     * reference to the node is returned. If a node with e exists, null is returned.
     */
    public Position<Integer> add(Position<Integer> p, Integer e) throws IllegalStateException {
        // set a variable h, check the depth of every new node.
        int h = 0;
        //if the IntlinkedBinaryTree is a empty tree, add a root;
        //else add new Integer into correct place;
        if (p == null) {
            return addRoot(e);
        }
        //set a Node y = x, when x is null, y is x's parentNode;
        Node<Integer> x = validate(p);
        Node<Integer> y = x;
        while (x != null) {
            if (p.getElement() == e) throw new IllegalStateException("The adding element has existed!");
            else if (x.getElement() > e) {
                y = x;
                x = x.getLeft();
                h++;//when searching go into a child node, depth +1;
            } else {
                y = x;
                x = x.getRight();
                h++;
            }
        }

        Node<Integer> temp = createNode(e, y, null, null);
        if (y.getElement() > e) y.setLeft(temp);
        else y.setRight(temp);

        size++;
        // check temp node's depth, if its depth over height, height + 1;
        if (h > height) height++;
        return temp;


        // implement this method

    }

    public static void main(String[] args) {

        // create a new binary tree instance
        IntLinkedBinaryTree t = new IntLinkedBinaryTree();

        // add some integers
        t.add(t.root, 100);
        t.add(t.root, 50);
        t.add(t.root, 150);
        t.add(t.root, 70);
        t.add(t.root, 80);
        t.add(t.root, 90);
        // test with more integers
        Iterator<Position<Integer>> it = t.inorder().iterator();
        while (it.hasNext()) {
            System.out.print(it.next().getElement() + " ");
        }
        System.out.println();

        // print on the screen all integers in the tree in increasing order
        // after adding above four integers, the following should be printed
        // 50 70 100 150

        // experimentally determine the average height of a binary search tree
        // clear the tree before beginning this experiment
        // repeat 100 times


        int maxIteration = 100;
        int height_sum = 0;
        for (int i = 0; i < maxIteration; i++) {
            // Begin with an empty tree in each iteration)
            // Generate 1000 random integers in the range [0, 999999]
            // and add them to the tree, one at a time
            // using the add method you implemented
            // Make sure the resulting tree has 1000 distinct integers
            // Determine the height of the resulting tree
            // Print on the screen the number of nodes and the the height of the tree
            IntLinkedBinaryTree t1000 = new IntLinkedBinaryTree();
            while (t1000.size() < 1000) {
                int num = (int) (Math.random() * 1000000);
                t1000.add(t1000.root, num);
            }
            height_sum += t1000.height();
        }
        int avg_height = height_sum / maxIteration;
        System.out.print("Average height of 100 binary search tree is: " + avg_height);
        // Calculate and display the average height of the 100 trees
    }

}
