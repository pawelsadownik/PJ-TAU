package pl.edu.pjatk.tau.Service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.cglib.core.Local;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.pjatk.tau.Repository.TrainingRepository;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class TrainingServiceTest  {

    private static TrainingService trainingService = new TrainingService();
    private static TrainingRepository trainingRepository = new TrainingRepository();
    private static TrainingDetails trainingDetails1;
    private static TrainingDetails trainingDetails2;
    private static TrainingDetails trainingDetails3;
    private static SetDate mockSetDate;
    private static TrainingRepository mockRepository;

    @Mock
    List<TrainingDetails> trainingDetailsList;

    List<String> excersises = Stream.of("pullUps", "pushUps", "barDips")
            .collect(Collectors.toList());

    @BeforeClass
    public static void setUp(){
        mockSetDate = mock(SetDate.class);
        mockRepository = mock(TrainingRepository.class);

        trainingDetails1 = new TrainingDetails(0,"Core",
                Arrays.asList("pullUps", "pushUps", "barDips"), 50);

        trainingDetails2 = new TrainingDetails(1,"ABS",
                Arrays.asList("pullUps", "pushUps", "barDips"), 80);
    }

    @Before
    public  void onceExecutedBeforeEach() {
        trainingService.addTrainingDetails(trainingDetails1);
        trainingService.addTrainingDetails(trainingDetails2);
    }

    @Test
    public void getAllTrainings_WhenListIsNotEmpty_ShouldReturnNonEmpytList() {
        List<TrainingDetails> allTrainings = trainingService.getAllTrainings();
        assertNotNull( allTrainings.size());
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
        TrainingDetails trainingDetails = trainingService.getTrainingDetailsById(id);

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
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () ->
                trainingService.getTrainingDetailsById(id));

        //then
        assertEquals("IndexOutOfBoundsException", exception.getClass().getSimpleName());
    }

    @Test
    public void addTrainingDetails_GivenValidTraingDetails_ShouldReturnIncreasedListCount() {
        //given
        TrainingDetails trainigToAdd = new TrainingDetails();
        int initialCount = trainingService.getAllTrainings().size();

        //when
        trainingService.addTrainingDetails(trainigToAdd);

        //then
        assertEquals(initialCount+1, trainingService.getAllTrainings().size());
    }

    @Test
    public void removeTrainingDetails_GivenValidTrainigDetails_ShoudReturnDecreasedListCount() {
        //given
        TrainingDetails trainingtoDel = new TrainingDetails();
        trainingService.addTrainingDetails(trainingtoDel);
        int initialCount = trainingService.getAllTrainings().size();

        //when
        trainingService.removeTrainingDetails(trainingtoDel.getId());

        //then
        assertEquals(initialCount-1, trainingService.getAllTrainings().size());
    }

    @Test
    public void updateTrainingDetails_givenValidTrainingDetails_ShouldReturnUpdatedValue() {
        //given
        TrainingDetails trainingtoUpdate = new TrainingDetails(0, "FBW", excersises, 50);

        //when
        trainingService.updateTrainingDetails(trainingtoUpdate);

        //then
        assertEquals("FBW", trainingService.getTrainingDetailsById(trainingtoUpdate.getId()).getName());
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
    public void addTrainingDetails_WithValidObject_ShouldSaveCreatedDate() {
        //given
        LocalDateTime expectedDate = LocalDateTime.now();
        TrainingDetails trainingDetails3 = new TrainingDetails();
        trainingDetails3.setCreatedDate(expectedDate);

        //when
        when(mockRepository.addTrainingDetails(trainingDetails3)).thenReturn(3);
        when(mockRepository.getTrainingDetailsById(3)).thenReturn(trainingDetails3);
        int id = mockRepository.addTrainingDetails(trainingDetails3);
        LocalDateTime actualDate = mockRepository.getTrainingDetailsById(id).getCreatedDate();

        //then
        assertNotNull(trainingDetails3.getCreatedDate());
        assertEquals(expectedDate,actualDate);
    }

    @Test
    public void updateTrainingDetails_WithValidObject_ShouldSaveUpdateDate() {
        //given
        LocalDateTime expectedDate = LocalDateTime.now();
        TrainingDetails trainingDetails4 = new TrainingDetails();
        trainingDetails4.setCreatedDate(expectedDate);

        //when
        when(mockRepository.updateTrainingDetails(trainingDetails4)).thenReturn(4);
        when(mockRepository.getTrainingDetailsById(4)).thenReturn(trainingDetails4);
        int id = mockRepository.updateTrainingDetails(trainingDetails4);
        LocalDateTime actualDate = mockRepository.getTrainingDetailsById(id).getCreatedDate();

        //then
        assertNotNull(trainingDetails4.getCreatedDate());
        assertEquals(expectedDate,actualDate);
    }

    @Test
    public void getTrainingDetails_WithValidObject_ShouldSaveReadDate() {
        //given
        LocalDateTime expectedDate = LocalDateTime.now();
        TrainingDetails trainingDetails5 = new TrainingDetails();
        trainingDetails5.setLastReadedDate(expectedDate);

        //when
        when(mockRepository.getTrainingDetailsById(5)).thenReturn(trainingDetails5);
        LocalDateTime actualDate = mockRepository.getTrainingDetailsById(5).getLastReadedDate();

        //then
        assertNotNull(trainingDetails5.getLastReadedDate());
        assertEquals(expectedDate,actualDate);
    }

    @Test
    public void collectionSizeInvocationNumber() {
        trainingDetailsList.size();
        trainingDetailsList.size();
        verify(trainingDetailsList, times(2)).size();
    }
}