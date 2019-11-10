package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.FakeDB.FakeDB;
import pl.edu.pjatk.tau.Repository.TrainingRepository;
import pl.edu.pjatk.tau.domain.TrainingDetails;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
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

        if (trainingDetails.isSaveCreatedDate()) {
            setDate.setCreateDate(trainingDetails);
        }
        trainingRepository.addTrainingDetails(trainingDetails);

        return trainingRepository.trainingDetailsList.indexOf(trainingDetails);
    }

    public int removeTrainingDetails(int id) {

        return trainingRepository.removeTrainingDetails(id);
    }

    public int updateTrainingDetails(TrainingDetails trainingDetails) {

        if (trainingDetails.isSaveUpdatedDate()) {
            setDate.setUpdateDate(trainingDetails);
        }
        return trainingRepository.updateTrainingDetails(trainingDetails);
    }

    public boolean canSaveCreateDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveCreatedDate(true);
        return true;
    }

    public boolean canNotSaveCreateDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveCreatedDate(false);
        return false;
    }

    public boolean canSaveUpdateDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveUpdatedDate(true);
        return true;
    }

    public boolean canNotSaveUpdateDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveUpdatedDate(false);
        return false;
    }

    public boolean canSaveReadedDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveReadedDate(true);
        return true;
    }

    public boolean canNotSaveReadedDate (TrainingDetails trainingDetails) {
        trainingDetails.setSaveReadedDate(false);
        return false;
    }

    public List<TrainingDetails> getTrainingDetailByRegex(String reg) {

    return trainingRepository.getAllTrainings().stream()
            .filter(s -> s.getName().matches(reg))
            .collect(Collectors.toList());
    }
}
