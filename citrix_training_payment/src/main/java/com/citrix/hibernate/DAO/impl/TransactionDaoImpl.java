package com.citrix.hibernate.dao.impl;


import org.springframework.stereotype.Repository;

import com.citrix.hibernate.dao.HibernateDao;
import com.citrix.hibernate.dao.TransactionDao;
import com.citrix.training.hibernate.entity.Transactions;

@Repository("transactionDao")
public class TransactionDaoImpl extends HibernateDao<Transactions, Long> implements TransactionDao {

}

