package com.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ads.MinHeap2.stringToParams;

public class RedBlackTree2 {

    private final int RED = 0;
    private static final int BLACK = 1;
    public List<RBTNode> rangeBuildings;
    public static class RBTNode {

        Node key;
        int color = BLACK;
        RBTNode left = nil, right = nil, parent = nil;

        RBTNode(Node key) {
            this.key = key;
        }
    }

    private static final RBTNode nil = new RBTNode(new Node(-1,-1,-1));
    private RBTNode root = nil;

    public void printTree(RBTNode node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        char c = 'B';
        if (node.color==RED)
            c = 'R';
        //System.out.print("("+r.element[0] +","+r.element[1]+","+r.element[2] +")"+c+" ");
        System.out.print("("+node.key.getBuildingNum()+","+node.key.getExecutionTime()+")"+c+" ");
        //System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
        printTree(node.right);
    }
    /*public void inorder()
    {
        inorder(header.right);
    }
    private void inorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            inorder(r.left);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print("("+r.element[0] +","+r.element[1]+","+r.element[2] +")"+c+" ");
            inorder(r.right);
        }
    }*/
    void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
    }
    int height(RBTNode root)
    {
        if (root == null)
            return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }
    void printGivenLevel (RBTNode node ,int level)
    {
        if (node == null)
            return;
        if (level == 1) {
            char c = 'B';
            if (node.color == RED)
                c = 'R';
            //if(node.key.buildingNum!=-1)
                System.out.print("("+node.key.getBuildingNum()+","+ node.key.getExecutionTime()+","+ node.key.getTotalTime()+ "," + c+") ");
        }
        else if (level > 1)
        {
            printGivenLevel(node.left, level-1);
            printGivenLevel(node.right, level-1);
        }
    }

    public RBTNode printBuilding( int x ) {
        //nullNode.element = x;
        RBTNode current = root;

        while(current!=nil) {
            if( x < current.key.getBuildingNum() )
                current = current.left;
            else if( x > current.key.getBuildingNum())
                current = current.right;
            else if( x == current.key.getBuildingNum() )
                return current;
            //else

        }
        return null;
    }

    public List<RBTNode> printBuilding(int x, int y) {
        rangeBuildings=new ArrayList<>();
        RBTNode current = root;
        printBetween(current,x,y);
        return rangeBuildings;
    }
    public void printBetween(RBTNode current,int x, int y){

        //while(current!=nil) {
            /*if(x== current.key.getBuildingNum() && y==current.key.getBuildingNum()){
                System.out.println("(" + current.key.getBuildingNum() + "," + current.key.getExecutionTime() + "," + current.key.getTotalTime() + ")");*/
            if(current==nil){
                //System.out.println("(" + 0 + "," + 0 + "," + 0 + ")");
            } else if( x <= current.key.getBuildingNum() && y >= current.key.getBuildingNum()) {
                rangeBuildings.add(current);
                //System.out.println("(" + current.key.getBuildingNum() + "," + current.key.getExecutionTime() + "," + current.key.getTotalTime() + ")");
                printBetween(current.left, x,Math.min(y,current.key.getBuildingNum()));
                printBetween(current.right, Math.max(x,current.key.getBuildingNum()),y);//current = current.left;
            } else if ( x > current.key.getBuildingNum() && y > current.key.getBuildingNum()) {
                //System.out.println("(" + current.key.getBuildingNum() + "," + current.key.getExecutionTime() + "," + current.key.getTotalTime() + ")");
                //printBetween(current.left, x,current.key.getExecutionTime());
                printBetween(current.right, Math.max(x,current.key.getBuildingNum()),y);//current = current.left;
            } else if (x < current.key.getBuildingNum() && y < current.key.getBuildingNum()){
                printBetween(current.left, x,Math.min(y,current.key.getBuildingNum()));
            }
            /*else if( x > current.key.getBuildingNum())
                current = current.right;*/
            //else if( x == current.key.getBuildingNum() )
                //return current;
            //else

        //}
    }
    /**
     * Find an item in the tree.
     * x the item to search for.
     * @return the matching item or null if not found.
     */
    /*public Comparable find( Comparable x ) {
        nullNode.element = x;
        current = header.right;

        for( ; ; ) {
            if( x.compareTo( current.element ) < 0 )
                current = current.left;
            else if( x.compareTo( current.element ) > 0 )
                current = current.right;
            else if( current != nullNode )
                return current.element;
            else
                return null;
        }
    }*/

    private RBTNode findNode(RBTNode findNode, RBTNode node) {
        if (root == nil) {
            return null;
        }

        if (findNode.key.getBuildingNum() < node.key.getBuildingNum()) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key.getBuildingNum() > node.key.getBuildingNum()) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key.getBuildingNum() == node.key.getBuildingNum()) {
            return node;
        }
        return null;
    }

    public void insertRBT(RBTNode node) {
        RBTNode temp = root;
        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            while (true) {
                if (node.key.getBuildingNum() < temp.key.getBuildingNum()) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key.getBuildingNum() >= temp.key.getBuildingNum()) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    //Takes as argument the newly inserted node
    private void fixTree(RBTNode node) {
        while (node.parent.color == RED) {
            RBTNode uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {
                    //Double rotation needed
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateRight(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    //Double rotation needed
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(RBTNode node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {//Need to rotate root
            RBTNode right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(RBTNode node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            RBTNode left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    //Deletes whole tree
    void deleteTree(){
        root = nil;
    }

    //Deletion Code .

    //This operation doesn't care about the new Node's connections
    //with previous node's left and right. The caller has to take care
    //of that.
    void transplant(RBTNode target, RBTNode with){
        if(target.parent == nil){
            root = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
    }

    boolean delete(RBTNode z){
        if((z = findNode(z, root))==null)return false;
        RBTNode x;
        RBTNode y = z; // temporary reference y
        int y_original_color = y.color;

        if(z.left == nil){
            x = z.right;
            transplant(z, z.right);
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left);
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(y_original_color==BLACK)
            deleteFixup(x);
        return true;
    }

    void deleteFixup(RBTNode x){
        while(x!=root && x.color == BLACK){
            if(x == x.parent.left){
                RBTNode w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                RBTNode w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    RBTNode treeMinimum(RBTNode subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    public void consoleUI() {
        Scanner scan = new Scanner(System.in);
//        while (true) {
//            System.out.println("\n1.- Add items\n"
//                    + "2.- Delete items\n"
//                    + "3.- Check items\n"
//                    + "4.- Print tree\n"
//                    + "5.- Delete tree\n");
            //int choice = scan.nextInt();

            int item;
        RBTNode node;
            int[] params = stringToParams("1,5,25");
        RBTNode node1 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node1);
            //minHeap.printHeap();
            params = stringToParams("4,6,10");
        RBTNode node2 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node2);
            //minHeap.printHeap();
            params = stringToParams("9,3,15");
        RBTNode node3 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node3);
            //minHeap.printHeap();
            params = stringToParams("10,7,3");
        RBTNode node4 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node4);
            //minHeap.printHeap();
            params = stringToParams("6,10,4");
            //RedBlackTree2.Node node = new RedBlackTree2.Node(params);
            //insertRBT(params);
        RBTNode node5 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node5);
            //minHeap.printHeap();
            params = stringToParams("5,5,4");
        RBTNode node6 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node6);
            //minHeap.printHeap();
            params = stringToParams("7,6,4");
        RBTNode node7 = new RBTNode(new Node(params[0],params[1],params[2]));
            insertRBT(node7);
        params = stringToParams("12,3,4");
        RBTNode node8 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node8);
        params = stringToParams("17,11,4");
        RBTNode node9 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node9);
        params = stringToParams("19,15,4");
        RBTNode node10 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node10);
        params = stringToParams("25,7,4");
        RBTNode node11 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node11);
        params = stringToParams("15,9,4");
        RBTNode node12 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node12);
        params = stringToParams("21,23,4");
        RBTNode node13 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node13);
        params = stringToParams("28,17,4");
        RBTNode node14 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node14);
        params = stringToParams("13,17,4");
        RBTNode node15 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node15);
        params = stringToParams("2,23,4");
        RBTNode node16 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node16);
        params = stringToParams("38,10,4");
        RBTNode node17 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node17);
        params = stringToParams("33,17,4");
        RBTNode node18 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node18);
        params = stringToParams("20,10,4");
        RBTNode node19 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node19);
        params = stringToParams("30,10,4");
        RBTNode node20 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node20);
        params = stringToParams("23,16,4");
        RBTNode node21 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node21);
        params = stringToParams("8,10,4");
        RBTNode node22 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node22);
        params = stringToParams("3,24,4");
        RBTNode node23 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node23);
        params = stringToParams("11,10,4");
        RBTNode node24 = new RBTNode(new Node(params[0],params[1],params[2]));
        insertRBT(node24);

            //minHeap.printHeap();
            /*node.key= new int[]{6, 10};
            node.color=BLACK;
            node.left=*/
        printLevelOrder();
            delete(node5);
        System.out.println(" ");
        printLevelOrder();
            delete(node3);
            System.out.println(" ");
            printLevelOrder();
        delete(node1);
        System.out.println(" ");
        printLevelOrder();
        delete(node2);
        System.out.println(" ");
        printLevelOrder();
        delete(node4);
        System.out.println(" ");
        printLevelOrder();
        delete(node5);
        System.out.println(" ");
        printLevelOrder();
        delete(node6);
        System.out.println(" ");
        printLevelOrder();
        delete(node7);
        System.out.println(" ");
        printLevelOrder();
        delete(node8);
        System.out.println(" ");
        printLevelOrder();
        delete(node9);
        System.out.println(" ");
        printLevelOrder();

            /*switch (choice) {
                case 1:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new Node(item);
                        insert(node);
                        item = scan.nextInt();
                    }
                    //printTree(root);
                    printLevelOrder();
                    break;
                case 2:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new Node(item);
                        System.out.print("\nDeleting item " + item);
                        if (delete(node)) {
                            System.out.print(": deleted!");
                        } else {
                            System.out.print(": does not exist!");
                        }
                        item = scan.nextInt();
                    }
                    System.out.println();
                    //printTree(root);
                    printLevelOrder();
                    break;
                case 3:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new Node(item);
                        System.out.println((findNode(node, root) != null) ? "found" : "not found");
                        item = scan.nextInt();
                    }
                    break;
                case 4:
                    printTree(root);
                    break;
                case 5:
                    deleteTree();
                    System.out.println("Tree deleted!");
                    break;
            }*/
//        }
    }
    public static void main(String[] args) {
        RedBlackTree2 rbt = new RedBlackTree2();
        rbt.consoleUI();
    }
}
