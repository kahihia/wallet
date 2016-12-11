package wepesa.data;

import java.sql.*;

/**
 * Created by Harsh.Pokharna on 6/30/2015.
 */
public class DatabaseService {

    public static final String INSERT_USER = "INSERT INTO user (email, first_name, last_name, password) VALUES (?, ?, ?, ?);";


    public static void insertDreamerIntoTable(String email, String first_name, String last_name, String password) {

        Connection databaseConnection = null;
        PreparedStatement preparedStatement = null;
        DatabaseAdapter databaseAdapter = DatabaseAdapter.getInstance();

        try {
            databaseConnection = databaseAdapter.getConnection();

            preparedStatement = databaseConnection.prepareStatement(INSERT_USER);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            databaseConnection.close();

        } catch (SQLException e) {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (databaseConnection != null) {
                    databaseConnection.close();
                }
            } catch (Exception e1) {

            }

                throw new RuntimeException();

        }
    }

}