package konradczajka.aws.swfpoc.workflow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;

import java.net.URL;
import java.util.Set;

@Workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 3600)
public interface VideoWorkflow {
    @Execute(version = "5.0")
    void processAsset(URL source, Set<String> profiles);
}

