package root.dao;

import root.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class StudentDao {
    private Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    private Student formStudent(ResultSet resultSet) throws SQLException {
        return new Student(resultSet.getLong(1), resultSet.getLong(2), resultSet.getLong(3),
                resultSet.getDate(4), resultSet.getDate(5));
    }

    public List<Student> getAll() {
        List<Student> result = new ArrayList<Student>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                result.add(formStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Student s) {
        String sqlStatement = "INSERT INTO students VALUES (NULL, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, s.getGroupID());
            preparedStatement.setLong(2, s.getPersonID());
            preparedStatement.setDate(3, new Date(s.getStart().getTime()) );
            preparedStatement.setDate(4, new Date(s.getFinish().getTime()) );

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            s.setId(resultSet.getLong(1));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Student s) {
        String sqlStatement = "UPDATE students SET group_id=?, person_id=?, start=?, finish=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, s.getGroupID());
            preparedStatement.setLong(2, s.getPersonID());
            preparedStatement.setDate(3, new Date(s.getStart().getTime()));
            preparedStatement.setDate(4, new Date(s.getFinish().getTime()) );
            preparedStatement.setLong(5, s.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sqlStatement = "DELETE from students where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     public Student getById(long id) {
        Student result = new Student();
        String sqlStatement = "SELECT * FROM students WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Student> getByGroupId(long id) {
        List<Student> result = new ArrayList<Student>();
        String sqlStatement = "SELECT * FROM students WHERE group_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Student> getByPersonId(long id) {
        List<Student> result = new ArrayList<Student>();
        String sqlStatement = "SELECT * FROM students WHERE person_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //not checked
    public List<Student> getByStart(Date start) {
        List<Student> result = new ArrayList<Student>();
        String sqlStatement = "SELECT * FROM persons WHERE start=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setDate(1, start);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //not checked
    public List<Student> getByFinish(Date finish) {
        List<Student> result = new ArrayList<Student>();
        String sqlStatement = "SELECT * FROM persons WHERE finish=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setDate(1, finish);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
