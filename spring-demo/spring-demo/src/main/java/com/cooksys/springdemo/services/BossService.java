package com.cooksys.springdemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.springdemo.entities.Boss;
import com.cooksys.springdemo.repositories.BossRepository;

@Service
public class BossService {
	
	private BossRepository bossRepository;
	
	public BossService(BossRepository bossRepository) {
		this.bossRepository = bossRepository;
	}

	public Long createBoss(Boss boss) {
		if (boss.getBoss() != null) {
			if (getBoss(boss.getBoss().getId()) == null) {
				boss.setBoss(null);
			}
		}
		return bossRepository.saveAndFlush(boss).getId();
	}

	public List<Boss> getBosses() {
		return bossRepository.findAll();
	}

	public Boss getBoss(Long id) {
		Optional<Boss> optionalBoss = bossRepository.findById(id);
		if (optionalBoss.isPresent()) {
			return optionalBoss.get();
		}
		// TODO handle exception
		return null;
	}

	public Boss updateBoss(Long id, Boss boss) {
		Optional<Boss> optionalBossToUpdate = bossRepository.findById(id);
		if (optionalBossToUpdate.isPresent()) {
			Boss bossToUpdate = optionalBossToUpdate.get();
			if (boss.getName() != null) {
				bossToUpdate.setName(boss.getName());
			}
			if (boss.getSalary() != null) {
				bossToUpdate.setSalary(boss.getSalary());
			}
			return bossRepository.saveAndFlush(bossToUpdate);
		}
		// TODO handle exception
		return null;
	}

	public Long deleteBoss(Long id) {
		Optional<Boss> optionalBoss = bossRepository.findById(id);
		if (optionalBoss.isPresent()) {
			Boss bossToDelete = optionalBoss.get();
			bossRepository.delete(bossToDelete);
			return bossToDelete.getId();
		}
		// TODO handle exception
		return null;
	}


}
