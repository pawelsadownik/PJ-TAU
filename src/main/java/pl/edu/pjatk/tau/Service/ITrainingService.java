package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.domain.AuditableEntity;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public interface ITrainingService {

    List<TrainingDetails> getAllTrainings();
    TrainingDetails getTrainingDetailsById(int id);
    int addTrainingDetails(TrainingDetails trainingDetails);
    int removeTrainingDetails(int id);
    int updateTrainingDetails(TrainingDetails trainingDetails);
    boolean canSaveDate(TrainingDetails trainingDetails);
    boolean canNotSaveDate(TrainingDetails trainingDetails);
}
