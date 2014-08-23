package root;

import root.dao.*;
import root.entities.*;
import root.services.PersonService;
import root.tools.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class Main {


    public static void main(String[] args) {
        try (Connection connection = new ConnectionManager().getConnection()
            ) {
            Scanner scan = new Scanner(System.in);
            PersonDao personDao = new PersonDao(connection);
            StudentDao studentDao = new StudentDao(connection);
            TeacherDao teacherDao = new TeacherDao(connection);
            GroupDao groupDao = new GroupDao(connection);
            SubjectDao subjectDao = new SubjectDao(connection);
            MajorDao majorDao = new MajorDao(connection);
            FormDao formDao = new FormDao(connection);

            FacultyDAO facultyDAO = new FacultyDAO(connection);
            PersonService personService = new PersonService(facultyDAO);

            System.out.println(personService.findByPassport("GG100023"));
            //List list = formDao.getAll();
           // System.out.println(list);

            //formDao.delete(3);
            //System.out.println(formDao.getByName("Stacionar"));


            // Person p = new Person(30, "Vaso", new Date(), "PS101010");

            //System.out.println(subjectDao.getByName("LAN"));
            /*System.out.println( ((Subject)list.get(list.size()-1)).getId());
            long id = scan.nextLong();
            subjectDao.delete(id);*/
            /*List groups = groupDao.getAll();
            Group g = (Group)groups.get(groups.size()-1);
            g.setName("Griffindor");
            groupDao.update(g);
            System.out.println(groupDao.getAll());*/

            //System.out.println("ID=" + g.getId());
            //System.out.println(teacherDao.getByPersonId(15));
            /*Teacher t = new Teacher(new Date(), new Date());
            t.setPersonID(25);
            teacherDao.create(t);
            System.out.println(teacherDao.getAll());*/
            /*Group g = new Group("Alpha");
            groupDao.create(g);
            System.out.println(groupDao.getAll());
            System.out.println("new id - " + g.getId());*/

            /*test of getAll methods - everything works properly*/
            //System.out.println(groupDao.getAll());
            //System.out.println(teacherDao.getAll());

            /*List groupList = groupDao.getAll();
            System.out.println(groupList);
            Group g = (Group)groupList.get(groupList.size()-1);
            g.setName("PSY");
            groupDao.update(g);
            System.out.println(groupDao.getAll());*/
            //System.out.println("new id - " + t.getId());

            /*List persList = personDao.getAll();
            System.out.println(persList);
            Person p2 = (Person)persList.get(persList.size()-1);
            p2.setName("VASOS");
            personDao.update(p2);*/
            //System.out.println(personDao.getAll());



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
