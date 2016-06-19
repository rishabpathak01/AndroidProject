package com.citrix.training.service;

import java.util.List;

import com.citrix.training.hibernate.entity.UserQualification;

public interface UserQualificationService {

	public UserQualification save(Long userId,UserQualification userQualification);

	public UserQualification update(UserQualification userQualification);

	public void delete(Long id);

	public UserQualification get(Long id);

	public List<UserQualification> list(Long userId);

}
