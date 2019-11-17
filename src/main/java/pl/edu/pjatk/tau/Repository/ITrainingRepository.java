package pl.edu.pjatk.tau.Repository;

import org.springframework.stereotype.Repository;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.List;

@Repository
public interface ITrainingRepository {

    List<TrainingDetails> getAllTrainings();
    TrainingDetails getTrainingDetailsById(int id);
    int addTrainingDetails(TrainingDetails trainingDetails);
    int removeTrainingDetails(int id);
    int updateTrainingDetails(TrainingDetails trainingDetails);
}
