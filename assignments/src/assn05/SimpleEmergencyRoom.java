package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        int lowestPriorityIndex = 0;
        Patient lowestPriority = patients.get(lowestPriorityIndex);

        for (int i = 1; i < patients.size(); i++) {
            Patient currentPatient = patients.get(i);

            if (currentPatient.getPriority().compareTo(lowestPriority.getPriority()) < 0) {
                lowestPriorityIndex = i;
                lowestPriority = currentPatient;
            }

        }

    	return this.patients.remove(lowestPriorityIndex);
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
