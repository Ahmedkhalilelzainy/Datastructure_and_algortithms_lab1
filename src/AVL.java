public class AVL {
    Node root=null;
    public void insert(int key){
        root=insertRec(key,root);
    }
    public Node insertRec(int val,Node root){
        if(root==null){
            root=new Node(val);
            return root;
        }
        else if(val<root.key)
           root.left=insertRec(val,root.left);
        else if(val>root.key)
           root.right=insertRec(val,root.right);
        return root;
    }

}
