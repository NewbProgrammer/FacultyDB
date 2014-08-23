package root.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class Teacher {
    private long id;
    private Date start;
    private Date finish;
    private Person person;
    private long personID;
    private List<Subject> subjectList;

    public Teacher() {}

    public Teacher(Date start, Date finish) {
        this.start = start;
        this.finish = finish;
    }

    public Teacher(long id, long personID, Date start, Date finish) {
        this(start, finish);
        this.id = id;
        this.personID = personID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void removePerson() {
        person = null;
    }

    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    @Override
    public String toString() {
        return "Teacher[" + "start=" + start + ", finish=" + finish + ", personID=" + personID + "]\n";
    }

    public void addSubject(Subject s) {
        s.addTeacher(this);
        subjectList.add(s);
    }

    public void removeSubject(Subject s) {
        subjectList.remove(s);
        s.removeTeacher(this);
    }
}
