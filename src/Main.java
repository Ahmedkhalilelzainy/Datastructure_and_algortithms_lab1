import AVL_Tree.AVL;
import AVL_Tree.ITree;
import RB_Tree.RB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
//        AVL<Integer> usedTree = new AVL<Integer>();
//
//        for (int i = 0; i < 10; i++) {
//            usedTree.insert(i);
//        }
//
//
//        // testing
////        RBNode<String> node1 = new RBNode<>("10"), node2 = new RBNode<>("20"), node3 = null;
////        node2.setParent( node1 );
////        complementary<String> functions = complementary.getInstance();
////        functions.leftRotate(node2);
////        try {
////            node1.setParent( node3 );
////            System.out.println("in try");
////        }catch (Exception e){
////            System.out.println( "in catch" );
////        }
//
//        RB<Integer> usedTree1 = new RB<>();
//        for(int i = 0;i < 10;i ++){
//            usedTree1.insert( i );
//        }
//
//
//        usedTree1.treeTraversal( usedTree1.getRoot(), "root" );
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  English Dictionary");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");

        //initialize tree type
        ITree<String> dictionary;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the tree type (AVL/RB): ");
            try {
                String treeType = sc.nextLine();
                if (treeType.equalsIgnoreCase("AVL")) {
                    dictionary = new AVL<String>();
                    System.out.println("avl");
                    break;
                } else if (treeType.equalsIgnoreCase("RB")) {
                    dictionary = new RB<String>();
                    System.out.println("rb");
                    break;
                } else {
                    System.out.println("Wrong Input");
                }
            } catch (Exception e) {
                System.out.println("Wrong input type");
            }
        }
        //start operations
        while (true){
            System.out.println("The operations you can do with our dictionary:");
            System.out.println("1- Insert\t\t2- Delete\t\t3- Search\t\t4- Batch Insert\t\t5- Batch Delete\t\t6- Size\t\t7- Tree Height\t\t8- terminate the program");
            System.out.print("Enter the operation order you want to do(1/2/3/4/5/6/7/8): ");
            String operation = sc.nextLine();
            if(operation.equals("1")){
                System.out.print("please enter the string key you want to insert: ");
                String key = sc.nextLine();
                if(dictionary.insert(key)){
                    System.out.println("the key was inserted successfully");
                }else{
                    System.out.println("Error!! the key already exists");
                }
            } else if(operation.equals("2")){
                System.out.print("please enter the string key you want to delete: ");
                String key = sc.nextLine();
                if(dictionary.delete(key)){
                    System.out.println("the key was deleted successfully");
                }else{
                    System.out.println("Error!! the key doesn't exist");
                }
            }else if(operation.equals("3")){
                System.out.print("please enter the string key you want to search for: ");
                String key = sc.nextLine();
                if(dictionary.search(key)){
                    System.out.println("the key was found successfully");
                }else{
                    System.out.println("the key wasn't found");
                }
            }else if(operation.equals("4")){
                int nSuccess = 0, nFail = 0;
                while (true) {
                    System.out.print("please enter the file path: ");
                    String filePath = sc.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (dictionary.insert(line)) {
                                nSuccess++;
                            } else {
                                nFail++;
                            }
                        }
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }
                System.out.println("The number of successfully newly added strings: " + nSuccess);
                System.out.println("The number of already existing strings: " + nFail);
            }else if(operation.equals("5")){
                int nSuccess = 0, nFail = 0;
                while (true) {
                    System.out.print("please enter the file path: ");
                    String filePath = sc.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (dictionary.delete(line)) {
                                nSuccess++;
                            } else {
                                nFail++;
                            }
                        }
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }
                System.out.println("The number of successfully deleted strings: " + nSuccess);
                System.out.println("The number of non-existing strings: " + nFail);
            }else if(operation.equals("6")){
                System.out.println("Dictionary size is: " + dictionary.size());
            }else if(operation.equals("7")){
                int h = dictionary.height();
                if( h == -1){
                    System.out.println("The tree is empty");
                }else{
                    System.out.println("The height of the backend tree is: " + h);
                }
            }else if(operation.equals("8")){
                break;
            }else {
                System.out.println("Wrong input you should enter (1/2/3/4/5/6/7/8)");
            }
            System.out.println();
        }

    }
}