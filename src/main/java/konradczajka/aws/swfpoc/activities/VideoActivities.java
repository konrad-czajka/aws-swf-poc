package konradczajka.aws.swfpoc.activities;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;

import java.net.URL;
import java.util.List;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300,
        defaultTaskStartToCloseTimeoutSeconds = 10)
@Activities(version="5.0")
public interface VideoActivities {
    URL encode(URL source, String profile);
    List<URL> packageWithMpegDash(List<URL> sources);
}
