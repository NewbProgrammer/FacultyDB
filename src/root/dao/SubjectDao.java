package root.dao;

import root.entities.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class SubjectDao {

    private Connection connection;

    public SubjectDao(Connection connection) {
        this.connection = connection;
    }

    private Subject formSubject(ResultSet resultSet) throws SQLException {
        return new Subject(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3));
    }

    public List<Subject> getAll() {
        List<Subject> result = new ArrayList<Subject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM subjects");
            while (resultSet.next()) {
                result.add(formSubject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Subject s) {
        String sqlStatement = "INSERT INTO subjects VALUES (NULL, ?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, s.getName());
            preparedStatement.setLong(2, s.getMajorID());


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

    public boolean update(Subject g) {
        String sqlStatement = "UPDATE subjects SET name=?, major_id=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, g.getName());
            preparedStatement.setLong(2, g.getMajorID());
            preparedStatement.setLong(3, g.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sqlStatement = "DELETE from subjects where id=?";
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

    public Subject getById(long id) {
        Subject result = new Subject();
        String sqlStatement = "SELECT * FROM subjects WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formSubject(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Subject> getByName(String name) {
        List<Subject> result = new ArrayList<Subject>();
        String sqlStatement = "SELECT * FROM subjects WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formSubject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Subject> getByMajorId(long id) {
        List<Subject> result = new ArrayList<Subject>();
        String sqlStatement = "SELECT * FROM subjects WHERE major_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formSubject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
