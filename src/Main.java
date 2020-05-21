import org.quartz.*;

/**
 * @author Sebastian Kazun
 */


public class Main {

    public static void main(String[] args)
    {

        DataFileParser fileParser = new DataFileParser("InterviewDataset.xlsx");

        AddressBook book = new AddressBook(fileParser.parseExcelFile());


        SMSMessage message = new SMSMessage();

        //create job and trigger
        JobDetail job = JobBuilder.newJob(SMSMessage.class).withIdentity("messageJob", "birthdayMessage").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("BeginningOfTheMonth", "birthdayMessage")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0 1 1 1/1 ? *")) //1st of the month at 1am
                        //CronScheduleBuilder.cronSchedule("0/10 * * * * ?")) //every 10 seconds
                .build();

        SMSScheduler scheduler = new SMSScheduler(trigger, job);

        scheduler.schedule(message, book.getContactsByBirthdayMonth());

    }
}
