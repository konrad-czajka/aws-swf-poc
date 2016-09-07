package konradczajka.aws.swfpoc.workflow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.Promises;
import konradczajka.aws.swfpoc.activities.VideoActivitiesClient;
import konradczajka.aws.swfpoc.activities.VideoActivitiesClientImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasicWorkflowImpl implements BasicWorkflow {

    private VideoActivitiesClient operations = new VideoActivitiesClientImpl();

    @Override
    public void processAsset(String source, Set<String> profiles) {
        List<Promise<String>> encodings = new ArrayList<>();
        for (String profile : profiles) {
            encodings.add(operations.encode(source, profile));
        }
        Promise<List<String>> packagedFiles = operations.packageWithMpegDash(Promises.listOfPromisesToPromise(encodings));
        displayResult(packagedFiles);

    }

    @Asynchronous
    protected void displayResult(Promise<List<String>> packagedFiles) {
        System.out.println("Package contents:");
        packagedFiles.get().forEach(System.out::println);
    }
}
