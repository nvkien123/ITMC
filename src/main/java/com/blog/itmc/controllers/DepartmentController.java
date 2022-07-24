

package com.blog.itmc.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.models.Department;
import com.blog.itmc.models.MemDepart;
import com.blog.itmc.models.Member;
import com.blog.itmc.services.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("")
    public APIResponse findAll() {
        return service.findAll();
    }
    
    @PostMapping("")
    public APIResponse create(@Valid @RequestBody Department Department) {
        return service.createDepart(Department);
    }
    
    @GetMapping("/{id}")
    public APIResponse getTeamById(@PathVariable(name = "id") String id) {
    	return service.getById(id);
    }
    
    @PutMapping("")
    public APIResponse updateDepartment(@Valid @RequestBody Department Department) {
        return service.updateDepart(Department);
    }
    
//    @DeleteMapping("")
//    public APIResponse deletedepart(@Valid @RequestBody String teamId) {
//    	return service.deleteDepart(teamId);
//    }

    
    ////
     
    @PostMapping("/member")
    public APIResponse createMemDepart(@Valid @RequestBody MemDepart MemDepart) {
        return service.addMemDepart(MemDepart);
    }
    
    @PutMapping("/member")
    public APIResponse updateMemDepart(@Valid @RequestBody MemDepart MemDepartment) {
        return service.updateMemDepart(MemDepartment);
    }
    
    @DeleteMapping("/member")
    public APIResponse deleteMemDepart(@RequestParam Map<String, String> param) {
    	String departId = param.getOrDefault("q", "");
    	String memId = param.getOrDefault("a", "");
    	return service.deteleMemDepart(departId,Integer.parseInt(memId));
    }
 }