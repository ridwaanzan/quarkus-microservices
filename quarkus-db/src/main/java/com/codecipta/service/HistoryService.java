package com.codecipta.service;

import com.codecipta.entity.ProcessHistory;
import com.codecipta.repository.ProcessHistoryRepository;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

@ApplicationScoped
public class HistoryService {
    @Inject
    ProcessHistoryRepository processHistoryRepository;

    static final Logger log = Logger.getLogger(UserService.class);

    @ConsumeEvent("history")
    @Blocking
    @Transactional
    public void processHistory(JsonObject data) {
        log.info("Start processHistory service");
        try {
            log.info("saving history: " + data);
            ProcessHistory pHistory = new ProcessHistory(
                    data.getString("pId"),
                    data.getString("pName")
            );
            processHistoryRepository.persist(pHistory);
        } catch (Exception e) {
            log.error("Error processHistory service: " + e.getMessage());
        }
    }

}
