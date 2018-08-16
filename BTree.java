
public class BTree <T extends Comparable<T>>  {

    private BTree left;
    private BTree right;
    private T value;
    private static int numberOfNodes = 1;

    public BTree (T obj) {
        left = null;
        right = null;
        value = obj;
    }

    private boolean hasLeft () {
        if (left != null)
                return true;
        return false;
    }
    private boolean hasRight () {
        if (right != null)
            return true;
        return false;
    }
    public BTree getLeft() {
        return left;
    }
    public BTree getRight() {
        return right;
    }
    public T getValue() {
        return value;
    }
    public void setLeft(BTree left) {
        this.left = left;
    }
    public void setRight(BTree right) {
        this.right = right;
    }

    public void insert (BTree node)  {

           if (node.value.compareTo(value)< 0) {
               if (hasLeft()){
                   left.insert(node);
               }
               else {
                   left = node;
                   numberOfNodes++;
                  // System.out.println(numberOfNodes);
               }
           }
           else if (node.value.compareTo(value) > 0) {
               if (hasRight()){
                   right.insert(node);
               }
               else {
                   right = node;
                   numberOfNodes++;
                  // System.out.println(numberOfNodes);
               }
           }



    }
    public void printOrderedValues() {
        if (hasLeft())
            left.printOrderedValues();
        System.out.println(getValue());
        if (hasRight())
            right.printOrderedValues();
    }
    public void search(T value) throws NullPointerException{
       try {
           if (value.compareTo(this.value) < 0)
               left.search(value);
           else if (value.compareTo(this.value) > 0)
               right.search(value);
           else if (value == this.value)
               System.out.print(this + " " + "il valore cercato era " + this.value);
       }
       catch (NullPointerException e) {
           System.out.println("Il numero cercato non è presente");
       }
    }

    public void printTree (BTree root) {
        
    }

}
