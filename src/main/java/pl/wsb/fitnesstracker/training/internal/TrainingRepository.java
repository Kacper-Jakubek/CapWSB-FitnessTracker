package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

interface TrainingRepository extends JpaRepository<Training, Long> {

        default List<Training> getFinishedTrainings(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(date) < 0)
                .toList();
    }

}
