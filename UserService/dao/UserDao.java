package acs.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import acs.data.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByLastNameLikeIgnoreCase(@Param("lastName") String lastName, Pageable pageable);
    
    List<UserEntity> findAllByAgeGreaterThan(@Param("age") int age, Pageable pageable);

    UserEntity findByEmail(@Param("email") String email);

}