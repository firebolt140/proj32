public class BinTree {
    private Node root = null;

    public BinTree(){
        root = null;
    }
    public BinTree(Node temp){
        root = temp;
    }

    public void setRoot(Node temp){
        root = temp;
    }
    public Node getRoot(){
        return root;
    }
    public Object[] search(Node find){
        Node current = root;
        boolean found = false;

        int num = 0;
        while(current != null) {
            if (find.getTerm().exp == current.getTerm().exp) {
                num = 0;
            } else if (find.getTerm().exp > current.getTerm().exp) {
                num = -1;
            }else{
                num = 1;
            }
            if(num == 0){
                found = true;
                break;
            }
            else if (num < 0){
                current = current.getLeft();
            }
            else{
                current = current.getRight();
            }
        }

        Object[] arr = {found, current};

        return arr;
    }


    public String print(){
        return print(root);
    }
    public String print(Node temp){
        if(temp != null){
            return print(temp.getLeft()) + "" + temp.getTerm().toString() + "|" + print(temp.getRight());
        }
        return "";
    }

    public void insert(Node newNode){
        Node current = root;
        Node parent = null;

        int num = 0;
        while(current != null) {
            if (newNode.getTerm().exp == current.getTerm().exp) {
                num = 0;
            } else if (newNode.getTerm().exp > current.getTerm().exp) {
                num = -1;
            }else{
                num = 1;
            }
            if(num == 0){
                break;
            }
            else if (num < 0){
                parent = current;
                current = current.getLeft();
            }
            else{
                parent = current;
                current = current.getRight();
            }
        }

        if(current == root){
            root = newNode;
        }
        else if(num < 0){
            parent.setLeft(newNode);
        }
        else{
            parent.setRight(newNode);
        }
    }

}
