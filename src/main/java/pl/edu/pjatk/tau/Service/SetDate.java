package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.time.LocalDateTime;

public class SetDate {

    public LocalDateTime setCreateDate (TrainingDetails trainingDetails){
        LocalDateTime localDateTime = LocalDateTime.now();

        trainingDetails.setCreatedDate(localDateTime);

        return localDateTime;
    }

    public LocalDateTime setUpdateDate (TrainingDetails trainingDetails){
        LocalDateTime localDateTime = LocalDateTime.now();

        trainingDetails.setCreatedDate(localDateTime);

        return localDateTime;
    }
}
