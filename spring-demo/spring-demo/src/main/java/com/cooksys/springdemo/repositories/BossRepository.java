package com.cooksys.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.springdemo.entities.Boss;

@Repository
public interface BossRepository extends JpaRepository<Boss, Long> {

}
