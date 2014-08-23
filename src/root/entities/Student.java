package root.entities;

import java.util.Date;

/**
 * Created by Alex on 17.08.14.
 */
public class Student {
    private long id;
    private Date start;
    private Date finish;
    private Person person;
    private Group group;
    private long groupID;
    private long personID;

    public Student() {}

    public Student(Date start, Date finish) {
        this.start = start;
        this.finish = finish;
    }

    public Student(long id, long groupID, long personID, Date start, Date finish) {
        this(start, finish);
        this.id = id;
        this.groupID = groupID;
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
        this.person = null;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void removeGroup() {
        this.group = null;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    @Override
        public String toString() {
        return "Student[" + "start=" + start + ", finish=" + finish + ", personID=" + personID + ", groupID="+ groupID + "]\n";
    }
}
