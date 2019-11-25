package pl.edu.pjatk.tau.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.tau.domain.TrainingDetails;

import java.util.List;

@Repository
public interface TrainingDetailsRepository extends JpaRepository<TrainingDetails, Integer> {

}

