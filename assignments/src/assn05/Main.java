package assn05;


public class Main {

    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        // SimpleEmergencyRoom simpleRoom = new SimpleEmergencyRoom();

        // simpleRoom.addPatient("Alice", 4);
        // simpleRoom.addPatient("Bob", 10);
        // simpleRoom.addPatient("Chris", 2);
        // simpleRoom.addPatient("Dale", 5);

        // System.out.println(simpleRoom.getPatients());
        // System.out.println(simpleRoom.dequeue().getValue());
        // System.out.println(simpleRoom.getPatients());

       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */

        // BinaryHeap<String, Integer> minHeapRoom = new MinBinHeapER<String, Integer>();

        // minHeapRoom.enqueue("Alice", 4);
        // minHeapRoom.enqueue("Bob", 10);
        // minHeapRoom.enqueue("Chris", 2);
        // minHeapRoom.enqueue("Dale", 5);
        // minHeapRoom.enqueue("Erik", 16);
        // minHeapRoom.enqueue("Finn", 1);
        // minHeapRoom.enqueue("Greg", 3);


        // Prioritized<String, Integer>[] heap = minHeapRoom.getAsArray();

        // for (int i = 0; i < heap.length; i++) {
        //     System.out.printf("%s with priority %d\n", heap[i].getValue(), heap[i].getPriority());
        // }

        // System.out.println(minHeapRoom.dequeue());

        // heap = minHeapRoom.getAsArray();

        // for (int i = 0; i < heap.length; i++) {
        //     System.out.printf("%s with priority %d\n", heap[i].getValue(), heap[i].getPriority());
        // }

        /*
        Part 3
         */
        // MinBinHeapER transfer = new MinBinHeapER(makePatients());
        // Prioritized[] arr = transfer.getAsArray();
        // for(int i = 0; i < transfer.size(); i++) {
        //     System.out.println("Value: " + arr[i].getValue()
        //             + ", Priority: " + arr[i].getPriority());
        // }

        compareRuntimes();

    }

    public static void fillER(MinBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i, 10-i);
        }
        return patients;
    }
    
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];
    	
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (1) Here
        double simpleStart = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            simplePQ.dequeue();
        }
        double simpleEnd = System.nanoTime();

        results[0] = simpleEnd - simpleStart;
        results[1] = results[0] / 100_000;

        // System.out.println(results[0]);
        // System.out.println(results[1]);

        MinBinHeapER binHeap = new MinBinHeapER();
        fillER(binHeap);

        // Code for (2) Here
        double binStart = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            binHeap.dequeue();
        }
        double binEnd = System.nanoTime();

        results[2] = binEnd - binStart;
        results[3] = results[2] / 100_000;

        // System.out.println(results[2]);
        // System.out.println(results[3]);

        return results;
    }



}



