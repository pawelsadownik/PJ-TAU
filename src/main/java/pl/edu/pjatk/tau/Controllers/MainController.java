package pl.edu.pjatk.tau.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.tau.Repository.TrainingDetailsRepository;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class MainController {

    @Autowired
    private TrainingDetailsRepository trainingRepository;

    @GetMapping("/trainings")
    public List<TrainingDetails> getALlTrainings() {

        return trainingRepository.findAll();
    }

    @PostMapping("/trainings")
    public int saveTrainings(@Valid @RequestBody TrainingDetails trainingDetails ) throws IOException, SQLException {

        TrainingDetails itemInDb = trainingRepository.save(trainingDetails);
        return itemInDb.getId();
    }

    @DeleteMapping("/trainings/{id}")
    public ResponseEntity<?> deleteTrainings(@PathVariable(value = "id") Integer id) {
        trainingRepository.delete(trainingRepository.getOne(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/trainings/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody TrainingDetails trainingDetails, @PathVariable Integer id) {

        Optional<TrainingDetails> trainingOptional = trainingRepository.findById(id);

        if (!trainingOptional.isPresent())
            return ResponseEntity.notFound().build();

        trainingDetails.setId((int)(id));

        trainingRepository.save(trainingDetails);

        return ResponseEntity.noContent().build();
    }


}