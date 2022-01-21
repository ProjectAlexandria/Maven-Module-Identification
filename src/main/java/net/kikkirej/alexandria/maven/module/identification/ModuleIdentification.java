package net.kikkirej.alexandria.maven.module.identification;

import net.kikkirej.alexandria.maven.module.identification.config.GeneralProperties;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ExternalTaskSubscription("maven-module-identification")
public class ModuleIdentification implements ExternalTaskHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GeneralProperties generalProperties;
    private final PomFinder pomFinder;

    @Autowired
    public ModuleIdentification(GeneralProperties generalProperties, PomFinder pomFinder) {
        this.generalProperties = generalProperties;
        this.pomFinder = pomFinder;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try{
            String businessKey = externalTask.getBusinessKey();
            log.debug("");
            File analysisFolder = getAnalysisFolder(businessKey);
            String semicolonSeparatedPomDirectories = pomFinder.semicolonSeparatedPomDirectoriesIn(analysisFolder);
            VariableMap variables = Variables.createVariables();
            variables.put("maven_module_directories", semicolonSeparatedPomDirectories);
            externalTaskService.complete(externalTask, variables);
        }catch (RuntimeException runtimeException){
            externalTaskService.handleBpmnError(externalTask, "undefined", runtimeException.getMessage());
        }
    }

    private File getAnalysisFolder(String businessKey) {
        String sharedfolder = generalProperties.getSharedfolder();
        String absoluteSharedFolderPath = new File(sharedfolder).getAbsolutePath();
        String analysisFolderPath = absoluteSharedFolderPath + File.separator + businessKey;
        return new File(analysisFolderPath);
    }
}
