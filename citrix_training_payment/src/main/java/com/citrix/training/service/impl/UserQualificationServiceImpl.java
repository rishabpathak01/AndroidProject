package com.citrix.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.citrix.hibernate.dao.UserQualificationDao;
import com.citrix.training.hibernate.entity.User;
import com.citrix.training.hibernate.entity.UserQualification;
import com.citrix.training.service.UserQualificationService;
import com.citrix.training.service.UserService;

@Service("userQualificationService")
@Transactional
public class UserQualificationServiceImpl implements UserQualificationService {

	@Autowired
	private UserQualificationDao userQualificationDao;

	@Autowired
	private UserService userService;

	@Override
	public UserQualification save(Long userId, UserQualification userQualification) {
		User user = userService.get(userId);
		userQualification.setUserId(user.getId());
		validate(userQualification);
		userQualificationDao.add(userQualification);
		return userQualification;
	}

	@Override
	public UserQualification update(UserQualification userQualification) {
		validate(userQualification);
		UserQualification savedUserQualification = get(userQualification.getId());
		BeanUtils.copyProperties(userQualification, savedUserQualification, new String[] { "id", "userId" });
		userQualificationDao.update(savedUserQualification);
		return savedUserQualification;
	}

	@Override
	public void delete(Long id) {
		UserQualification userQualification = get(id);
		userQualificationDao.remove(userQualification);
	}

	@Override
	public UserQualification get(Long id) {
		UserQualification userQualification = userQualificationDao.find(id);
		if (userQualification == null) {
			throw new EntityNotFoundException("Qualification cannot be found with id=" + id);
		}
		return userQualification;
	}

	@Override
	public List<UserQualification> list(Long userId) {
		List<Criterion> list = new ArrayList<Criterion>();
		list.add(Restrictions.eq("userId", userId));
		return userQualificationDao.list(list);
	}

	public void validate(UserQualification userQualification) {
		if (userQualification.getAdmissionYear().longValue() >= userQualification.getPassoutYear().longValue()) {
			throw new FieldErrorException("Admission Year=" + userQualification.getAdmissionYear()
					+ " should be <= Passout Year=" + userQualification.getPassoutYear());
		}
		List<Criterion> list = new ArrayList<Criterion>();
		list.add(Restrictions.eq("userId", userQualification.getUserId()));
		list.add(Restrictions.eq("degreeName", userQualification.getDegreeName()));
		List<UserQualification> res = userQualificationDao.list(list);
		if (!CollectionUtils.isEmpty(res)) {
			UserQualification userQualification1 = res.get(0);
			if (!userQualification1.getId().equals(userQualification.getId())) {
				throw new FieldErrorException(userQualification.getDegreeName() + " already exists with user");
			}
		}
	}

}
