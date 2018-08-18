public class BTree <T extends Comparable<T>>  {

    private BTree left;
    private BTree right;
    private T value;
    private boolean isLeft, isRight;
    private int depthLevel;
    public static int maxDepth;
    private BTree parent;

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
    public void setMaxDepth(int currentDepth) {
        if (currentDepth > maxDepth)
            maxDepth = currentDepth;
    }

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
                   setMaxDepth(left.depthLevel);

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
                   setMaxDepth(right.depthLevel);
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

    private BTree findMinimum (BTree node) {
        while (node.hasLeft()){
            node = node.left;
        }
        return node;
    }

}
