
package com.blog.itmc.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import com.blog.itmc.models.Member;
import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.services.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("")
    public APIResponse findAll(@RequestParam Map<String, String> param) {   	
    	String teamName = param.getOrDefault("team", "");
    	String departName = param.getOrDefault("depart", "");
    	if(departName.length() == 0 && teamName.length() == 0)
    		return service.findAll(); 		
    	if (teamName.length() > 0)
    		return service.getMemTeamByTeamId(teamName);
    	return service.getMemDepartByDepartId(departName);
	}
    
    @PostMapping("")
    public APIResponse createMember(@Valid @RequestBody Member member) {
        return service.createMember(member);
    }
    
    @GetMapping("/{id}")
    public APIResponse getMember(@PathVariable(name = "id") int id) {
    	return service.getById(id);
    }
    
    @PutMapping("")
    public APIResponse updateMember(@Valid @RequestBody Member member) {
        return service.updateMember(member);
    }
    
    @DeleteMapping("/{id}")
    public APIResponse deleteMember(@PathVariable(name = "id") int id) {
        return service.deleteMember(id);
    }  
   
 }