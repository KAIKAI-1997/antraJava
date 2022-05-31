package com.HWday19.secure;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface userReposiry extends JpaRepository<user, Integer> {

    boolean existsByUsername(String username);

    user findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
