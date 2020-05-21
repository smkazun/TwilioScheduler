/**
 * @author Sebastian Kazun
 */

import java.util.*;

/**
 * Maintains a collection of PersonInfo objects that represent the contact list
 */
public class AddressBook {

    private Set<PersonInfo> persons;

    public AddressBook(Set<PersonInfo> persons)
    {
        this.persons = persons;
    }

    public Set<PersonInfo> getAddressBook()
   {
       return persons;
   }

   public void setAddressBook(Set<PersonInfo> persons)
   {
       this.persons = persons;
   }


   public Set<PersonInfo> getContactsByBirthdayMonth()
   {
       Set<PersonInfo> dobPersons = new HashSet<PersonInfo>();
       Calendar current = Calendar.getInstance();
       int birthdayMonth = current.get(Calendar.MONTH); //get current month

       for(PersonInfo person : persons)
       {
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(person.getDOB()); //set birthday

           if(calendar.get(Calendar.MONTH) == birthdayMonth)
           {
               dobPersons.add(person);
           }
       }

       return dobPersons;
   }

}
