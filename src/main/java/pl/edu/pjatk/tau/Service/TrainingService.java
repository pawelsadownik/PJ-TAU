package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.FakeDB.FakeDB;
import pl.edu.pjatk.tau.Repository.TrainingRepository;
import pl.edu.pjatk.tau.domain.TrainingDetails;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingService implements ITrainingService {

    SetDate setDate = new SetDate();
    TrainingRepository trainingRepository = new TrainingRepository();


    public List<TrainingDetails> getAllTrainings() {
        return trainingRepository.getAllTrainings();
    }

    public TrainingDetails getTrainingDetailsById(int id) {

        return trainingRepository.getTrainingDetailsById(id);
    }

    public int addTrainingDetails(TrainingDetails trainingDetails) {

        setDate.setCreateDate(trainingDetails);

        return trainingRepository.addTrainingDetails(trainingDetails);
    }

    public int removeTrainingDetails(int id) {

        return trainingRepository.removeTrainingDetails(id);
    }

    public int updateTrainingDetails(TrainingDetails trainingDetails) {

        setDate.setUpdateDate(trainingDetails);
        return trainingRepository.updateTrainingDetails(trainingDetails);
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
