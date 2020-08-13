package com.capgemini.userDashBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.userDashBoard.entities.MyUser;
/**
 * @author lhatwar
 *
 */
@Repository

public interface UserJpaRepository extends JpaRepository<MyUser, Long>{
	

}
