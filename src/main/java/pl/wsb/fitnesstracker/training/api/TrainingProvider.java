package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    List<Training> getAllTrainings();

    List<Training> getAllUserTrainings(Long userId);

    /**
     * Retrieves all users that are older than given date.
     * If no User is older than given date, then {@link Optional#empty()} will be returned.
     *
     * @param date The date we compare users to
     * @return An {@link List} containing the located users, or {@link Optional#empty()} if none found
     */
    List<Training> getFinishedTrainings(Date date);

    List<Training> getTrainingsByActivityType(ActivityType activityType);
}
