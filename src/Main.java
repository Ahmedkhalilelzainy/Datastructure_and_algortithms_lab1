public class Main<T extends Comparable<T>> {
    AVL<T> tree = new AVL<>();
    public static void main(String[] args) {
        AVL tree = new AVL<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
        tree.insert(2);
        tree.insert(11);
        tree.insert(1);
        tree.insert(3);
        tree.insert(6);
        tree.insert(8);
        tree.getRoot().print();
        Node n = tree.search(10,tree.getRoot());
            if(n.getLeft() != null)
                System.out.println("left:   " + n.getLeft().getKey());
            if(n.getRight() != null)
                System.out.println("Right:   " + n.getRight().getKey());
        System.out.println("---------------------");

        tree.delete(5);
        n = tree.search(10,tree.getRoot());
        if(n.getLeft() != null)
            System.out.println("left:   " + n.getLeft().getKey());
        if(n.getRight() != null)
            System.out.println("Right:   " + n.getRight().getKey());

        System.out.println("left:   " + n.getLeft().getLeft().getKey());
        System.out.println("left:   " + n.getLeft().getRight().getKey());
        tree.getRoot().print();

//        Node n = tree.search("u",tree.getRoot());
//        if(n == null){
//            System.out.println("not Found");
//        }else{
//            System.out.println("val:   " + n.getKey());
//            if(n.getLeft() != null)
//                System.out.println("left:   " + n.getLeft().getKey());
//            if(n.getRight() != null)
//                System.out.println("Right:   " + n.getRight().getKey());
//        }




    }
}