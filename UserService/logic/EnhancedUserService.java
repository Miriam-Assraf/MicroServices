package acs.logic;

import org.springframework.stereotype.Repository;

public interface EnhancedUserService extends UserService{

    void deleteAllUsers();

}
