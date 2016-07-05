package com.revotech.nsegen.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Revotech on 05.07.16.
 */
public interface DAO<T> {
    int addEntity(T entity) throws SQLException;
    List<T> getEntities() throws PropertyVetoException, SQLException, IOException;
    int deleteEntity(int id) throws SQLException;
    int updateEntity(T entity) throws SQLException;
}
