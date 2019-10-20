package pl.edu.pjatk.tau.Service;

import org.junit.*;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TrainingServiceTest  {

    private static ITrainingService mockedService;
    private static TrainingDetails trainingDetails1;
    private static TrainingDetails trainingDetails2;


    TrainingService trainingService = new TrainingService();
    List<String> excersises = Stream.of("pullUps", "pushUps", "barDips")
            .collect(Collectors.toList());

    @BeforeClass
    public static void setUp(){
        mockedService = mock(TrainingService.class);

        trainingDetails1 = new TrainingDetails(0,"Core",
                Arrays.asList("pullUps", "pushUps", "barDips"), 50);

        trainingDetails2 = new TrainingDetails(1,"ABS",
                Arrays.asList("pullUps", "pushUps", "barDips"), 80);

        when(mockedService.getAllTrainings()).thenReturn(Arrays.asList(trainingDetails1, trainingDetails2));
        when(mockedService.getTrainingDetailsById(0)).thenReturn(trainingDetails1);
        when(mockedService.addTrainingDetails(trainingDetails1)).thenReturn(trainingDetails1.getId());
        when(mockedService.updateTrainingDetails(trainingDetails1)).thenReturn(trainingDetails1.getId());
        when(mockedService.removeTrainingDetails(trainingDetails1.getId())).thenReturn(trainingDetails1.getId());
        when(mockedService.canSaveDate(trainingDetails1)).thenReturn(true);
        when(mockedService.canNotSaveDate(trainingDetails1)).thenReturn(false);
    }

    @Test
    public void getAllTrainings_WhenListIsNotEmpty_ShouldReturnNonEmpytList() {
        List<TrainingDetails> allTrainings = trainingService.getAllTrainings();
        assertEquals(2, allTrainings.size());
        TrainingDetails trainingDetails = allTrainings.get(0);
        assertEquals(0, trainingDetails.getId());
        assertEquals(50, trainingDetails.getDuration());
        assertEquals(3, trainingDetails.getExcersises().size());
        assertEquals("Core", trainingDetails.getName());
    }

    @Test
    public void getTrainingDetailsById_GivenValidId_ShouldReturnProperObject() {
        //given
        int id = 0;

        //when
        TrainingDetails trainingDetails = mockedService.getTrainingDetailsById(id);

        //then
        assertNotNull(trainingDetails);
        assertEquals(0, trainingDetails.getId());
        assertEquals(50, trainingDetails.getDuration());
        assertEquals(3, trainingDetails.getExcersises().size());
        assertEquals("Core", trainingDetails.getName());
    }

    @Test
    public void getTrainingDetailsById_GivenInvalidId_ShouldReturnNull() {
        //given
        int id = 10;

        //when
        TrainingDetails trainingDetails = mockedService.getTrainingDetailsById(id);

        //then
        assertNull(trainingDetails);
    }

    @Test
    public void addTrainingDetails_GivenValidTraingDetails_ShouldReturnId() {

        trainingDetails1.setCreatedDate(LocalDateTime.now());
        LocalDateTime createdDate = trainingDetails1.getCreatedDate();
        int id = mockedService.addTrainingDetails(trainingDetails1);

        mockedService.addTrainingDetails(trainingDetails2);
        LocalDateTime aaa = trainingDetails2.getCreatedDate();
        assertNotNull(id);
        assertEquals(trainingDetails1.getId(), id);
        assertEquals(trainingDetails1.getCreatedDate(), createdDate);

    }

    @Test
    public void removeTrainingDetails_GivenValidTrainigDetails_ShoudReturnId() {

        int id = mockedService.removeTrainingDetails(trainingDetails1.getId());

        assertNotNull(id);
        assertEquals(trainingDetails1.getId(), id);

    }

    @Test
    public void updateTrainingDetails_givenValidTrainingDetails_ShouldReturnUpdatedValue() {

        int id = mockedService.updateTrainingDetails(trainingDetails1);

        assertNotNull(id);
        assertEquals(trainingDetails1.getId(), id);
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

    //JUnit 4 @Test(expected = Exception.class)
    @Test(expected = IndexOutOfBoundsException.class)
    public void updateTrainingDetails_givenInValidTrainingDetails_ShouldReturnIndexOfBoundsException() {
        //given
        TrainingDetails trainingtoUpdate = new TrainingDetails(-1, "FBW", excersises, 50);

        trainingService.updateTrainingDetails(trainingtoUpdate);
    }

    @Test
    public void unableToSetCreatedDate_WhenAddingObject_ShouldReturnCreatedDateAsNull() {

        LocalDateTime createdDate = trainingDetails1.getCreatedDate();
        int id = mockedService.addTrainingDetails(trainingDetails1);

        mockedService.addTrainingDetails(trainingDetails1);

        assertNull(trainingDetails1.getCreatedDate());
        assertEquals(trainingDetails1.getCreatedDate(), createdDate);

    }

    @Test
    public void addTrainingDetails_WithEnabledDateOption_ShouldSaveDate() {

        LocalDateTime createdDate = null;

        if(mockedService.canSaveDate(trainingDetails1)) {
            trainingDetails1.setCreatedDate(LocalDateTime.now());
            createdDate = trainingDetails1.getCreatedDate();
        }
        else {
            createdDate = null;
        }
        assertEquals(trainingDetails1.getCreatedDate(), createdDate);
    }

    @Test
    public void addTrainingDetails_WithDisabledDateOption_Should() {

        LocalDateTime createdDate = null;
        trainingDetails1.setSaveCreatedDate(mockedService.canNotSaveDate(trainingDetails1));

        trainingDetails1.setCreatedDate(LocalDateTime.now());
        createdDate = trainingDetails1.getCreatedDate();
        int id = mockedService.addTrainingDetails(trainingDetails1);

        assertNotNull(id);
        assertEquals(trainingDetails1.getCreatedDate(), null);
    }

}