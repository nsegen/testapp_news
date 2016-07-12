package com.revotech.nsegen.dao;

import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.exceptions.DAOException;

/**
 * Created by Revotech on 05.07.16.
 */
public interface INewsDAO extends DAO<News>{
    News getNewsById(Integer id) throws DAOException;
}
