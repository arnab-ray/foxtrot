package com.flipkart.foxtrot.core.querystore;

import com.flipkart.foxtrot.core.internalevents.InternalEventBus;
import com.flipkart.foxtrot.core.internalevents.events.QueryProcessed;
import com.flipkart.foxtrot.core.internalevents.events.QueryProcessingError;

/**
 *
 */
public class EventPublisherActionExecutionObserver implements ActionExecutionObserver {

    private final InternalEventBus eventBus;

    public EventPublisherActionExecutionObserver(InternalEventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void handleExecution(ActionEvaluationResponse response) {
        if(null != response.getException()) {
            eventBus.publish(new QueryProcessingError(response.getRequest(), response.getException()));
        }
        eventBus.publish(new QueryProcessed(response.getRequest(), response.getResponse(), response.getElapsedTime()));
    }
}