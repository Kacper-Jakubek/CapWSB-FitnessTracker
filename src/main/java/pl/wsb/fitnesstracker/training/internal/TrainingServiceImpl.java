package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    private final TrainingMapper trainingMapper;

    private final UserServiceImpl userService;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getAllUserTrainings(Long userId) {
        return trainingRepository.findAll().stream().filter(training -> training.getUser().getId().equals(userId)).toList();
    }

    @Override
    public List<Training> getFinishedTrainings(final Date date) {
        return trainingRepository.getFinishedTrainings(date);
    }

    @Override
    public List<Training> getTrainingsByActivityType(final ActivityType activityType) {
        return trainingRepository.findAll().stream().filter(training -> training.getActivityType() == activityType).toList();
    }

    @Override
    public Training createTraining(TrainingDto trainingDto) {
        Optional<User> user = userService.getUser(trainingDto.getUserId());
        if (user.isPresent() == false) {
            throw new IllegalArgumentException("There is no user with given ID, save is not permitted");
        }
        Training newTraining = trainingMapper.toEntity(trainingDto, user.get());
        log.info("Creating Training {}", newTraining);
        return trainingRepository.save(newTraining);
    }

    @Override
    public Training updateTraining(final TrainingDto trainingDto, final Long id) {
        Optional<User> user = userService.getUser(trainingDto.getUserId());
        if (user.isPresent() == false) {
            throw new IllegalArgumentException("There is no user with given ID, save is not permitted");
        }
        Training newTraining = trainingMapper.toEntity(trainingDto, user.get());
        newTraining.setId(id);
        log.info("Creating Training {}", newTraining);
        return trainingRepository.save(newTraining);
    }
}
