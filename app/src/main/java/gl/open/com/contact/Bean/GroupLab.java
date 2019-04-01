package gl.open.com.contact.Bean;

import java.util.ArrayList;

/**
 * Created by Zhu TingYu on 2019/4/1.
 */

public class GroupLab {
    private static GroupLab contactLab;

    public ArrayList<Group> getContactArrayList() {
        return mGroupArrayList;
    }


    private ArrayList<Group> mGroupArrayList = new ArrayList<>();

    public GroupLab(ArrayList<Group> contactArrayList) {
        mGroupArrayList = contactArrayList;
    }
    public void addGroup(String GroupName , int Id)
    {
        Group group = new Group(GroupName,Id);
        mGroupArrayList.add(group);
    }

    private GroupLab() {
    }

    public static GroupLab getGroupLab()
    {
        if(contactLab==null)
        {
            contactLab = new GroupLab();
        }
        return contactLab;
    }



}
