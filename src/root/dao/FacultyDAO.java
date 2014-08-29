package root.dao;

import java.sql.Connection;

/**
 * Created by Alex on 22.08.14.
 */
public class FacultyDAO {
    private FormDao formDao;
    private GroupDao groupDao;
    private MajorDao majorDao;
    private JDBCPersonDao personDao;
    private StudentDao studentDao;
    private SubjectDao subjectDao;
    private TeacherDao teacherDao;

    public FacultyDAO(Connection connection) {
        this.formDao = new FormDao(connection);
        this.groupDao = new GroupDao(connection);
        this.majorDao = new MajorDao(connection);
        this.personDao = new JDBCPersonDao(connection);
        this.studentDao = new StudentDao(connection);
        this.teacherDao = new TeacherDao(connection);
    }

    public FormDao getFormDao() {
        return formDao;
    }

    public void setFormDao(FormDao formDao) {
        this.formDao = formDao;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public MajorDao getMajorDao() {
        return majorDao;
    }

    public void setMajorDao(MajorDao majorDao) {
        this.majorDao = majorDao;
    }

    public JDBCPersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(JDBCPersonDao personDao) {
        this.personDao = personDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public SubjectDao getSubjectDao() {
        return subjectDao;
    }

    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
}
