package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

@Component
public class TrainingMapper {

    public TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    public Training toEntity(TrainingDto trainingDto, User user) {
        return new Training(user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }
}
