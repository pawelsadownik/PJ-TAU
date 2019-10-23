package pl.edu.pjatk.tau.Repository;

import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.List;

public interface ITrainingRepository {

    List<TrainingDetails> getAllTrainings();
    TrainingDetails getTrainingDetailsById(int id);
    int addTrainingDetails(TrainingDetails trainingDetails);
    int removeTrainingDetails(int id);
    int updateTrainingDetails(TrainingDetails trainingDetails);
}
