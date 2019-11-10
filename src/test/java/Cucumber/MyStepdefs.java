package Cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.edu.pjatk.tau.Service.TrainingService;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class MyStepdefs {

    private TrainingService trainingService;
    private ArrayList<TrainingDetails> trainingDetailsList;
    private List<TrainingDetails> filteredList;
    private static TrainingDetails trainingDetails1;
    private static TrainingDetails trainingDetails2;
    private static TrainingDetails trainingDetails3;

    @Before
    public void setUp() {
        trainingService = new TrainingService();

        trainingDetails1 = new TrainingDetails(0,"Core",
                Arrays.asList("pullUps", "pushUps", "barDips"), 50);

        trainingDetails2 = new TrainingDetails(1,"ABS",
               Arrays.asList("pullUps", "pushUps", "barDips"), 60);

        trainingDetails3 = new TrainingDetails(2,"FBW",
                Arrays.asList("pullUps", "pushUps", "barDips"), 35);

        trainingService.addTrainingDetails(trainingDetails1);
        trainingService.addTrainingDetails(trainingDetails2);
        trainingService.addTrainingDetails(trainingDetails3);
    }

    @Given("^there is a collection of twoTrainings")
    public void thereIsACollectionOftwoTrainings(List<TrainingDetails> arg) throws Throwable {
        trainingDetailsList = new ArrayList<TrainingDetails>();
        trainingDetailsList.addAll(arg);
    }

    @When("^I give a <name>$")
    public void iGiveName() throws Throwable {
        trainingDetails1 = trainingDetailsList.get(0);
    }

    @Then("^I get$")
    public void i_get(List<TrainingDetails> arg) throws Throwable {
        assertTrue(trainingDetails1.getName().equals(arg.get(0).getName()));
    }

    @Given("^there is a collection of entities")
    public void thereIsACollectionOfEntities(List<TrainingDetails> arg) throws Throwable {
        trainingDetailsList = new ArrayList<TrainingDetails>();
        trainingDetailsList.addAll(arg);
    }

    @When("^I give a regex (.*)$")
    public void iGiveRegex(String Arg) throws Throwable {
        filteredList = trainingService.getTrainingDetailByRegex(Arg);

    }

    @Then("^I get list$")
    public void i_get_list(List<TrainingDetails> arg) throws Throwable {
        assertTrue(filteredList.size() == (arg.size()));
        assertTrue(filteredList.get(0).getName().equals(arg.get(0).getName()));
    }

    @When("^I give a list$")
    public void i_give_a_list(List<TrainingDetails> listToRemove) throws Exception {

        LinkedList<TrainingDetails> finalList = new LinkedList<>(listToRemove);
        trainingService.removeTrainigDetailsByGivenList(finalList);
    }

    @Then("^I get updatedList$")
    public void i_get_updatedList(List<TrainingDetails> arg) throws Throwable {
        assertTrue(trainingService.getAllTrainings().size() == (arg.size()));
        assertTrue(trainingService.getAllTrainings().get(0).getName().equals(arg.get(0).getName()));
    }
}
