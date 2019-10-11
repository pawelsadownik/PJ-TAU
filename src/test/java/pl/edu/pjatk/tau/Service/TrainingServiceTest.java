package pl.edu.pjatk.tau.Service;

import org.junit.*;
import pl.edu.pjatk.tau.domain.TrainingDetails;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TrainingServiceTest  {

    TrainingService trainingService = new TrainingService();
    List<String> excersises = Stream.of("pullUps", "pushUps", "barDips")
            .collect(Collectors.toList());

    TrainingDetails trainingDetails1 = new TrainingDetails(0, "Core", excersises, 50);
    TrainingDetails trainingDetails2 = new TrainingDetails(1, "ABS", excersises, 55);

    @Before
    public  void onceExecutedBeforeEach() {


        trainingService.fakeDB.getTrainingDetailsList().add(trainingDetails1);
        trainingService.fakeDB.getTrainingDetailsList().add(trainingDetails2);
    }

    @Test
    public void getAllTrainings_WhenListIsNotEmpty_ShouldReturnNonEmpytList() {
        assertFalse(trainingService.getAllTrainings().isEmpty());
        assertEquals(2,trainingService.trainingDetailsList.size());
        assertNotNull(trainingService.getAllTrainings());
    }

    @Test
    public void getTrainingDetailsById_GivenValidId_ShouldReturnProperObject() {
        //given
        int id = 1;

        //when
        TrainingDetails objectToTest = trainingService.getTrainingDetailsById(id);

        //then
        assertNotNull(objectToTest);
        assertEquals("ABS", objectToTest.getName());
    }

    @Test
    public void getTrainingDetailsById_GivenInvalidId_ShouldReturnProperException() {
        //given
        int id = 10;

        //when
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                trainingService.getTrainingDetailsById(id));

        //then
        assertEquals("IndexOutOfBoundsException", exception.getClass().getSimpleName());
    }

    @Test
    public void addTrainingDetails_GivenValidTraingDetails_ShouldReturnIncreasedListCount() {
        //given
        TrainingDetails trainigToAdd = new TrainingDetails();
        int initialCount = trainingService.trainingDetailsList.size();

        //when
        trainingService.trainingDetailsList.add(trainigToAdd);

        //then
        assertEquals(initialCount+1, trainingService.trainingDetailsList.size());

        trainingService.trainingDetailsList.remove(initialCount-1);

    }
    @Test
    public void removeTrainingDetails_GivenValidTrainigDetails_ShoudReturnDecreasedListCount() {
        //given
        TrainingDetails trainingtoDel = new TrainingDetails();
        trainingService.trainingDetailsList.add(trainingtoDel);
        int initialCount = trainingService.trainingDetailsList.size();

        //when
        trainingService.trainingDetailsList.remove(trainingtoDel);

        //then
        assertEquals(initialCount-1, trainingService.trainingDetailsList.size());

    }

    @Test
    public void updateTrainingDetails_givenValidTrainingDetails_ShouldReturnUpdatedValue() {
        //given
        TrainingDetails trainingtoUpdate = new TrainingDetails(0, "FBW", excersises, 50);

        //when
        trainingService.updateTrainingDetails(trainingtoUpdate);

        //then
        assertEquals("FBW", trainingService.trainingDetailsList.get(trainingtoUpdate.getId()).getName());
    }

    @Test
    public void updateTrainingDetails_givenInValidTrainingDetails_ShouldReturnProperException() {
        //given
        TrainingDetails trainingtoUpdate = new TrainingDetails(-1, "FBW", excersises, 50);

        //when
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                trainingService.updateTrainingDetails(trainingtoUpdate));

        //then
        assertEquals("IndexOutOfBoundsException", exception.getClass().getSimpleName());
    }
}