package pl.wsb.fitnesstracker.training.api;

import lombok.RequiredArgsConstructor;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.training.internal.TrainingDto;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * REST controller for training operations.
 * Provides basic CRUD endpoints for trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    /**
     * Retrieves all Trainings.
     *
     * @return a list of all trainings as {{@link Training}}
     */
    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .toList();
    }

    /**
     * Retrieves all user's trainings.
     *
     * @return a list of all trainings with given userId as {{@link Training}}
     */
    @GetMapping("/{userId}")
    public List<Training> getAllUserTrainings(@PathVariable Long userId) {
        return trainingService.getAllUserTrainings(userId);
    }

    /**
     * Retrieves trainings that have ended before given date.
     *
     * @return a list of all finished trainings as {{@link Training}}
     */
    @GetMapping("/finished/{afterTime}")
    public List<Training> getFinishedTrainings(@PathVariable LocalDate afterTime) {
        Date date = Date.from(afterTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return trainingService.getFinishedTrainings(date)
                .stream()
                .toList();
    }

    /**
     * Retrieves trainings that have selected activityType.
     *
     * @return a list of all trainings with given activityType as {{@link Training}}
     */
    @GetMapping("/activityType")
    public List<Training> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType);
    }

    /**
     * Adds a new training based on the provided TrainingDto data.
     *
     * @param TrainingDto the training data transfer object with information to create a new training
     * @return {{@link Training}} entity saved to database
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody TrainingDto trainingDto) throws InterruptedException {
        return trainingService.createTraining(trainingDto);
    }

    /**
     * Updates training's data based on the provided TrainingDto data and given id.
     *
     * @param TrainingDto the training data transfer object with new informations about user
     * @return {{@link Training}} entity updated in database
     */
    @PutMapping("/{id}")
    public Training updateTraining(@RequestBody TrainingDto trainingDto, @PathVariable Long id) throws InterruptedException {
        return trainingService.updateTraining(trainingDto, id);
    }
}