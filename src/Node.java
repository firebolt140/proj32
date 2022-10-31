public class Node {
    private Node left;
    private Node right;
    private Term term;

    public Node(){
        term = null;
    }
    public Node(Term temp){
        term = temp;
    }

    public void setTerm(Term temp){
        term = temp;
    }
    public Term getTerm(){
        return term;
    }

    public void setRight(Node temp){
        right = temp;
    }
    public Node getRight(){
        return right;
    }

    public void setLeft(Node temp){
        left = temp;
    }
    public Node getLeft(){
        return left;
    }
}
