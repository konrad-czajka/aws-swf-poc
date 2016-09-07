package konradczajka.aws.swfpoc.activities;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;

import java.util.List;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300,
        defaultTaskStartToCloseTimeoutSeconds = 10)
@Activities(version="6.0")
public interface VideoActivities {
    String encode(String source, String profile);
    List<String> packageWithMpegDash(List<String> sources);
}
