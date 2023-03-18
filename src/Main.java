public class Main<T extends Comparable<T>> {
    AVL<T> tree = new AVL<>();
    public static void main(String[] args) {
        AVL tree = new AVL<>();
        tree.insert("g");
        tree.insert("c");
        tree.insert("a");
        tree.insert("j");
        tree.insert("q");
        tree.insert("b");
        tree.insert("o");
        tree.insert("o");
        tree.insert("u");

        tree.getRoot().print();

        Node n = tree.search("u",tree.getRoot());
        if(n == null){
            System.out.println("not Found");
        }else{
            System.out.println("val:   " + n.getKey());
            if(n.getLeft() != null)
                System.out.println("left:   " + n.getLeft().getKey());
            if(n.getRight() != null)
                System.out.println("Right:   " + n.getRight().getKey());
        }




    }
}