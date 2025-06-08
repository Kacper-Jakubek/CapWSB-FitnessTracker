package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.TrainingDto;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    Training createTraining(TrainingDto trainingDto);

    Training updateTraining(TrainingDto trainingDto, Long id);
}
