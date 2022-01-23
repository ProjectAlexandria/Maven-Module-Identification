package net.kikkirej.alexandria.module.identification;

import net.kikkirej.alexandria.module.identification.config.GeneralProperties;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("npm-project-identification")
public class NPMModuleIdentification extends ModuleIdentification {
    @Autowired
    public NPMModuleIdentification(GeneralProperties generalProperties, DefiningObjectFinder definingObjectFinder) {
        super(generalProperties, definingObjectFinder);
    }

    @Override
    protected String getModulePathsVariableName() {
        return "npm_project_paths";
    }

    @Override
    protected String getFilePattern() {
        return "package.json";
    }
}
