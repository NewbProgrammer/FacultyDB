package root.dao;

import root.entities.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class GroupDao {
    private Connection connection;

    public GroupDao(Connection connection) {
        this.connection = connection;
    }

    private Group formGroup(ResultSet resultSet) throws SQLException {
        return new Group(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
    }

    public List<Group> getAll() {
        List<Group> result = new ArrayList<Group>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups");
            while (resultSet.next()) {
                result.add(formGroup(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Group g) {
        String sqlStatement = "INSERT INTO groups VALUES (NULL, ?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, g.getName());
            preparedStatement.setLong(2, g.getFormID());
            preparedStatement.setLong(3, g.getMajorID());


            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            g.setId(resultSet.getLong(1));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Group g) {
        String sqlStatement = "UPDATE groups SET name=?, form_id=?, major_id=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, g.getName());
            preparedStatement.setLong(2, g.getFormID());
            preparedStatement.setLong(3, g.getMajorID());
            preparedStatement.setLong(4, g.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sqlStatement = "DELETE from groups where id=?";
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

    public Group getById(long id) {
        Group result = new Group();
        String sqlStatement = "SELECT * FROM groups WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formGroup(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Group> getByName(String name) {
        List<Group> result = new ArrayList<Group>();
        String sqlStatement = "SELECT * FROM groups WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formGroup(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Group> getByFormId(long id) {
        List<Group> result = new ArrayList<Group>();
        String sqlStatement = "SELECT * FROM groups WHERE form_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formGroup(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Group> getByMajorId(long id) {
        List<Group> result = new ArrayList<Group>();
        String sqlStatement = "SELECT * FROM groups WHERE major_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formGroup(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
