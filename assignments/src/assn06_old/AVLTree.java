package assn06_old;

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
        AVLTree<T> root = (AVLTree<T>) this.getRight();

        if (root == null) {
            return this;
        }
        
        this._right = (AVLTree<T>) root.getLeft();
        this._size = 1;
        this._height = 1;

        int leftHeight = 0;
        int rightHeight = 0;

        if (this.getLeft() != null) {
            this._size += this.getLeft().size();
            leftHeight = this.getLeft().height();
        }
        if (this.getRight() != null) {
            this._size += this.getRight().size();
            rightHeight = this.getRight().height();
        }

        this._height = Math.max(leftHeight, rightHeight) + 1;

        root._left = this;
        root._size = 1;
        root._height = 1;

        int newLeftHeight = 0;
        int newRightHeight = 0;

        if (root.getLeft() != null) {
            root._size += root.getLeft().size();
            newLeftHeight = root.getLeft().height();
        }
        if (root.getRight() != null) {
            root._size += root.getRight().size();
            newRightHeight = root.getRight().height();
        }

        root._height = Math.max(newLeftHeight, newRightHeight) + 1;

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
        AVLTree<T> root = (AVLTree<T>) this.getLeft();

        if (root == null) {
            return this;
        }

        this._left = (AVLTree<T>) root.getRight();
        this._size = 1;
        this._height = 1;

        int leftHeight = 0;
        int rightHeight = 0;

        if (this.getLeft() != null) {
            this._size += this.getLeft().size();
            leftHeight = this.getLeft().height();
        }
        if (this.getRight() != null) {
            this._size += this.getRight().size();
            rightHeight = this.getRight().height();
        }

        this._height = Math.max(leftHeight, rightHeight) + 1;

        root._right = this;
        root._size = 1;
        root._height = 1;

        int newLeftHeight = 0;
        int newRightHeight = 0;

        if (root.getLeft() != null) {
            root._size += root.getLeft().size();
            newLeftHeight = root.getLeft().height();
        }
        if (root.getRight() != null) {
            root._size += root.getRight().size();
            newRightHeight = root.getRight().height();
        }

        root._height = Math.max(newLeftHeight, newRightHeight) + 1;

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

        if (this.getValue() == null) {
            this._value = element;
            this._size = 1;
            this._height = 1;
            return this;
        }

        int comparison = element.compareTo(this.getValue());

        if (comparison == 0) {
            return this;
        } else if (comparison < 0) { // Number is less than current
            if (this.getLeft() != null) {
                int oldSize = this.getLeft().size();

                this._left = (AVLTree<T>) this.getLeft().insert(element);

                int newSize = this.getLeft().size();

                this._size += (newSize - oldSize); // Account for duplicate being inserted not creating change in size

                // Set new height
                AVLTree<T> left = (AVLTree<T>) this.getLeft();
                AVLTree<T> right = (AVLTree<T>) this.getRight();

                int leftHeight = left.height();
                int rightHeight = 0;

                if (right != null) {
                    rightHeight = right.height();
                }

                this._height = Math.max(leftHeight, rightHeight) + 1;

                // Rebalance
                return this.rebalance();
                // if (leftHeight - rightHeight > 1) { // Left imbalance
                //     int leftLeftHeight = left.getLeft() != null ? left.getLeft().height() : 0;
                //     int leftRightHeight = left.getRight() != null ? left.getRight().height() : 0;

                //     if (leftLeftHeight >= leftRightHeight) { // Left left imbalance
                //         return this.rotateRight();
                //     } else if (leftLeftHeight < leftRightHeight) { // Left right imbalance
                //         this._left = this._left.rotateLeft();
                //         return this.rotateRight();
                //     }
                // } else if (rightHeight - leftHeight > 1) { // Right imbalance
                //     int rightLeftHeight = right.getLeft() != null ? right.getLeft().height() : 0;
                //     int rightRightHeight = right.getRight() != null ? right.getRight().height() : 0;

                //     if (rightLeftHeight> rightRightHeight) { // Right left imbalance
                //         this._right = this._right.rotateRight();
                //         return this.rotateLeft();
                //     } else if (rightLeftHeight <= rightRightHeight) { // Right right imbalance
                //         return this.rotateLeft();
                //     }
                // }

                // return this;
            } else {
                this._left = new AVLTree<T>();
                this._left.insert(element);
                this._size += 1;
                this._height += 1;
                return this;
            }
        } else if (comparison > 0) { // Number is greater than current
            if (this.getRight() != null) {
                // Insert and calculate new size
                int oldSize = this.getRight().size();

                this._right = (AVLTree<T>) this.getRight().insert(element);

                int newSize = this.getRight().size();

                this._size += (newSize - oldSize); // Account for duplicate being inserted not creating change in size

                // Set new height
                AVLTree<T> left = (AVLTree<T>) this.getLeft();
                AVLTree<T> right = (AVLTree<T>) this.getRight();

                int rightHeight = right.height();
                int leftHeight = 0;

                if (left != null) {
                    leftHeight = left.height();
                }

                this._height = Math.max(leftHeight, rightHeight) + 1;

                // Rebalance
                return this.rebalance();
                // if (leftHeight - rightHeight > 1) { // Left imbalance
                //     int leftLeftHeight = left.getLeft() != null ? left.getLeft().height() : 0;
                //     int leftRightHeight = left.getRight() != null ? left.getRight().height() : 0;

                //     if (leftLeftHeight >= leftRightHeight) { // Left left imbalance
                //         return this.rotateRight();
                //     } else if (leftLeftHeight < leftRightHeight) { // Left right imbalance
                //         this._left = this._left.rotateLeft();
                //         return this.rotateRight();
                //     }
                // } else if (rightHeight - leftHeight > 1) { // Right imbalance
                //     int rightLeftHeight = right.getLeft() != null ? right.getLeft().height() : 0;
                //     int rightRightHeight = right.getRight() != null ? right.getRight().height() : 0;

                //     if (rightLeftHeight > rightRightHeight) { // Right left imbalance
                //         this._right = this._right.rotateRight();
                //         return this.rotateLeft();
                //     } else if (rightLeftHeight <= rightRightHeight) { // Right right imbalance
                //         return this.rotateLeft();
                //     }
                // }

                // return this;
            } else {
                this._right = new AVLTree<T>();
                this._right.insert(element);
                this._size += 1;
                this._height += 1;
                return this;
            }
        }

        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO
        SelfBalancingBST<T> newTree = this.removeInternal(element);
        
        if (newTree == null) {
            return new AVLTree<T>();
        }

        return newTree;
    }

    public SelfBalancingBST<T> removeInternal(T element) {
    	// TODO
        try {
            if (element == null) {
                return this;
            }
    
            if (this.getValue() == null) {
                this._size = 0;
                this._height = 0;
                return this;
            }
    
            int comparison = element.compareTo(this.getValue());
    
            if (comparison == 0) {
                if (this.getLeft() != null && this.getRight() != null) { // Parent of two children
                    AVLTree<T> current = (AVLTree<T>) this.getRight();
    
                    while (current.getLeft() != null) {
                        current = (AVLTree<T>) current.getLeft();
                    }
    
                    this._right = (AVLTree<T>) this._right.removeInternal(current.getValue());
    
                    this._value = current.getValue();
    
                    this._size -= 1;
    
                    // Reclaulate height
                    int leftHeight = this.getLeft() != null ? this.getLeft().height() : 0;
                    int rightHeight = this.getRight() != null ? this.getRight().height() : 0;
    
                    this._height = Math.max(leftHeight, rightHeight) + 1;
    
                    return this.rebalance();
                } else if (this.getLeft() != null) { // Only left child
                    return this.getLeft();
                } else if (this.getRight() != null) { // Only right child
                    return this.getRight();
                } else {
                    // this._size = 0;
                    // this._height = 0;
                    // this._value = null;
                    // return this;
                    return null;
                }
            } else if (comparison < 0) {
                if (this.getLeft() != null) {
                    // Recalculate size
                    int oldSize = this.getLeft().size();
    
                    this._left = (AVLTree<T>) this._left.removeInternal(element);
    
                    int newSize = this.getLeft() != null ? this.getLeft().size() : 0;
    
                    this._size += (newSize - oldSize);
    
                    // Reclaulate height
                    int leftHeight = this.getLeft() != null ? this.getLeft().height() : 0;
                    int rightHeight = this.getRight() != null ? this.getRight().height() : 0;
    
                    this._height = Math.max(leftHeight, rightHeight) + 1;
    
                    return this.rebalance();
                }
            } else if (comparison > 0) {
                if (this.getRight() != null) {
                    // Recalculate size
                    int oldSize = this.getRight().size();
    
                    this._right = (AVLTree<T>) this._right.removeInternal(element);
    
                    int newSize = this.getRight() != null ? this.getRight().size() : 0;
    
                    this._size += (newSize - oldSize);
    
                    // Reclaulate height
                    int leftHeight = this.getLeft() != null ? this.getLeft().height() : 0;
                    int rightHeight = this.getRight() != null ? this.getRight().height() : 0;
    
                    this._height = Math.max(leftHeight, rightHeight) + 1;
    
                    return this.rebalance();
                }
            }
    
            return this;
        } catch (Exception e) {
            return this;
        }
    }

    @Override
    public T findMin() {
    	// TODO
        SelfBalancingBST<T> leftMost = this;

        while (leftMost.getLeft() != null) {
            leftMost = leftMost.getLeft();
        }
        
        return leftMost.getValue();
    }

    @Override
    public T findMax() {
    	// TODO
        SelfBalancingBST<T> rightMost = this;

        while (rightMost.getRight() != null) {
            rightMost = rightMost.getRight();
        }
        
        return rightMost.getValue();
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        int comparison = element.compareTo(this.getValue());

        if (comparison == 0) {
            return true;
        } else if (comparison < 0 && this.getLeft() != null) { // Number is less than current
            return this.getLeft().contains(element);
        } else if (comparison > 0 && this.getRight() != null) { // Number is greater than current
            return this.getRight().contains(element);
        } else {
            return false;
        }
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

    private SelfBalancingBST<T> rebalance() {
        AVLTree<T> left = (AVLTree<T>) this.getLeft();
        AVLTree<T> right = (AVLTree<T>) this.getRight();

        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height() : 0;

        if (leftHeight - rightHeight > 1) { // Left imbalance
            int leftLeftHeight = left.getLeft() != null ? left.getLeft().height() : 0;
            int leftRightHeight = left.getRight() != null ? left.getRight().height() : 0;

            if (leftLeftHeight >= leftRightHeight) { // Left left imbalance
                return this.rotateRight();
            } else if (leftLeftHeight < leftRightHeight) { // Left right imbalance
                this._left = this._left.rotateLeft();
                return this.rotateRight();
            }
        } else if (rightHeight - leftHeight > 1) { // Right imbalance
            int rightLeftHeight = right.getLeft() != null ? right.getLeft().height() : 0;
            int rightRightHeight = right.getRight() != null ? right.getRight().height() : 0;

            if (rightLeftHeight > rightRightHeight) { // Right left imbalance
                this._right = this._right.rotateRight();
                return this.rotateLeft();
            } else if (rightLeftHeight <= rightRightHeight) { // Right right imbalance
                return this.rotateLeft();
            }
        }

        return this;
    }
}