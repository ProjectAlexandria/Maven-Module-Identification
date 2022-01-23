package net.kikkirej.alexandria.module.identification;

import net.kikkirej.alexandria.module.identification.config.GeneralProperties;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class ModuleIdentification implements ExternalTaskHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected final GeneralProperties generalProperties;
    protected final DefiningObjectFinder definingObjectFinder;

    protected ModuleIdentification(GeneralProperties generalProperties, DefiningObjectFinder definingObjectFinder) {
        this.generalProperties = generalProperties;
        this.definingObjectFinder = definingObjectFinder;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try{
            String businessKey = externalTask.getBusinessKey();
            log.debug("Business-Key to analyze: " + businessKey);
            File analysisFolder = getAnalysisFolder(businessKey);
            String semicolonSeparatedPomDirectories = definingObjectFinder.semicolonSeparatedPomDirectoriesIn(analysisFolder, getFilePattern());
            VariableMap variables = Variables.createVariables();
            variables.put(getModulePathsVariableName(), semicolonSeparatedPomDirectories);
            externalTaskService.complete(externalTask, variables);
        }catch (RuntimeException runtimeException){
            externalTaskService.handleBpmnError(externalTask, "undefined", runtimeException.getMessage());
        }
    }

    protected abstract String getModulePathsVariableName();

    protected abstract String getFilePattern();


    private File getAnalysisFolder(String businessKey) {
        String sharedfolder = generalProperties.getSharedfolder();
        String absoluteSharedFolderPath = new File(sharedfolder).getAbsolutePath();
        String analysisFolderPath = absoluteSharedFolderPath + File.separator + businessKey;
        return new File(analysisFolderPath);
    }
}
