package net.kikkirej.alexandria.module.identification;

import net.kikkirej.alexandria.module.identification.config.GeneralProperties;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("maven-module-identification")
public class MavenModuleIdentification extends ModuleIdentification {

    @Autowired
    public MavenModuleIdentification(GeneralProperties generalProperties, DefiningObjectFinder definingObjectFinder) {
        super(generalProperties, definingObjectFinder);
    }

    protected String getModulePathsVariableName() {
        return "maven_module_paths";
    }

    @Override
    protected String getFilePattern() {
        return "pom.xml";
    }


}
