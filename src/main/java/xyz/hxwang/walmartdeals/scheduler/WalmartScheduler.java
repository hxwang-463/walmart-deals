package xyz.hxwang.walmartdeals.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.hxwang.walmartdeals.service.WalmartCatalogsService;
import xyz.hxwang.walmartdeals.service.WalmartProductsService;

@Component
public class WalmartScheduler {

    private static final Logger logger = LogManager.getLogger(WalmartScheduler.class);

    @Autowired
    WalmartCatalogsService walmartCatalogsService;

    @Autowired
    WalmartProductsService walmartProductsService;

    @Scheduled(cron = "0 48 20 * * ?") // Executes daily at 1:00 AM
    public void task1() throws JsonMappingException, JsonProcessingException {
        // Your task logic here
        logger.info("Updating catalogs");
        this.walmartCatalogsService.deleteCatalogs();
        this.walmartCatalogsService.fetchDataAndSave();
        logger.info("Catalogs updated");
        logger.info("Updating products");
        this.walmartProductsService.deleteProducts();
        this.walmartProductsService.fetchDataAndSave();
        logger.info("products updated");
        logger.info("updation complete");
    }
}
