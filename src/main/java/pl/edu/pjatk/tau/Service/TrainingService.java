package pl.edu.pjatk.tau.Service;

import org.junit.Before;
import pl.edu.pjatk.tau.FakeDB.FakeDB;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Collectors;

public class TrainingService implements ITrainingService {


    FakeDB fakeDB = new FakeDB();

    LinkedList<TrainingDetails> trainingDetailsList = fakeDB.getTrainingDetailsList();

    @Override
    public LinkedList<TrainingDetails> getAllTrainings() {
        return trainingDetailsList;
    }

    @Override
    public TrainingDetails getTrainingDetailsById(int id) {
        return trainingDetailsList.stream()
                .filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public void addTrainingDetails(TrainingDetails trainingDetails) {
        trainingDetailsList.add(trainingDetails);
    }

    @Override
    public void removeTrainingDetails(int id) {

        TrainingDetails objectToDelete = trainingDetailsList.stream()
                .filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);

        int index = trainingDetailsList.indexOf(objectToDelete);
        trainingDetailsList.remove(index);

        trainingDetailsList.removeIf(x -> x.getId() == id);
    }

    @Override
    public void updateTrainingDetails(TrainingDetails trainingDetails) {

        int index =  trainingDetailsList.indexOf(trainingDetailsList
                .stream()
                .filter(a -> a.getId() == trainingDetails.getId())
                .collect(Collectors.toList()).get(0));

        trainingDetailsList.set(index, trainingDetails);
    }
}
