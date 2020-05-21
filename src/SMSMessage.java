/**
 * @author Sebastian Kazun
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.Set;
import org.quartz.*;


/**
 * SMSMessage class that sends a birthday message to a group of specified contacts
 */
public class SMSMessage implements Job {

	//To utilize this application, you must create a twilio account and add the SID and AUTH_TOKEN down below.
	//For security reasons, it is recommended that you do not directly put the SID and AUTH_TOKEN directly below,
	//but rather in an external file such as a configuration file.
    private static final String SID = "";
    private static final String AUTH_TOKEN = "";

    public SMSMessage()
    {
        Twilio.init(SID, AUTH_TOKEN);
    }

    private Message sendBirthdayMessage(PersonInfo person)
    {
        String birthdayMsg = "Happy Birthday! " + person.getFirstName() + ", I see that it's your birthday month. I hope you have an awesome month!";

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+1" + person.getMobilePhone()), //to
                new com.twilio.type.PhoneNumber("+1 {XXXXXXXXXX}"), //from  //ADD your phone number here, I have removed mine for security purposes
                birthdayMsg )
                .create();

        System.out.println(message.getBody());

        return message;
    }

    public void sendBirthdayMessageToContacts(Set<PersonInfo> persons)
    {
        for(PersonInfo person : persons)
        {
            if(person.getMobilePhone() != null)
            {
                sendBirthdayMessage(person);
            }
        }

    }


    public void execute(JobExecutionContext context) throws JobExecutionException {

        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = context.getScheduler().getContext();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SMSMessage message = (SMSMessage) schedulerContext.get("smsJob");
        Set<PersonInfo> persons = (Set<PersonInfo>) schedulerContext.get("birthdays");

        message.sendBirthdayMessageToContacts(persons);
    }
}
