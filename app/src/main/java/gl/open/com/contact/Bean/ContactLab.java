package gl.open.com.contact.Bean;

import java.util.ArrayList;

/**
 * Created by Zhu TingYu on 2019/4/1.
 */

public class ContactLab {
    private static ContactLab contactLab;

    public ArrayList<Contact> getContactArrayList() {
        return mContactArrayList;
    }


    private ArrayList<Contact> mContactArrayList = new ArrayList<>();

    public void addContact(String name,String telNum,int id)
    {
        Contact contact = new Contact(name,telNum,id);
        mContactArrayList.add(contact);
    }

    public ContactLab(ArrayList<Contact> contactArrayList) {
        mContactArrayList = contactArrayList;
    }

    private ContactLab() {
    }

    public static ContactLab getContactLab()
    {
        if(contactLab==null)
        {
            contactLab = new ContactLab();
        }
        return contactLab;
    }



}
