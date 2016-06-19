package com.citrix.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citrix.hibernate.dao.UserDao;
import com.citrix.training.hibernate.entity.User;
import com.citrix.training.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		userDao.add(user);
		return user;
	}

	@Override
	public User update(User user) {
		User savedUser= get(user.getId());
		savedUser.setFirstName(user.getFirstName());
		savedUser.setLastName(user.getLastName());
		savedUser.setUserName(user.getUserName());
		savedUser.setEmail(user.getEmail());
		userDao.update(savedUser);
		return savedUser;
	}

	@Override
	public void delete(Long id) {
		User user= get(id);
		userDao.remove(user);
	}

	@Override
	public User get(Long id) {
		User user= userDao.find(id);
		if(user==null){
			throw new EntityNotFoundException("User could not be found with id="+id);
		}
		return user;
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

}
