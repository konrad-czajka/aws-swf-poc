package konradczajka.aws.swfpoc.starter;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import konradczajka.aws.swfpoc.workflow.VideoWorkflowClientExternal;
import konradczajka.aws.swfpoc.workflow.VideoWorkflowClientExternalFactory;
import konradczajka.aws.swfpoc.workflow.VideoWorkflowClientExternalFactoryImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

public class VideoMain {
    public static void main(String[] args) throws MalformedURLException {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        String swfAccessId = "AKIAJ4X56XZF5TC7LNIQ";
        String swfSecretKey = "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";
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.eu-west-1.amazonaws.com");

        String domain = "abc";

        VideoWorkflowClientExternalFactory factory = new VideoWorkflowClientExternalFactoryImpl(service, domain);
        VideoWorkflowClientExternal video = factory.getClient("someID2");

        video.processAsset(new URL("ftp://bucket-name/file.mp4"), new HashSet<>(Arrays.asList("profile-1", "profile-2", "profile-3")));
    }
}
