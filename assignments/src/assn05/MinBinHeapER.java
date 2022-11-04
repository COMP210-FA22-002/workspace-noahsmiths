package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MinBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MinBinHeapER(Prioritized<V, P>[] initialEntries ) {
        this._heap = new ArrayList<>();

        for (int i = 0; i < initialEntries.length; i++) {
            this._heap.add(initialEntries[i]);
        }

        for (int j = (this.size() - 2 / 2); j >= 0; j--) {
            // Bubble down
            int i = j;

            while ((i * 2) + 1 < this.size()) { // Guaranteed left exists if run
                Prioritized<V,P> parent = this._heap.get(i);
                Prioritized<V,P> leftChild = this._heap.get((i * 2) + 1);
                Prioritized<V,P> rightChild = null;

                if ((i * 2) + 2 < this.size()) {
                    rightChild = this._heap.get((i * 2) + 2);
                }

                if (rightChild != null) { // Both children exist
                    if (leftChild.getPriority().compareTo(parent.getPriority()) < 0 || rightChild.getPriority().compareTo(parent.getPriority()) < 0) { // If at least one of the priorities is smaller than the parent priority
                        if (leftChild.getPriority().compareTo(rightChild.getPriority()) < 0) { // Swap parent and left
                            this._heap.set(i, leftChild);
                            i = (i * 2) + 1;
                        } else { // Swap parent and right
                            this._heap.set(i, rightChild);
                            i = (i * 2) + 2;
                        }

                        this._heap.set(i, parent);
                    } else {
                        break;
                    }
                } else { // Only left child exists
                    if (leftChild.getPriority().compareTo(parent.getPriority()) < 0) {
                        this._heap.set(i, leftChild);
                        i = (i * 2) + 1;
                        this._heap.set(i, parent);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Prioritized<V,P> patient = new Patient<V,P>(value, priority);

        this._heap.add(patient); // Add element to heap;

        int i = this._heap.size() - 1;

        while ((i - 1) / 2 >= 0) { // Guaranteed if run to have parent
            // (i - 1) / 2
            Prioritized<V,P> child = this._heap.get(i);
            Prioritized<V,P> parent = this._heap.get((i - 1) / 2);

            if (child.getPriority().compareTo(parent.getPriority()) < 0) { // Swap child and parent
                this._heap.set(i, parent);
                i = (i - 1) / 2;
                this._heap.set(i, child);
            } else {
                break;
            }
        }
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Prioritized<V,P> patient = new Patient<V,P>(value);

        this._heap.add(patient); // Add element to heap;

        // Bubble up
        int i = this._heap.size() - 1;

        while ((i - 1) / 2 >= 0) { // Guaranteed if run to have parent
            // (i - 1) / 2
            Prioritized<V,P> child = this._heap.get(i);
            Prioritized<V,P> parent = this._heap.get((i - 1) / 2);

            if (child.getPriority().compareTo(parent.getPriority()) < 0) { // Swap child and parent
                this._heap.set(i, parent);
                i = (i - 1) / 2;
                this._heap.set(i, child);
            } else {
                break;
            }
        }
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (this._heap.size() == 0) {
            return null;
        }

        Prioritized<V,P> patient = this._heap.get(0);
        this._heap.set(0, this._heap.get(this.size() - 1)); // Replace root with lastly inserted element
        this._heap.remove(this.size() - 1); // Delete last element
        
        // Bubble down
        int i = 0;

        while ((i * 2) + 1 < this.size()) { // Guaranteed left exists if run
            Prioritized<V,P> parent = this._heap.get(i);
            Prioritized<V,P> leftChild = this._heap.get((i * 2) + 1);
            Prioritized<V,P> rightChild = null;

            if ((i * 2) + 2 < this.size()) {
                rightChild = this._heap.get((i * 2) + 2);
            }

            if (rightChild != null) { // Both children exist
                if (leftChild.getPriority().compareTo(parent.getPriority()) < 0 || rightChild.getPriority().compareTo(parent.getPriority()) < 0) { // If at least one of the priorities is smaller than the parent priority
                    if (leftChild.getPriority().compareTo(rightChild.getPriority()) < 0) { // Swap parent and left
                        this._heap.set(i, leftChild);
                        i = (i * 2) + 1;
                    } else { // Swap parent and right
                        this._heap.set(i, rightChild);
                        i = (i * 2) + 2;
                    }

                    this._heap.set(i, parent);
                } else {
                    break;
                }
            } else { // Only left child exists
                if (leftChild.getPriority().compareTo(parent.getPriority()) < 0) {
                    this._heap.set(i, leftChild);
                    i = (i * 2) + 1;
                    this._heap.set(i, parent);
                } else {
                    break;
                }
            }
        }

        return patient.getValue();
    }

    // TODO: getMin
    @Override
    public V getMin() {
        if (this._heap.size() > 0) {
            return this._heap.get(0).getValue();
        } else {
            return null;
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}
