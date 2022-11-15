package assn06;

import java.lang.Math;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = 0;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
    private AVLTree<T> rotateLeft() {
        // You should implement left rotation and then use this 
        // method as needed when fixing imbalances.
        // TODO
        if (this._right.isEmpty()) {
            return this;
        }

        AVLTree<T> root = this._right;
        this._right = this._right._left;
        root._left = this;

        this._size = this._left.size() + this._right.size() + 1;
        this._height = Math.max(this._left.height(), this._right.height()) + 1;

        root._size = root._left.size() + root._right.size() + 1;
        root._height = Math.max(root._left.height(), root._right.height()) + 1;
        
        return root;
    }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
    private AVLTree<T> rotateRight() {
        // You should implement right rotation and then use this 
        // method as needed when fixing imbalances.
        // TODO
        if (this._left.isEmpty()) {
            return this;
        }

        AVLTree<T> root = this._left;
        this._left = this._left._right;
        root._right = this;

        this._size = this._left.size() + this._right.size() + 1;
        this._height = Math.max(this._left.height(), this._right.height()) + 1;

        root._size = root._left.size() + root._right.size() + 1;
        root._height = Math.max(root._left.height(), root._right.height()) + 1;
        
        return root;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO
        if (element == null) {
            return this;
        }

        if (this.isEmpty()) {
            this._height = 1;
            this._size = 1;
            this._left = new AVLTree<T>();
            this._right = new AVLTree<T>();
            this._value = element;
            return this;
        }

        int comparison = element.compareTo(this._value);

        if (comparison < 0) { // Less than current
            this._left = (AVLTree<T>) this._left.insert(element);

            this._size = this._left.size() + this._right.size() + 1;
            this._height = Math.max(this._left.height(), this._right.height()) + 1;

            return this.rebalance();
        } else if (comparison > 0) { // Greater than current
            this._right = (AVLTree<T>) this._right.insert(element);

            this._size = this._left.size() + this._right.size() + 1;
            this._height = Math.max(this._left.height(), this._right.height()) + 1;

            return this.rebalance();
        }

        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO
        if (this.isEmpty()) {
            return this;
        }

        int comparison = element.compareTo(this._value);
        
        if (comparison == 0) {
            if (!this._left.isEmpty() && !this._right.isEmpty()) { // Both child nodes
                AVLTree<T> current = this._right;

                while (!current._left.isEmpty()) {
                    current = this._left;
                }

                this._right = (AVLTree<T>) this._right.remove(current.getValue());

                this._value = current.getValue();

                this._size -= 1;
                this._height = Math.max(this._left.height(), this._right.height()) + 1;

                return this.rebalance();
            } else if (!this._left.isEmpty()) { // Left node only
                return this._left;
            } else if (!this._right.isEmpty()) { // Right node only
                return this._right;
            } else { // No children node aka leaf node
                return new AVLTree<T>();
            }
        } else if (comparison < 0) {
            this._left = (AVLTree<T>) this._left.remove(element);

            this._size = this._left.size() + this._right.size() + 1;
            this._height = Math.max(this._left.height(), this._right.height()) + 1;

            return this.rebalance();
        } else if (comparison > 0) {
            this._right = (AVLTree<T>) this._right.remove(element);

            this._size = this._left.size() + this._right.size() + 1;
            this._height = Math.max(this._left.height(), this._right.height()) + 1;

            return this.rebalance();
        }

        return this;
    }

    @Override
    public T findMin() {
    	// TODO
        AVLTree<T> current = this;

        while (!current._left.isEmpty()) {
            current = current._left;
        }

        return current.getValue();
    }

    @Override
    public T findMax() {
    	// TODO
        AVLTree<T> current = this;

        while (!current._right.isEmpty()) {
            current = current._right;
        }

        return current.getValue();
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        if (this.isEmpty()) {
            return false;
        }

        int comparison = element.compareTo(this._value);

        if (comparison < 0) {
            return this._left.contains(element);
        } else if (comparison > 0) {
            return this._right.contains(element);
        }

        return true;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }

    private AVLTree<T> rebalance() {
        int leftHeight = this._left.height();
        int rightHeight = this._right.height();

        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) { // Left Imbalance
                int leftLeftHeight = this._left._left.height();
                int leftRightHeight = this._left._right.height();

                if (leftLeftHeight < leftRightHeight) {
                    this._left = this._left.rotateLeft();
                }

                return this.rotateRight();

                // if (leftLeftHeight >= leftRightHeight) { // Left Left Imbalance
                //     return this.rotateRight();
                // } else { // Left Right Imbalance
                //     this._left = this._left.rotateLeft();
                //     return this.rotateRight()
                // }
            } else { // Right Imbalance
                int rightLeftHeight = this._right._left.height();
                int rightRightHeight = this._right._right.height();

                if (rightRightHeight < rightLeftHeight) {
                    this._right = this._right.rotateRight();
                }

                return this.rotateLeft();
            }
        }

        return this;
    }

}