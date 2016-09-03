package konradczajka.aws.swfpoc.worker;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import konradczajka.aws.swfpoc.activities.VideoActivitiesImpl;
import konradczajka.aws.swfpoc.workflow.VideoWorkflowImpl;

public class VideoWorker {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        String swfAccessId = "AKIAJ4X56XZF5TC7LNIQ";
        String swfSecretKey = "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";
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.eu-west-1.amazonaws.com");

        String domain = "abc";
        String taskListToPoll = "AbcList";

        ActivityWorker aw = new ActivityWorker(service, domain, taskListToPoll);
        aw.addActivitiesImplementation(new VideoActivitiesImpl());
        aw.start();

        WorkflowWorker wfw = new WorkflowWorker(service, domain, taskListToPoll);
        wfw.addWorkflowImplementationType(VideoWorkflowImpl.class);
        wfw.start();


//        Access Key ID:
//        AKIAJ4X56XZF5TC7LNIQ
//        Secret Access Key:
//        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
    }
}
