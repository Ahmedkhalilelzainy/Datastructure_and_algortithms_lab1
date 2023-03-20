public class Main<T extends Comparable<T>> {
    AVL<T> tree = new AVL<>();

    public static void main(String[] args) {
        AVL tree = new AVL<>();
        AVL p = new AVL<>();

        System.out.println(tree.delete(10));
        System.out.println(tree.search(10));
        System.out.println(tree.insert(10));
        System.out.println(tree.insert(5));
        System.out.println(tree.insert(7));
        System.out.println(tree.insert(2));
        System.out.println(tree.insert(11));
        System.out.println(tree.insert(12));
        System.out.println(tree.insert(1));
        System.out.println(tree.insert(3));
        System.out.println(tree.insert(6));
        System.out.println(tree.insert(3));
        System.out.println(tree.insert(8));
        System.out.println(tree.insert(8));
        System.out.println(tree.insert(10));
        System.out.println(tree.insert(3));

        tree.getRoot().print();
        System.out.println("size: " + tree.size());
        System.out.println("height: "+ tree.height());
        System.out.println("search:");
        System.out.println(tree.search(10));
        System.out.println(tree.search(110));

        System.out.println();
        System.out.println("search: "+ tree.search(3));
        System.out.println(tree.delete(3));
        System.out.println("search: "+ tree.search(3));
        System.out.println("size: " + tree.size());
        System.out.println("height: "+ tree.height());
        System.out.println(tree.delete(1));
        System.out.println("size: " + tree.size());
        System.out.println("height: "+ tree.height());
        System.out.println("search: "+ tree.search(7));
        System.out.println(tree.delete(7));
        System.out.println("search: "+ tree.search(7));
        tree.getRoot().print();
        System.out.println("size: " + tree.size());
        System.out.println("height: "+ tree.height());
        System.out.println(tree.delete(100));
        System.out.println(tree.delete(111));
        System.out.println("search: "+ tree.search(1111));
    }
}