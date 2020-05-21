/**
 * @author Sebastian Kazun
 */
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Set;

/**
 * Schedules the SMSMessages to be sent out and when they are to be sent out
 */
public class SMSScheduler {

    Trigger trigger;
    JobDetail job;

    public SMSScheduler(Trigger trigger, JobDetail job)
    {
        this.trigger = trigger;
        this.job = job;
    }


    public void schedule(SMSMessage message, Set<PersonInfo> birthdays) {

        try {
            //putting it all together
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();

            scheduler.getContext().put("smsJob", message);
            scheduler.getContext().put("birthdays", birthdays);

            scheduler.start();
            scheduler.scheduleJob(job, trigger);
            System.out.println("Started scheduler service");

        } catch (SchedulerException se) {
            System.out.println("Unable to start scheduler service");
        }
    }

}