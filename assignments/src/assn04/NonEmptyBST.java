package assn04;


import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		switch (element.compareTo(this.getElement())) {
			case 0: // Number is equal to current
				// return this;
				break;
			case -1: // Number is less than current
				if (this.getLeft().isEmpty()) {
					this._left = new NonEmptyBST<T>(element);
				} else {
					this.getLeft().insert(element);
				}
				break;
				// return this;
			case 1: // Number is greater than current
				if (this.getRight().isEmpty()) {
					this._right = new NonEmptyBST<T>(element);
				} else {
					this.getRight().insert(element);
				}
				break;
		}

		return this;
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		switch (element.compareTo(this.getElement())) {
			case 0: // Number is equal to current
				if (this.getLeft().isEmpty() && this.getRight().isEmpty()) { // No children
					return new EmptyBST<T>();
				}

				if (this.getLeft().isEmpty()) { // Only right child
					return this.getRight();
				} else if (this.getRight().isEmpty()) { // Only left child
					return this.getLeft();
				} else { // Both children exist
					NonEmptyBST<T> currentNode = (NonEmptyBST<T>) this.getRight();

					while (!currentNode.getLeft().isEmpty()) { // Get leftmost child of right side, aka closest larger value to the one being removed
						currentNode = (NonEmptyBST<T>) currentNode.getLeft();
					}

					T temp = currentNode.getElement(); // Store the value
					this.remove(currentNode.getElement()); // Remove it from wherever it was
					this._element = temp; // Assign the value to the root of the subtree

					return this;
				}
			case -1: // Number is less than current
				this._left = this.getLeft().remove(element);
				break;
			case 1: // Number is greater than current
				this._right = this.getRight().remove(element);
				break;
		}

		return this;
	}


	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(this.getElement() + " ");
		
		if (!this.getLeft().isEmpty()) {
			this.getLeft().printPreOrderTraversal();
		}

		if (!this.getRight().isEmpty()) {
			this.getRight().printPreOrderTraversal();
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (!this.getLeft().isEmpty()) {
			this.getLeft().printPostOrderTraversal();
		}

		if (!this.getRight().isEmpty()) {
			this.getRight().printPostOrderTraversal();
		}
	
		System.out.print(this.getElement() + " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		LinkedList queue = new LinkedList<BST<T>>();
		queue.add(this);

		while (!queue.isEmpty()) {
			BST<T> tree = (BST<T>) queue.poll();

			System.out.print(tree.getElement() + " ");

			if (!tree.getLeft().isEmpty()) {
				queue.add(tree.getLeft());
			}

			if (!tree.getRight().isEmpty()) {
				queue.add(tree.getRight());
			}
		}
	
	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	
	
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
