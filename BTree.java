public class BTree <T extends Comparable<T>>  {

    private BTree left;
    private BTree right;
    private T value;
    private boolean isLeft, isRight;
    private int depthLevel;
    public static int maxDepth;
    private BTree parent;

    /*
     Constructor for the BTree class
     @param obj: a comparable object
     @return: an instance of the class BTree
     */
    public BTree (T obj) {
        left = null;
        right = null;
        parent = null;
        value = obj;
        isLeft = false;
        isRight = false;
        depthLevel = 0;
        maxDepth = 0;
    }
    /*
     Returns true if "this" node has a left child
     @return: true if "this" has a left child, false otherwise
     */
    private boolean hasLeft () {
        if (left != null)
                return true;
        return false;
    }
    /*
    Returns true if "this" node has a right child
    @return: true if "this" has a right child, false otherwise
    */
    private boolean hasRight () {
        if (right != null)
            return true;
        return false;
    }
    /*
    getLeft returns the left child of "this" node
    @return: BTree instance, left child of "this"
     */
    public BTree getLeft() {
        return left;
    }
    /*
    getRight returns the right child of "this" node
    @return: BTree instance, right child of "this"
     */
    public BTree getRight() {
        return right;
    }
    /*
    getValue returns the value of "this" node
    @return: the value recorded in "this" node
     */
    public T getValue() {
        return value;
    }
    /*
    setLeft sets the left child of "this" node
    @param left: the node that must be set as left child of "this" node
     */
    private void setLeft(BTree left) {
        this.left = left;
    }
    /*
   setRight sets the right child of "this" node
   @param right: the node that must be set as right child of "this" node
    */
    private void setRight(BTree right) {
        this.right = right;
    }

    /*
    insert inserts a new node in the tree, in the right position as left/right child of another node
    @param: node: a comparable object
     */
    public void insert (BTree node)  {

           if (node.value.compareTo(value)< 0) {
               if (hasLeft()){
                   left.insert(node);
               }
               else {
                   left = node;
                   node.parent = this;
                   node.isLeft = true;
                   left.depthLevel = depthLevel + 1;

               }
           }
           else if (node.value.compareTo(value) > 0) {
               if (hasRight()){
                   right.insert(node);
               }
               else {
                   right = node;
                   node.parent = this;
                   node.isRight = true;
                   right.depthLevel = this.depthLevel + 1;
               }
           }



    }

    /*
    printOrderedValues prints the values in "this" tree in the console, ordered in an ascending way.
    Must be called on the root node.
     */
    public void printOrderedValues() {
        if (hasLeft())
            left.printOrderedValues();
        System.out.println(getValue());
        if (hasRight())
            right.printOrderedValues();
    }
    /*
    search looks for the desired value and returns the node whose value is the searched one
    @param value: the value searched
    @return: the node whose value is the one searched, or prints a message in the console if the value isn't in the tree
     */
    public BTree search(T value) {
       try {
           if (value.compareTo(this.value) < 0)
               return left.search(value);
           else if (value.compareTo(this.value) > 0)
               return right.search(value);
           else if (value == this.value) {
               System.out.println(this + " " + "il valore cercato era " + this.value);
               return this;
           }
       }
       catch (NullPointerException e) {
           System.out.println("Il numero cercato non è presente");
           return null;
       }
        return null;
    }
    /*
    remove removes "this" node from the tree, preserving the values order in the tree
     */
    public void remove () {
        if (!this.hasRight() && !this.hasLeft()) {
            if (this.isRight)
                this.parent.right = null;
            else
                this.parent.left = null;
            System.out.println("Il nodo contenente il valore " + this.value + " è stato rimosso");
        }
        else if ((this.hasLeft() && !this.hasRight()) ||
                (!this.hasLeft() && this.hasRight())) {
            if (this.hasLeft()) {
                BTree tmp = this.left;
                this.left = null;
                if (this.isRight)
                    this.parent.right = tmp;
                else
                    this.parent.left = tmp;
            } else {
                BTree tmp = this.right;
                this.right = null;
                if (this.isRight)
                    this.parent.right = tmp;
                else
                    this.parent.left = tmp;
            }
            System.out.println("Il nodo contenente il valore " + this.value + " è stato rimosso");
        }
        else {
            BTree minValue = findMinimum(this.right);
            T precValue = value;
            value = (T) minValue.value;
            minValue.remove();
            System.out.println("Il nodo contenente il valore " + precValue + " è stato rimosso, al suo posto è stato inserito il nodo contenente il valore " + this.value);
        }


    }
    /*
    findMinimum returns the node which value is the minimum in the subtree where the root is the node where this method is called
    @param node: the node to be used as root of the subtree where to look for the minimum
    @return: the node whose value is the minimum in the subtree
     */
    private BTree findMinimum (BTree node) {
        while (node.hasLeft()){
            node = node.left;
        }
        return node;
    }


}
