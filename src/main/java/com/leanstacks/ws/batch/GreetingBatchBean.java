package com.leanstacks.ws.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leanstacks.ws.model.Greeting;
import com.leanstacks.ws.service.GreetingService;

/**
 * The GreetingBatchBean contains <code>@Scheduled</code> methods operating on
 * Greeting entities to perform batch operations.
 * 
 * @author Matt Warman
 */
@Component
@Profile("batch")
public class GreetingBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The <code>CounterService</code> captures metrics for Spring Actuator.
     */
    @Autowired
    private CounterService counterService;

    /**
     * The GreetingService business service.
     */
    @Autowired
    private GreetingService greetingService;

    /**
     * Use a cron expression to execute logic on a schedule. Expression: second
     * minute hour day-of-month month weekday
     * 
     * @see http 
     *      ://docs.spring.io/spring/docs/current/javadoc-api/org/springframework
     *      /scheduling/support/CronSequenceGenerator.html
     */
    @Scheduled(
            cron = "${batch.greeting.cron}")
    public void cronJob() {
        logger.info("> cronJob");

        counterService.increment("method.invoked.greetingBatchBean.cronJob");

        // Add scheduled logic here

        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< cronJob");
    }

    /**
     * Execute logic beginning at fixed intervals. Use the
     * <code>fixedRate</code> element to indicate how frequently the method is
     * to be invoked.
     */
    @Scheduled(
            fixedRateString = "${batch.greeting.fixedrate}")
    public void fixedRateJob() {
        logger.info("> fixedRateJob");

        counterService
                .increment("method.invoked.greetingBatchBean.fixedRateJob");

        // Add scheduled logic here

        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< fixedRateJob");
    }

    /**
     * Execute logic beginning at fixed intervals with a delay after the
     * application starts. Use the <code>fixedRate</code> element to indicate
     * how frequently the method is to be invoked. Use the
     * <code>initialDelay</code> element to indicate how long to wait after
     * application startup to schedule the first execution.
     */
    @Scheduled(
            initialDelayString = "${batch.greeting.initialdelay}",
            fixedRateString = "${batch.greeting.fixedrate}")
    public void fixedRateJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");

        counterService
                .increment("method.invoked.greetingBatchBean.fixedRateJobWithInitialDelay");

        // Add scheduled logic here

        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< fixedRateJobWithInitialDelay");
    }

    /**
     * Execute logic with a delay between the end of the last execution and the
     * beginning of the next. Use the <code>fixedDelay</code> element to
     * indicate the time to wait between executions.
     */
    @Scheduled(
            fixedDelayString = "${batch.greeting.fixeddelay}")
    public void fixedDelayJob() {
        logger.info("> fixedDelayJob");

        counterService
                .increment("method.invoked.greetingBatchBean.fixedDelayJob");

        // Add scheduled logic here

        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< fixedDelayJob");
    }

    /**
     * Execute logic with a delay between the end of the last execution and the
     * beginning of the next. Use the <code>fixedDelay</code> element to
     * indicate the time to wait between executions. Use the
     * <code>initialDelay</code> element to indicate how long to wait after
     * application startup to schedule the first execution.
     */
    @Scheduled(
            initialDelayString = "${batch.greeting.initialdelay}",
            fixedDelayString = "${batch.greeting.fixeddelay}")
    public void fixedDelayJobWithInitialDelay() {
        logger.info("> fixedDelayJobWithInitialDelay");

        counterService
                .increment("method.invoked.greetingBatchBean.fixedDelayJobWithInitialDelay");

        // Add scheduled logic here

        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< fixedDelayJobWithInitialDelay");
    }

}
