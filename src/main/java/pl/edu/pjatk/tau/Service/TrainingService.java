package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.FakeDB.FakeDB;
import pl.edu.pjatk.tau.domain.TrainingDetails;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingService implements ITrainingService {

    FakeDB fakeDB = new FakeDB();

    LinkedList<TrainingDetails> trainingDetailsList = fakeDB.getTrainingDetailsList();

    public List<TrainingDetails> getAllTrainings() {
        return trainingDetailsList;
    }

    public TrainingDetails getTrainingDetailsById(int id) {
        return trainingDetailsList.stream()
                .filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);
    }

    public int addTrainingDetails(TrainingDetails trainingDetails) {

        trainingDetailsList.add(trainingDetails);
        trainingDetails.setCreatedDate(LocalDateTime.now());
        return trainingDetails.getId();
    }

    public int removeTrainingDetails(int id) {

        TrainingDetails objectToDelete = trainingDetailsList.stream()
                .filter(a -> a.getId() == id).collect(Collectors.toList()).get(0);

        int index = trainingDetailsList.indexOf(objectToDelete);
        trainingDetailsList.remove(index);
        trainingDetailsList.removeIf(x -> x.getId() == id);

        return id;
    }

    public int updateTrainingDetails(TrainingDetails trainingDetails) {

        int index =  trainingDetailsList.indexOf(trainingDetailsList
                .stream()
                .filter(a -> a.getId() == trainingDetails.getId())
                .collect(Collectors.toList()).get(0));

        trainingDetailsList.set(index, trainingDetails);

        return trainingDetails.getId();
    }

    public boolean canSaveDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveCreatedDate(true);
        return true;
    }

    public boolean canNotSaveDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveCreatedDate(false);
        return false;
    }
}
