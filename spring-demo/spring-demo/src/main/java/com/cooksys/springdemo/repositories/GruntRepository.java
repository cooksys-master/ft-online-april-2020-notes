package com.cooksys.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.springdemo.entities.Grunt;

@Repository
public interface GruntRepository extends JpaRepository<Grunt, Long> {

}
