
package com.blog.itmc.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.models.MemTeam;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Team;
import com.blog.itmc.services.MemberService;
import com.blog.itmc.services.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService service;

    @GetMapping("")
    public APIResponse findAll() {
        return service.findAll();
    }
    
    @PostMapping("")
    public APIResponse createTeam(@Valid @RequestBody Team team) {
        return service.createTeam(team);
    }
    
    @PutMapping("")
    public APIResponse updateTeam(@Valid @RequestBody Team team) {
    	return service.updateTeam(team);
    }
    
    @DeleteMapping("")
    public APIResponse deleteTeam(@Valid @RequestBody String teamId) {
    	return service.deleteTeam(teamId);
    }
    
    @GetMapping("/{id}")
    public APIResponse getTeamById(@PathVariable(name = "id") String id) {
    	return service.getById(id);
    }
    
    
    ////

     
    @PostMapping("/member")
    public APIResponse createMemTeam(@Valid @RequestBody MemTeam MemTeam) {
        return service.createMemTeam(MemTeam);
    }
    
    @PutMapping("/member")
    public APIResponse updateMemTeam(@Valid @RequestBody MemTeam MemTeam) {
        return service.updateMemTeam(MemTeam);
    }
    
    @DeleteMapping("/member")
    public APIResponse deleteMemTeam(@RequestParam Map<String, String> param) {
    	String teamId = param.getOrDefault("teamId", "");
    	String memId = param.getOrDefault("memId", "");
    	return service.deteleMemTeam(teamId,Integer.parseInt(memId));
    }
 }