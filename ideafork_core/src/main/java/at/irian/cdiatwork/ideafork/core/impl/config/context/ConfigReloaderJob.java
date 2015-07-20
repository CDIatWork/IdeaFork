package at.irian.cdiatwork.ideafork.core.impl.config.context;

import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

//won't work in glassfish 3 due to GLASSFISH-19668
@ApplicationScoped
@Scheduled(cronExpression = "0 0/10 * * * ?")
public class ConfigReloaderJob implements Job {
    @Inject
    private ConfigReloader configReloader;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        configReloader.reloadConfig();
    }
}
