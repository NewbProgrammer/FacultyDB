package root.dao;

import root.entities.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class TeacherDao {
    private Connection connection;

    public TeacherDao(Connection connection) {
        this.connection = connection;
    }

    private Teacher formTeacher(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getLong(1), resultSet.getLong(2),
                resultSet.getDate(3), resultSet.getDate(4));
    }

    public List<Teacher> getAll() {
        List<Teacher> result = new ArrayList<Teacher>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers");
            while (resultSet.next()) {
                result.add(formTeacher(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Teacher t) {
        String sqlStatement = "INSERT INTO teachers VALUES (NULL, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, t.getPersonID());
            preparedStatement.setDate(2, new Date(t.getStart().getTime()) );
            preparedStatement.setDate(3, new Date(t.getFinish().getTime()) );

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            t.setId(resultSet.getLong(1));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Teacher t) {
        String sqlStatement = "UPDATE teachers SET person_id=?, start=?, finish=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, t.getPersonID());
            preparedStatement.setDate(2, new Date(t.getStart().getTime()) );
            preparedStatement.setDate(3, new Date(t.getFinish().getTime()) );
            preparedStatement.setLong(4, t.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sqlStatement = "DELETE from teachers where id=?";
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

    public Teacher getById(long id) {
        Teacher result = new Teacher();
        String sqlStatement = "SELECT * FROM teachers WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formTeacher(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Teacher> getByPersonId(long id) {
        List<Teacher> result = new ArrayList<Teacher>();
        String sqlStatement = "SELECT * FROM teachers WHERE person_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formTeacher(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //not checked
    public List<Teacher> getByStart(Date start) {
        List<Teacher> result = new ArrayList<Teacher>();
        String sqlStatement = "SELECT * FROM persons WHERE start=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setDate(1, start);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formTeacher(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //not checked
    public List<Teacher> getByFinish(Date finish) {
        List<Teacher> result = new ArrayList<Teacher>();
        String sqlStatement = "SELECT * FROM persons WHERE finish=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setDate(1, finish);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formTeacher(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
