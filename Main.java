import java.util.ArrayList;
import java.util.Scanner;

class MobilePhone{
    private String myNumber;
    private ArrayList<Contacts> myContacts;
    public MobilePhone(String number){
        this.myNumber = number;
        this.myContacts = new ArrayList<Contacts>();
    }
    public boolean addNewContact(Contacts contact){
        if(findContact(contact.getName())>=0){
            System.out.println("Contact Already Present");
            return false;
        }
        myContacts.add(contact);
        return true;
    }
    public boolean updateContact(Contacts oldContact, Contacts newContact){
        int foundPosition = findContact(oldContact);
        if(foundPosition < 0){
            System.out.println(oldContact.getName() + ", was not found");
            return false;
        } else if(findContact(newContact.getName()) != -1){
            System.out.println("Contact with same name already exists...");
            return false;
        }

        this.myContacts.set(foundPosition, newContact);
        System.out.println(oldContact.getName()+", was replaced with " + newContact.getName());
        return true;
    }

    public String queryContact(Contacts contact){
        if(findContact(contact) >= 0){
            return contact.getName();
        }
        return null;
    }
    public boolean removeContact(Contacts contact){
        int foundPosition = findContact(contact);
        if(foundPosition < 0){
            System.out.println(contact.getName()+", was not found");
            return false;
        }
        this.myContacts.remove(foundPosition);
        System.out.println(contact.getName()+", was deleted");
        return true;
    }
    public void printContacts(){
        System.out.println("Contact List");
        for(int i=0;i<myContacts.size();i++){
            System.out.println((i+1)+". "+
                    this.myContacts.get(i).getName()+" -> "+
                    this.myContacts.get(i).getPhoneNo());
        }
    }
    public Contacts queryContact(String name){
        int position = findContact(name);
        if(position>=0){
            return this.myContacts.get(position);
        }
        return null;
    }
    private int findContact(Contacts contact){
        return this.myContacts.indexOf(contact);
    }
    private int findContact(String contactNM){
        for(int i=0;i<this.myContacts.size();i++){
            Contacts contact = this.myContacts.get(i);
            if(contact.getName().equals(contactNM)){
                return i;
            }
        }
        return -1;
    }
}
class Contacts{
    private String name;
    private String phoneNo;
    public Contacts(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public static Contacts createContact(String name, String number){
        return new Contacts(name, number);
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("+91 7023455665");
    public static void main(String[] args){
        boolean quit = false;
        startPhone();
        printActions();
        while(!quit){
            int action = scanner.nextInt();
            switch (action){
                case 1:
                    System.out.println("Shutting down phone...");
                    quit = true;
                    break;
                case 2:
                    printContacts();
                    break;
                case 3:
                    addNewContact();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    removeContact();
                    break;
                case 6:
                    queryContact();
                    break;
                case 7:
                    printActions();
                    break;
            }
            if(action!=7&&action!=1)
                System.out.println("Enter action: (6 to print all actions)");
        }
    }
    private static void addNewContact(){
        scanner.nextLine();
        System.out.println("Enter new Contact Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number:");
        String  phoneNo = scanner.nextLine();
        Contacts contact = Contacts.createContact(name, phoneNo);
        if(mobilePhone.addNewContact(contact)){
            System.out.println("New contact Created");
        } else {
            System.out.println("Cannot add the contact number....");
        }
    }
    private static void queryContact(){
        scanner.nextLine();
        System.out.println("Enter existing Contact name: ");
        String oldName = scanner.nextLine();
        Contacts existingContact = mobilePhone.queryContact(oldName);
        if(existingContact == null){
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Name: " + existingContact.getName() +
                        ", phone number: " + existingContact.getPhoneNo());
    }
    private static void removeContact(){
        scanner.nextLine();
        System.out.println("Enter existing Contact name: ");
        String oldName = scanner.nextLine();
        Contacts existingContact = mobilePhone.queryContact(oldName);
        if(existingContact == null){
            System.out.println("Contact not found");
            return;
        }
        if(mobilePhone.removeContact(existingContact))
            System.out.println("Successfully removed the contact!!");
        else
            System.out.println("Error occurred while removing....");
    }
    private static void updateContact(){
        scanner.nextLine();
        System.out.println("Enter existing Contact name: ");
        String oldName = scanner.nextLine();
        Contacts existingContact = mobilePhone.queryContact(oldName);
        if(existingContact == null){
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Enter new Contact name:");
        String newContactName = scanner.nextLine();
        System.out.println("Enter new phone number: ");
        String number = scanner.nextLine();
        Contacts newContact = Contacts.createContact(newContactName, number);
        if(mobilePhone.updateContact(existingContact,newContact))
            System.out.println("Success!!!");
        else
            System.out.println("Cannot update... ERROR :X");
    }
    private static void printContacts(){
        mobilePhone.printContacts();
    }
    private static void startPhone(){
        System.out.println("Phone is starting....");
    }
    private static void printActions(){
        System.out.println("\n1. To shut down"+
                "\n2. To print Contacts"+
                "\n3. To add new Contact"+
                "\n4. To update existing Contact"+
                "\n5. To remove an existing contact"+
                "\n6. Query if an existing contact exists"+
                "\n7. To print a list of available actions");
        System.out.println("Choose you action: ");
    }
}
