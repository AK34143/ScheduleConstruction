package com.ads.archive;

import java.util.Scanner;


/* Class RedBlackTreeTest */
public class RedBlackTreeTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        /* Creating object of RedBlack Tree */
        RedBlackTree rbt = new RedBlackTree(new int[3]);
        System.out.println("Red Black Tree Test\n");
        char ch;
        /*  Perform tree operations  */
        do
        {
            System.out.println("\nRed Black Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    int[] params = stringToParams(scan.next());
                    rbt.insertRBT( params );
                    break;
                case 2 :
                    System.out.println("Enter integer element to search");
                    System.out.println("Search result : "+ rbt.search( scan.nextInt() ));
                    break;
                case 3 :
                    System.out.println("Nodes = "+ rbt.countNodes());
                    break;
                case 4 :
                    System.out.println("Empty status = "+ rbt.isEmpty());
                    break;
                case 5 :
                    System.out.println("\nTree Cleared");
                    rbt.makeEmpty();
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            /*  Display tree  */
            /*System.out.print("\nPost order : ");
            rbt.postorder();
            System.out.print("\nPre order : ");
            rbt.preorder();*/
            System.out.print("\nIn order : ");
            rbt.inorder();

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
    public static int[] stringToParams(String input) {
        input = input.trim();
        //input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }


        String[] params = input.split(",");
        int[] output = new int[2+1];
        String part = params[0].split("\\(")[1];
        output[0] = Integer.parseInt(part);
        output[1]=0;
        part = params[1].split("\\)")[0];
        output[2] = Integer.parseInt(part);
        /*for(int index = 0; index < params.length+1; index++) {
            if(index==1){
                output[index]=0;
            }else {
                String part = params[index].trim();
                output[index] = Integer.parseInt(part);
            }
        }*/
        return output;
    }
}
