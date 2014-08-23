package root.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class Person {
    private long id;
    private String name;
    private Date birthday;
    private String passport;
    private List<Student> studentList = new ArrayList<Student>();
    private List<Teacher> teacherList = new ArrayList<Teacher>();

    public Person() {}

    public Person(String name, Date birthday, String passport) {
        this.name = name;
        this.birthday = birthday;
        this.passport = passport;
    }

    public Person(long id, String name, Date birthday, String passport) {
        this(name, birthday, passport);
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        for (Student s : studentList) {
            s.setPerson(this);
        }
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
        for (Teacher t : teacherList) {
            t.setPerson(this);
        }
    }

    public void addStudent(Student s) {
        s.setPerson(this);
        studentList.add(s);
    }

    public void addTeacher(Teacher t) {
        t.setPerson(this);
        teacherList.add(t);
    }

    public void removeStudent(Student s) {
        studentList.remove(s);
        s.removePerson();
    }

    public void removeTeacher(Teacher t) {
        teacherList.remove(t);
        t.removePerson();
    }

    @Override
    public String toString() {
        return "Person\n[" + "name=" + name + ", birthday=" + birthday + ", passport=" + passport + "\nstudents:" +
                studentList +  "\nteachers:" + teacherList +"]\n";
    }
}
