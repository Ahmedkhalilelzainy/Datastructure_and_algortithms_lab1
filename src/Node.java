
public class Node {
    int key;
    Node left,right;
    public Node(int val){
        key=val;
        left=right=null;
    }
    public void print(){
        if(this.left!=null)
            this.left.print();
        System.out.println(this.key);
        if(this.right!=null)
            this.right.print();
    }

}
