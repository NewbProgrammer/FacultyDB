package root.entities;

import java.util.List;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class Major {
    long id;
    String name;
    List<Subject> subjectList;
    List<Group> groupList;

    public Major() {}

    public Major(String name) {
        this.name = name;
    }

    public Major(long id, String name) {
        this.name = name;
        this.id = id;
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

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public void addSubject(Subject s) {
        s.setMajor(this);
        subjectList.add(s);
    }

    public void removeSubject(Subject s) {
        s.removeMajor();
        subjectList.remove(s);
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public void addGroup(Group g) {
        g.setMajor(this);
        groupList.add(g);
    }

    public void removeGroup(Group g) {
        groupList.remove(g);
        g.removeMajor();
    }

    @Override
    public String toString() {
        return "Major[" + "id=" + id + ", name=" + name + "]\n";
    }
}
