package jBehave;

import org.jbehave.core.annotations.*;

import pl.edu.pjatk.tau.Service.TrainingService;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class JBehaveSteps {

    private TrainingService trainingService;
    private ArrayList<TrainingDetails> trainingDetailsList;
    private List<TrainingDetails> filteredList;
    private List<TrainingDetails> givenList;

    @BeforeStories
    public void beforeStories() {
        trainingService = new TrainingService();

         TrainingDetails trainingDetails1 = new TrainingDetails(0, "Core",
                Arrays.asList("pullUps", "pushUps", "barDips"), 50);

        TrainingDetails trainingDetails2 = new TrainingDetails(1, "ABS",
                Arrays.asList("pullUps", "pushUps", "barDips"), 60);

        TrainingDetails trainingDetails3 = new TrainingDetails(2, "FBW",
                Arrays.asList("pullUps", "pushUps", "barDips"), 35);

        trainingService.addTrainingDetails(trainingDetails1);
        trainingService.addTrainingDetails(trainingDetails2);
        trainingService.addTrainingDetails(trainingDetails3);
    }
    @Given("the collection")
    public void theCollection() {
        trainingService.getAllTrainings().size();
    }


    @When("When I type a regex: $regex")
    public void whenITypeARegex(String regex) {
        filteredList = trainingService.getTrainingDetailByRegex(regex);
    }

    @Then("I got a result: $trainingsList")
    public void iGotAResult(List<TestTrainingDetails> arg) {
        assertTrue(filteredList.size() == (arg.size()));
        assertTrue(filteredList.get(0).getName().equals(arg.get(0).getName()));
    }

    @Given("there is a collection of entities: $collection")
    public void theCollection(List<TestTrainingDetails> testTrainingDetailsList) {
        trainingDetailsList.size();
    }


    @When("When I give a list: $list")
    public void whenIGiveAList(List<TrainingDetails> listToRemove) {
        LinkedList<TrainingDetails> finalList = new LinkedList<>(listToRemove);
        trainingService.removeTrainigDetailsByGivenList(finalList);    }

    @Then("I get updatedList: $updatedList")
    public void iGetUpdatedList(List<TestTrainingDetails> arg) {
        assertTrue(trainingService.getAllTrainings().size() == (arg.size()));
        assertTrue(trainingService.getAllTrainings().get(0).getName().equals(arg.get(0).getName()));
    }
}
