package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.domain.AuditableEntity;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public interface ITrainingService {

    List<TrainingDetails> getAllTrainings();
    TrainingDetails getTrainingDetailsById(int id);

    List<TrainingDetails> getTrainingDetailByRegex(String pattern);

    void removeTrainigDetailsByGivenList(LinkedList<TrainingDetails> listToremove);

    int addTrainingDetails(TrainingDetails trainingDetails);
    int removeTrainingDetails(int id);
    int updateTrainingDetails(TrainingDetails trainingDetails);

    boolean canSaveCreateDate(TrainingDetails trainingDetails);
    boolean canNotSaveCreateDate(TrainingDetails trainingDetails);

    boolean canSaveUpdateDate(TrainingDetails trainingDetails);
    boolean canNotSaveUpdateDate(TrainingDetails trainingDetails);

    boolean canSaveReadedDate(TrainingDetails trainingDetails);
    boolean canNotSaveReadedDate(TrainingDetails trainingDetails);
}
