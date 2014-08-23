package root.services;

import root.dao.FacultyDAO;
import root.entities.Person;
import root.entities.Student;
import root.entities.Teacher;

import java.util.List;

/**
 * Created by Alex on 22.08.14.
 */
public class PersonService {

    private FacultyDAO facultyDAO;

    private List<Person> persons;
    private List<Student> students;
    private List<Teacher> teachers;

    public PersonService(FacultyDAO facultyDAO) {
        this.facultyDAO = facultyDAO;
    }

    //checked
    private void createLinks() {
        for (Person p : persons) {
            for (Student s : students) {
                if (s.getPersonID() == p.getId()) {
                    p.addStudent(s);
                }
            }

            for (Teacher t : teachers) {
                if (t.getPersonID() == p.getId()) {
                    p.addTeacher(t);
                }
            }
        }
    }

    //checked
    public List<Person> findAll() {
        persons = facultyDAO.getPersonDao().getAll();
        students = facultyDAO.getStudentDao().getAll();
        teachers = facultyDAO.getTeacherDao().getAll();

        createLinks();

        return persons;
    }

    //checked
    public Person findById(long id) {
        Person p = facultyDAO.getPersonDao().getById(id);
        students = facultyDAO.getStudentDao().getByPersonId(id);
        teachers = facultyDAO.getTeacherDao().getByPersonId(id);

        p.setStudentList(students);

        p.setTeacherList(teachers);

        return p;
    }

    //checked
    public List<Person> findByName(String name) {
        persons = facultyDAO.getPersonDao().getByName(name);

        for (Person p : persons) {
            students = facultyDAO.getStudentDao().getByPersonId(p.getId());
            p.setStudentList(students);

            teachers = facultyDAO.getTeacherDao().getByPersonId(p.getId());
            p.setTeacherList(teachers);
        }

        return persons;
    }

    //checked
    public List<Person> findByPassport(String passport) {
        persons = facultyDAO.getPersonDao().getByPassport(passport);

        for (Person p : persons) {
            students = facultyDAO.getStudentDao().getByPersonId(p.getId());
            p.setStudentList(students);

            teachers = facultyDAO.getTeacherDao().getByPersonId(p.getId());
            p.setTeacherList(teachers);
        }

        return persons;
    }

    public boolean create(Person p) {
        //validate person p

        return facultyDAO.getPersonDao().create(p);
    }

    public boolean delete(Person p) {
        return facultyDAO.getPersonDao().delete(p.getId());
    }

    public boolean update(Person p) {
        //validate person p
        return facultyDAO.getPersonDao().update(p);
    }

}
