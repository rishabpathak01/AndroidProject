package com.citrix.training.service;

import java.util.List;

import com.citrix.training.hibernate.entity.User;

public interface UserService {

	public User save(User user);

	public User update(User user);

	public void delete(Long id);

	public User get(Long id);

	public List<User> list();

}
