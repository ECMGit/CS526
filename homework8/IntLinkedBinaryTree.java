package hw8;


import net.datastructures.Position;

import java.util.Iterator;
import java.util.Scanner;

public class IntLinkedBinaryTree extends hw8.LinkedBinaryTree<Integer> {

    // define necessary instance variables and methods, including a constructor(s)
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

    /**
     * @param p the root of the tree
     * @param e the given integer needed to be searched
     * @return
     */
    public Position<Integer> search(Position<Integer> p, Integer e) {
        if (e.intValue() > p.getElement().intValue()) {
            return search(validate(p).getRight(), e);
        } else if (p.getElement().intValue() == e.intValue()) {
            return p;
        } else if (e.intValue() < p.getElement().intValue()) {
            return search(validate(p).getLeft(), e);
        } else {
            return null;
        }
    }

    /**
     * in this function, we assumed that the given number has existed in the tree;
     * @param p the root of the tree
     * @param e the given integer needed to be deleted;
     * @return
     * @throws NullPointerException
     */
    public Integer delete(Position<Integer> p, Integer e) throws NullPointerException {
        //find the position of given number;
        Position<Integer> deletedElement = search(p, e);
        //call remove(), and remove node;
        return remove(deletedElement);
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
        t.add(t.root, 120);

        while (t.size() < 20) {
            int num = (int) (Math.random() * 1000);
            t.add(t.root, num);
        }

        //display the menu;
        //set a flag, if flag =0,display menu; if flag = 1, exist;
        int flag = 0;
        while (flag == 0) {
            System.out.println("Choose an option:\n\n" +
                    "1. Add a Key\n" +
                    "2. Remove a Key\n" +
                    "3. Print the tree\n" +
                    "4. Exist");

            Scanner in = new Scanner(System.in);
            String a = in.next();
            switch (a) {
                case "1"://adding node
                    System.out.print("Enter a Key:");
                    Integer addkey = in.nextInt();
                    if (t.search(t.root(), addkey) == null) {
                        t.add(t.root(), addkey);
                        System.out.println("The given number has added!");
                    } else
                        System.out.println("The given number has existed!");
                    break;
                case "2":
                    System.out.print("Enter a Key:");
                    Integer removekey = in.nextInt();
                    if (t.search(t.root(), removekey) != null) {
                        t.delete(t.root(), removekey);
                        System.out.println("The given number has deleted!");
                    } else {
                        System.out.println("The given number doesn't exist!");
                    }
                    break;
                case "3":
                    Iterator<Position<Integer>> it = t.inorder().iterator();
                    while (it.hasNext()) {
                        System.out.print(it.next().getElement() + " ");
                    }
                    System.out.println();
                    break;
                case "4":
                    System.out.println("Exited");
                    flag = 1;
                    in.close();
                    break;

            }
        }


        // test with more integers


        // print on the screen all integers in the tree in increasing order
        // after adding above four integers, the following should be printed
        // 50 70 100 150

        // experimentally determine the average height of a binary search tree
        // clear the tree before beginning this experiment
        // repeat 100 times


//        int maxIteration = 100;
//        int height_sum = 0;
//        for (int i = 0; i < maxIteration; i++) {
//            // Begin with an empty tree in each iteration)
//            // Generate 1000 random integers in the range [0, 999999]
//            // and add them to the tree, one at a time
//            // using the add method you implemented
//            // Make sure the resulting tree has 1000 distinct integers
//            // Determine the height of the resulting tree
//            // Print on the screen the number of nodes and the the height of the tree
//            IntLinkedBinaryTree t1000 = new IntLinkedBinaryTree();
//            while (t1000.size() < 1000) {
//                int num = (int) (Math.random() * 1000000);
//                t1000.add(t1000.root, num);
//            }
//            height_sum += t1000.height();
//        }
//        int avg_height = height_sum / maxIteration;
//        System.out.print("Average height of 100 binary search tree is: " + avg_height);
        // Calculate and display the average height of the 100 trees
    }

}
