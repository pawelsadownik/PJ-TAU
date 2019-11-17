package pl.edu.pjatk.tau.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjatk.tau.Service.ITrainingService;

@RestController
public class MainController {

    private ITrainingService trainingService;

    @Autowired
    public MainController( ITrainingService trainingService){
        this.trainingService = trainingService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String myAction() {

        return trainingService.getAllTrainings().toString();
    }
}