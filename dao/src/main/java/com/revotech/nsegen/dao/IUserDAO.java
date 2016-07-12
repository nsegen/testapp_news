package com.revotech.nsegen.dao;

import com.revotech.nsegen.entities.User;
import com.revotech.nsegen.exceptions.DAOException;

/**
 * Created by Revotech on 12.07.16.
 */
public interface IUserDAO extends DAO<User> {
    Integer getUserIdByNickname(String nickname) throws DAOException;
    User getUserById(Integer id) throws DAOException;
}
