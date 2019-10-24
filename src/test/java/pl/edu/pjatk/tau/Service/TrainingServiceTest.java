package pl.edu.pjatk.tau.Service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.edu.pjatk.tau.Repository.TrainingRepository;
import pl.edu.pjatk.tau.domain.TrainingDetails;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
    private static SetDate mockSetDate;
    private static TrainingRepository mockRepository;

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

        trainingService.addTrainingDetails(trainingDetails1);
        trainingService.addTrainingDetails(trainingDetails2);
    }

    @Test
    public void getAllTrainings_WhenListIsNotEmpty_ShouldReturnNonEmpytList() {

        List<TrainingDetails> allTrainings = trainingService.getAllTrainings();
        assertNotNull( allTrainings.size());
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
        verify(mockRepository, atMost(1)).addTrainingDetails(trainingDetails3);

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
        verify(mockRepository, atLeastOnce()).updateTrainingDetails(trainingDetails4);

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
        verify(mockRepository, never()).removeTrainingDetails(5);
        verify(mockRepository, only()).getTrainingDetailsById(5);
        //then
        assertNotNull(trainingDetails5.getLastReadedDate());
        assertEquals(expectedDate,actualDate);
    }

    @Test
    public void TrainingDetails_ShouldReturnAllDates() {
        //given
        LocalDateTime expectedCreateDate = LocalDateTime.now();
        LocalDateTime expectedUpdateDate = LocalDateTime.now();
        LocalDateTime expectedReadDate = LocalDateTime.now();

        TrainingDetails trainingDetails6 = new TrainingDetails();
        trainingDetails6.setLastReadedDate(expectedReadDate);
        trainingDetails6.setCreatedDate(expectedCreateDate);
        trainingDetails6.setUpdatedDate(expectedUpdateDate);

        //when mocks
        when(mockRepository.getTrainingDetailsById(6)).thenReturn(trainingDetails6);
        LocalDateTime actualReadedDate = mockRepository.getTrainingDetailsById(6).getLastReadedDate();
        LocalDateTime actualCreatedDate = mockRepository.getTrainingDetailsById(6).getCreatedDate();
        LocalDateTime actualUploadedDate = mockRepository.getTrainingDetailsById(6).getUpdatedDate();
        verify(mockRepository, times(3)).getTrainingDetailsById(6);
        verify(mockRepository, atLeast(2)).getTrainingDetailsById(6);


        //when real objects
        TrainingDetails trainingDetails7 = new TrainingDetails();
        int id = trainingService.addTrainingDetails(trainingDetails7);
        trainingService.updateTrainingDetails(trainingDetails7);
        trainingService.getTrainingDetailsById(id);
        LocalDateTime readedDate = trainingService.getTrainingDetailsById(id).getLastReadedDate();
        LocalDateTime createdDate = trainingService.getTrainingDetailsById(id).getCreatedDate();
        LocalDateTime updatedDate = trainingService.getTrainingDetailsById(id).getUpdatedDate();

        //then mocks
        assertEquals(expectedCreateDate,actualCreatedDate);
        assertEquals(expectedReadDate,actualReadedDate);
        assertEquals(expectedUpdateDate,actualUploadedDate);

        //then real
        assertNotNull(createdDate);
        assertNotNull(readedDate);
        assertNotNull(updatedDate);
    }


    @Test
    public void trainingDetails_WithDeniedCreationDate_ShouldReturnNullAsDate(){
        TrainingDetails trainingDetails8 = new TrainingDetails();

        trainingService.canNotSaveCreateDate(trainingDetails8);

        int id = trainingService.addTrainingDetails(trainingDetails8);

        LocalDateTime actualDate = trainingService.getTrainingDetailsById(id).getCreatedDate();

        assertNull(actualDate);
    }

    @Test
    public void trainingDetails_WithPermittedCreationDate_ShouldReturnDate(){
        TrainingDetails trainingDetails9 = new TrainingDetails();

        trainingService.canSaveCreateDate(trainingDetails9);

        int id = trainingService.addTrainingDetails(trainingDetails9);

        LocalDateTime actualDate = trainingService.getTrainingDetailsById(id).getCreatedDate();

        assertNotNull(actualDate);
    }


    @Test
    public void trainingDetails_WithDeniedUpdatedDate_ShouldReturnNullAsDate(){
        TrainingDetails trainingDetails10 = new TrainingDetails();

        trainingService.canNotSaveUpdateDate(trainingDetails10);
        trainingService.addTrainingDetails(trainingDetails10);
        trainingService.updateTrainingDetails(trainingDetails10);

        LocalDateTime actualDate = trainingDetails10.getUpdatedDate();

        assertNull(actualDate);
    }

    @Test
    public void trainingDetails_WithPermittedUpdatedDate_ShouldReturnDate(){
        TrainingDetails trainingDetails11 = new TrainingDetails();

        trainingService.canSaveUpdateDate(trainingDetails11);
        trainingService.addTrainingDetails(trainingDetails11);
        trainingService.updateTrainingDetails(trainingDetails11);

        LocalDateTime actualDate = trainingDetails11.getUpdatedDate();

        assertNotNull(actualDate);
    }

    @Test
    public void trainingDetails_WithDeniedReadDate_ShouldReturnNullAsDate(){
        TrainingDetails trainingDetails12 = new TrainingDetails();

        trainingService.canNotSaveReadedDate(trainingDetails12);

        int id = trainingService.addTrainingDetails(trainingDetails12);
        trainingService.getTrainingDetailsById(id);
        LocalDateTime readedDate = trainingService.getTrainingDetailsById(id).getLastReadedDate();
        assertNull(readedDate);
    }

    @Test
    public void trainingDetails_WithPermittedReadDate_ShouldReturnDate(){
        TrainingDetails trainingDetails13 = new TrainingDetails();

        trainingService.canSaveReadedDate(trainingDetails13);

        int id = trainingService.addTrainingDetails(trainingDetails13);
        trainingService.getTrainingDetailsById(id);
        LocalDateTime readedDate = trainingService.getTrainingDetailsById(id).getLastReadedDate();
        assertNotNull(readedDate);
    }
}