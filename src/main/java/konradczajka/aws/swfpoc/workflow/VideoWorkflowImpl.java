package konradczajka.aws.swfpoc.workflow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.Promises;
import konradczajka.aws.swfpoc.activities.VideoActivitiesClient;
import konradczajka.aws.swfpoc.activities.VideoActivitiesClientImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VideoWorkflowImpl implements VideoWorkflow {

    private VideoActivitiesClient operations = new VideoActivitiesClientImpl();

    @Override
    public void processAsset(URL source, Set<String> profiles) {
        List<Promise<URL>> encodedFiles = new ArrayList<>();
        for (String profile : profiles) {
            encodedFiles.add(operations.encode(source, profile));
        }

        Promise<List<URL>> packagedFiles = operations.packageWithMpegDash(Promises.listOfPromisesToPromise(encodedFiles));

        displayResult(packagedFiles);
    }

    @Asynchronous
    protected void displayResult(Promise<List<URL>> packagedFiles) {
        System.out.println("Packaged files:");
        packagedFiles.get().forEach(System.out::println);
    }
}
