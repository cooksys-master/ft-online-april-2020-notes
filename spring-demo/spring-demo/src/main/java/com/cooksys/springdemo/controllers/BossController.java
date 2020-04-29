package com.cooksys.springdemo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.springdemo.entities.Boss;
import com.cooksys.springdemo.services.BossService;

@RestController
@RequestMapping(value = "/bosses")
public class BossController {
	
	
	
	private BossService bossService;
	
	public BossController(BossService bossService) {
		this.bossService = bossService;
	}

	@PostMapping
	public Long createBoss(@RequestBody Boss boss) {
		return bossService.createBoss(boss);
	}
	
	@GetMapping
	public List<Boss> getBosses() {
		return bossService.getBosses();
	}
	
	@GetMapping("/{identity}")
	public Boss getBoss(@PathVariable("identity") Long id) {
		return bossService.getBoss(id);
	}
	
	@PatchMapping("/{id}")
	public Boss updateBoss(@PathVariable Long id, @RequestBody Boss boss) {
		return bossService.updateBoss(id, boss);
	}
	
	@DeleteMapping("/{id}")
	public Long deleteBoss(@PathVariable Long id) {
		return bossService.deleteBoss(id);
	}
	

}
