package pl.wsb.fitnesstracker.training.internal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TrainingDto {

    private Long id;

    private Long userId;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;

    private double distance;

    private double averageSpeed;

    public TrainingDto(
            final Long id,
            final Long userId,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.id = id;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}