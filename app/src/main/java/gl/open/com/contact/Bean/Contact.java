package gl.open.com.contact.Bean;

/**
 * Created by Zhu TingYu on 2019/4/1.
 */

public class Contact {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumbel() {
        return telNumbel;
    }

    public void setTelNumbel(String telNumbel) {
        this.telNumbel = telNumbel;
    }

    private String name;
    private String telNumbel;

    public Contact(String name, String telNumbel, int id) {
        this.name = name;
        this.telNumbel = telNumbel;
        Id = id;
    }
    public Contact(){

    }

    private int Id;
    private Boolean isSelected;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
