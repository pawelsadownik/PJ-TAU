package pl.edu.pjatk.tau.Repository;

import pl.edu.pjatk.tau.FakeDB.FakeDB;
import pl.edu.pjatk.tau.domain.TrainingDetails;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class TrainingRepository  {

    public FakeDB fakeDB = new FakeDB();

    public LinkedList<TrainingDetails> trainingDetailsList = fakeDB.getTrainingDetailsList();


    public List<TrainingDetails> getAllTrainings() {
        return trainingDetailsList;
    }

    public TrainingDetails getTrainingDetailsById(int id) {
        //Because of using List as a DB
        TrainingDetails trainingDetails =  trainingDetailsList.get(id);

        if (trainingDetails.isSaveReadedDate()) {
            trainingDetails.setLastReadedDate(LocalDateTime.now());
            updateTrainingDetails(trainingDetails);
        }
        return trainingDetails;
    }

    public int addTrainingDetails(TrainingDetails trainingDetails) {

        trainingDetailsList.add(trainingDetails);
        return trainingDetails.getId();
    }

    public int removeTrainingDetails(int id) {
        int idOfObjectToDelete = trainingDetailsList.stream()
                .filter(a -> a.getId() == id).collect(Collectors.toList()).get(0).getId();

        trainingDetailsList.remove(idOfObjectToDelete);
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
}
