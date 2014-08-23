package root.entities;

import java.util.List;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class Subject {
    private long id;
    private String name;
    private Major major;
    private long majorID;
    private List<Teacher> teacherList;

    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }

    public Subject(long id, String name, long majorID) {
        this(name);
        this.id = id;
        this.majorID = majorID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMajorID() {
        return majorID;
    }

    public void setMajorID(long majorID) {
        this.majorID = majorID;
    }

    public void removeMajor() {
        this.major = null;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return "Subject[" + "name=" + name + ", majorID=" + majorID + "]\n";
    }

    public void addTeacher(Teacher t) {
        teacherList.add(t);
    }

    public void removeTeacher(Teacher t) {
        teacherList.remove(t);
    }

}
