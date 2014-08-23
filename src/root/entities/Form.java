package root.entities;

import java.util.List;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class Form {
    private long id;
    private String name;
    List<Group> groupList;

    public Form() {}

    public Form(String name) {
        this.name = name;
    }

    public Form(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public void addGroup(Group g) {
        g.setForm(this);
        groupList.add(g);
    }

    public void removeGroup(Group g) {
        groupList.remove(g);
        g.removeForm();
    }

    @Override
    public String toString() {
        return "Form[" + "id=" + id + ", name=" + name + ", groups:" + groupList + "]\n";
    }
}
