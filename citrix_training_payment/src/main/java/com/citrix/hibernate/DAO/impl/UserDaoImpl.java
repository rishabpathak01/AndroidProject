package com.citrix.hibernate.dao.impl;


import org.springframework.stereotype.Repository;

import com.citrix.hibernate.dao.HibernateDao;
import com.citrix.hibernate.dao.UserDao;
import com.citrix.training.hibernate.entity.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDao<User, Long> implements UserDao {

}

