package com.citrix.hibernate.dao.impl;


import org.springframework.stereotype.Repository;

import com.citrix.hibernate.dao.HibernateDao;
import com.citrix.hibernate.dao.UserQualificationDao;
import com.citrix.training.hibernate.entity.UserQualification;

@Repository("userQualificationDao")
public class UserQualificationDaoImpl extends HibernateDao<UserQualification, Long> implements UserQualificationDao {

}

