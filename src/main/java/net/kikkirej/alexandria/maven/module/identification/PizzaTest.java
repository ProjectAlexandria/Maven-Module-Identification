package net.kikkirej.alexandria.maven.module.identification;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ExternalTaskSubscription("pizza")
public class PizzaTest implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        LoggerFactory.getLogger(getClass()).error(externalTask.getBusinessKey(), externalTask.getProcessDefinitionKey());
    }
}
