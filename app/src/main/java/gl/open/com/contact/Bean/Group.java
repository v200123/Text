package gl.open.com.contact.Bean;

/**
 * Created by Zhu TingYu on 2019/4/1.
 */

public class Group {
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    private String groupName;

    public Group(String groupName, int groupNum) {
        this.groupName = groupName;
        this.groupNum = groupNum;
    }

    private int groupNum;

}
