package pl.edu.pjatk.tau.FakeDB;

import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.LinkedList;

public class FakeDB {

    LinkedList<TrainingDetails> trainingDetailsList = new LinkedList<TrainingDetails>();

    public LinkedList<TrainingDetails> getTrainingDetailsList() {
        return trainingDetailsList;
    }

    public void setTrainingDetailsList(LinkedList<TrainingDetails> trainingDetailsList) {
        this.trainingDetailsList = trainingDetailsList;
    }


    public FakeDB(LinkedList<TrainingDetails> trainingDetailsList) {
        this.trainingDetailsList = trainingDetailsList;
    }

    public FakeDB(){
    }
}
