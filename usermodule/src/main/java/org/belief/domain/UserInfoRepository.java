package org.belief.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	//Page<UserInfo> findUseful(String name,Pageable pageable);
	
	UserInfo findByUserName(String userName);
}
