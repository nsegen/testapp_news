package com.revotech.nsegen.dao;

import com.revotech.nsegen.constants.UserQueries;
import com.revotech.nsegen.datasource.DataSource;
import com.revotech.nsegen.entities.User;
import com.revotech.nsegen.exceptions.DAOException;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Revotech on 12.07.16.
 */
public class UserDAO implements IUserDAO {

    private static final Logger log = Logger.getLogger(UserDAO.class);

    private static UserDAO userDAO;

    /**
     * constructor is private, because this class is a singleton
     */
    private UserDAO(){

        Locale.setDefault(Locale.ENGLISH);
    }

    /**
     * @return instance UserDAO
     */
    public static UserDAO getInstance(){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    /**
     * This method used when we need user and we know his id
     * @param id this is id news which we need
     * @return News which we need
     * @throws DAOException
     */
    @Override
    public User getUserById(Integer id) throws DAOException{

        User user = null;

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(UserQueries.SQL_GET_USER_BY_ID)){

            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();
            if(rs.next()){
                user = createUser(rs);
            }
        } catch(IOException | SQLException | PropertyVetoException e) {
            log.error("DAO GetNewsById failed " + e);
            throw new DAOException("DAO GetNewsById failed " + e);
        }
        return user;
    }

    /**
     * This method is used to find user according to his nickname
     * @param nickname this is user nickname
     * @return user id with given nickname
     * @throws DAOException
     */
    @Override
    public Integer getUserIdByNickname(String nickname) throws DAOException {
        Integer userID = null;

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(UserQueries.SQL_GET_USER_ID_BY_NICKNAME)) {

            prst.setString(1, nickname);

            ResultSet resultSet = prst.executeQuery();
            if(resultSet.next()) {
                userID = resultSet.getInt("ID");
            }
            resultSet.close();

        } catch (IOException | SQLException | PropertyVetoException e) {
            log.error("DAO getUserIdByNickname failed " + e);
            throw new DAOException("DAO getUserIdByNickname failed " + e);
        }
        return userID;
    }

    /**
     * This method add record in table USERS in database
     * @param user This is user which will be add in database
     * @return id user
     * @throws DAOException
     */
    @Override
    public Integer addEntity(User user) throws DAOException {

        if(getUserIdByNickname(user.getNickname()) != null){
            throw new DAOException("DAO addEntity failed: user with nickname '" + user.getNickname() + "' is exist");
        }

        Integer id = getNextId();

        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(UserQueries.SQL_ADD_USER)) {

            prst.setInt(1, id);
            prst.setString(2, user.getFirstName());
            prst.setString(3, user.getLastName());
            prst.setString(4, user.getPassword());
            prst.setString(5, user.getNickname());
            prst.executeUpdate();

        } catch (PropertyVetoException | IOException | SQLException e) {
            log.error("DAO addEntity failed " + e);
            throw new DAOException("DAO addEntity failed " + e);
        }

        return id;
    }

    /**
     * This method used to generate user id
     * @return Integer user ID
     * @throws DAOException
     */
    private Integer getNextId() throws DAOException{
        Integer id = null;
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(UserQueries.SQL_GET_USER_ID)){
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (PropertyVetoException | IOException | SQLException e){
            log.error("DAO addEntity failed " + e);
            throw new DAOException("DAO getNextId failed " + e);
        }

        return id;
    }

    /**
     * This method return list of all users, which contains table USERS
     * @return List This is list of users
     * @throws DAOException
     */
    @Override
    public List<User> getEntities() throws DAOException {
        List<User> users = new ArrayList<>();

        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(UserQueries.SQL_GET_LIST_OF_USERS)){

            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (PropertyVetoException | SQLException | IOException e){
            log.error("DAO getEntities failed " + e);
            throw new DAOException("DAO getEntities failed " + e);
        }

        return users;
    }

    /**
     * This method delete records from table USERS, where id equals given id
     * @param id User id which need to remove
     * @return int Amount of changing row in database
     * @throws DAOException
     */
    @Override
    public int deleteEntity(int id) throws DAOException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SQL_DELETE_USER)){

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();

        } catch(PropertyVetoException | SQLException | IOException e) {
            log.error("DAO deleteEntity failed " + e);
            throw new DAOException("DAO deleteEntity failed " + e);
        }
    }

    /**
     * Update record in table USERS in database
     * @param userNew User which need to update
     * @return int Amount of changing row in database
     * @throws DAOException
     */
    @Override
    public int updateEntity(User userNew, User userOld) throws DAOException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement prst = connection.prepareStatement(UserQueries.SQL_UPDATE_USER)) {

            prst.setString(1, userNew.getFirstName());
            prst.setString(2, userNew.getLastName());
            prst.setString(3, userNew.getPassword());
            prst.setInt(4, userNew.getId());

            return prst.executeUpdate();

        } catch (PropertyVetoException | IOException | SQLException e) {
            log.error("DAO editEntity failed " + e);
            throw new DAOException("DAO editEntity failed " + e);
        }
    }

    /**
     * This method create user with parameter such as in result set
     * @param resultSet
     * @return User Creating user
     * @throws SQLException
     */
    private User createUser(ResultSet resultSet) throws SQLException {

        User user = new User();

        user.setId(resultSet.getInt("ID"));
        user.setFirstName(resultSet.getString("FIRST_NAME"));
        user.setLastName(resultSet.getString("LAST_NAME"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setNickname(resultSet.getString("NICK_NAME"));

        return user;
    }

}
