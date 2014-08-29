package root.dao;

import root.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.08.14.
 *
 */
public class JDBCPersonDao {
    private Connection connection;

    public JDBCPersonDao(Connection connection) {
        this.connection = connection;
    }

    private Person formPerson(ResultSet resultSet) throws SQLException {
        return new Person(resultSet.getLong(1),resultSet.getString(2),
                resultSet.getDate(3), resultSet.getString(4));
    }

    public List<Person> getAll() {
        List<Person> result = new ArrayList<Person>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM persons");
            while (resultSet.next()) {
                result.add(formPerson(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean create(Person p) {
        String sqlStatement = "INSERT INTO persons VALUES (NULL, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sqlStatement, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, p.getName());
            preparedStatement.setDate(2, new Date(p.getBirthday().getTime()) );
            preparedStatement.setString(3, p.getPassport());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            p.setId(resultSet.getLong(1));

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Person p) {
        String sqlStatement = "UPDATE persons SET name=?, birthday=?, passport=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, p.getName());
            preparedStatement.setDate(2, new Date(p.getBirthday().getTime()) );
            preparedStatement.setString(3, p.getPassport());
            preparedStatement.setLong(4, p.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sqlStatement = "DELETE from persons where id=?";
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

    public Person getById(long id) {
        Person result = new Person();
        String sqlStatement = "SELECT * FROM persons WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = formPerson(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Person> getByName(String name) {
        List<Person> result = new ArrayList<Person>();
        String sqlStatement = "SELECT * FROM persons WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formPerson(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Person> getByPassport(String passport) {
        List<Person> result = new ArrayList<Person>();
        String sqlStatement = "SELECT * FROM persons WHERE passport=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, passport);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formPerson(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //not checked
    public List<Person> getByBirthday(Date birthday) {
        List<Person> result = new ArrayList<Person>();
        String sqlStatement = "SELECT * FROM persons WHERE birthday=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setDate(1, birthday);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(formPerson(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
