package root.dao;

import root.entities.Major;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class MajorDao {
    private Connection connection;

    public MajorDao(Connection connection) {
        this.connection = connection;
    }

    private Major formMajor(ResultSet resultSet) throws SQLException {
        return new Major(resultSet.getLong(1), resultSet.getString(2));
    }

    public List<Major> getAll() {
        List<Major> result = new ArrayList<Major>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM majors");
            while (resultSet.next()) {
                result.add(formMajor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Major s) {
        String sqlStatement = "INSERT INTO majors VALUES (NULL, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, s.getName());
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

    public boolean update(Major g) {
        String sqlStatement = "UPDATE majors SET name=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, g.getName());
            preparedStatement.setLong(2, g.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sqlStatement = "DELETE from majors where id=?";
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

    public Major getById(long id) {
        Major result = new Major();
        String sqlStatement = "SELECT * FROM majors WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formMajor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Major> getByName(String name) {
        List<Major> result = new ArrayList<Major>();
        String sqlStatement = "SELECT * FROM majors WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formMajor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
