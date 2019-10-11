package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.LinkedList;
import java.util.UUID;

public interface ITrainingService {

    LinkedList<TrainingDetails> getAllTrainings();
    TrainingDetails getTrainingDetailsById(int id);
    void addTrainingDetails(TrainingDetails trainingDetails);
    void removeTrainingDetails(int id);
    void updateTrainingDetails(TrainingDetails trainingDetails);
}
