package root.entities;

import java.util.List;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class Group {
    private long id;
    private String name;
    private Form form;
    private long formID;
    private Major major;
    private long majorID;
    private List<Student> studentList;

    public Group() {}

    public Group(String name) {
        this.name = name;
    }

    public Group(long id, String name) {
        this(name);
        this.id = id;
    }

    public Group(long id, String name, long formID, long majorID) {
        this(id, name);
        this.formID = formID;
        this.majorID = majorID;
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student s) {
        s.setGroup(this);
        studentList.add(s);
    }

    public  void removeStudent(Student s) {
        studentList.remove(s);
        s.removeGroup();
    }

    @Override
    public String toString() {
        return "Group[" + "name=" + name + ", fomID="+ formID + ", majorID=" + majorID + ", students:" + studentList + "]\n";
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void removeForm() {
        this.form = null;
    }

    public long getFormID() {
        return formID;
    }

    public void setFormID(long formID) {
        this.formID = formID;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void removeMajor() {
        this.major = null;
    }

    public long getMajorID() {
        return majorID;
    }

    public void setMajorID(long majorID) {
        this.majorID = majorID;
    }
}
