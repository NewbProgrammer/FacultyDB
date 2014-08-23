package root.dao;

import root.entities.Form;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 */
public class FormDao {
    private Connection connection;

    public FormDao(Connection connection) {
        this.connection = connection;
    }

    private Form formForm(ResultSet resultSet) throws SQLException {
        return new Form(resultSet.getLong(1), resultSet.getString(2));
    }

    public List<Form> getAll() {
        List<Form> result = new ArrayList<Form>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM forms");
            while (resultSet.next()) {
                result.add(formForm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Form s) {
        String sqlStatement = "INSERT INTO forms VALUES (NULL, ?)";
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

    public boolean update(Form g) {
        String sqlStatement = "UPDATE forms SET name=? WHERE id=?";
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
        String sqlStatement = "DELETE from forms where id=?";
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

    public Form getById(long id) {
        Form result = new Form();
        String sqlStatement = "SELECT * FROM forms WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formForm(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Form> getByName(String name) {
        List<Form> result = new ArrayList<Form>();
        String sqlStatement = "SELECT * FROM forms WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formForm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
