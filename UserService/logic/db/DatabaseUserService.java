package acs.logic.db;

import acs.boundary.UserBoundaryWithPassword;
import acs.exceptions.AlreadyExistsException;
import acs.logic.EnhancedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import acs.boundary.UserBoundary;
import acs.dao.UserDao;
import acs.data.UserEntity;
import acs.exceptions.NotFoundException;
import acs.exceptions.UnauthorizedException;
import acs.logic.utils.UserConverter;
import acs.utils.CriteriaType;
import acs.utils.UserFullName;

@Service
public class DatabaseUserService implements EnhancedUserService {
	private UserDao userDao; // Data access object
	private UserConverter converter;

	@Autowired
	public DatabaseUserService(UserDao userDao, UserConverter converter) {
		this.userDao = userDao;
		this.converter = converter;
	}

	@Override
	@Transactional
	public UserBoundary createUser(UserBoundaryWithPassword userBoundary) {
		UserEntity userEntity = this.converter.toEntity(userBoundary);
		UserEntity user = this.userDao.findByEmail(userEntity.getEmail());
		if(user != null){
			throw new AlreadyExistsException("User is already exists");
		}
		userEntity = this.userDao.save(userEntity);
		return this.converter.fromEntity(userEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary getUser(String email) {
		UserEntity userEntity = this.userDao.findById(email)
				.orElseThrow(() -> new NotFoundException("no user found by email: " + email));

		return this.converter.fromEntity(userEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary login(String email, String password) {
		UserEntity userEntity = this.userDao.findById(email)
				.orElseThrow(() -> new NotFoundException("no user found by email: " + email));

		if (!userEntity.getPassword().equals(password)) {
			throw new UnauthorizedException("wrong password");
		}

		return this.converter.fromEntity(userEntity);

	}

	@Override
	@Transactional
	public void updateUser(String email, UserBoundaryWithPassword update) {
		UserEntity userEntity = this.userDao.findById(email)
				.orElseThrow(() -> new NotFoundException("no user found by email: " + email));
		String password = userEntity.getPassword();

		update.setEmail(email);

		UserBoundary user = this.converter.fromEntity(userEntity);

		if (update.getName().getFirst() == null) {
			update.setName(new UserFullName(user.getName().getFirst(), update.getName().getLast()));
		}

		if (update.getName().getLast() == null) {
			update.setName(new UserFullName(update.getName().getFirst(), user.getName().getLast()));
		}

		if (update.getPassword() == null) {
			update.setPassword(password);
		}

		if (update.getBirthdate() == null) {
			update.setBirthdate(user.getBirthdate());
		}
		if (update.getRoles() == null) {
			update.setRoles(user.getRoles());
		}

		this.userDao.save(this.converter.toEntity(update));
	}

	@Override
	@Transactional
	public void deleteAllUsers() {
		this.userDao.deleteAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String criteriaType, String criteriaValue, int size, int page,
			String sortBy, String sortOrder) {
		if (criteriaType != null && criteriaValue != null) {
			if (criteriaType.equals(CriteriaType.BY_LAST_NAME.toString())) {
				return this.userDao
						.findAllByLastNameLikeIgnoreCase(criteriaValue,
								PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
			} else if (criteriaType.equals(CriteriaType.BY_MINIMUM_AGE.toString())) {
				return this.userDao
						.findAllByAgeGreaterThan(Integer.parseInt(criteriaValue),
								PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
			} else if (criteriaType.equals(CriteriaType.BY_ROLE.toString())) {
				return findAllByRole(criteriaValue, size, page, sortBy, sortOrder);
			}
		}
		return this.userDao.findAll(PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy)).getContent()
		.stream().map(this.converter::fromEntity).collect(Collectors.toList());
		// throw new BadRequestException();
	}

	public List<UserBoundary> findAllByRole(String criteriaValue, int size, int page, String sortBy, String sortOrder){
		return this.userDao.findAll(PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy)).stream()
				.filter(userEntity -> userEntity.roles.contains(criteriaValue))
				.map(this.converter::fromEntity).collect(Collectors.toList());
	}


}
