package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.models.Account;
import com.blog.itmc.models.Department;
import com.blog.itmc.models.MemDepart;
import com.blog.itmc.models.MemDepartId;
import com.blog.itmc.models.MemTeam;
import com.blog.itmc.models.MemTeamId;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Team;
import com.blog.itmc.models.Department;
import com.blog.itmc.repositories.DepartmentRepo;
import com.blog.itmc.repositories.MemDepartRepo;
import com.blog.itmc.repositories.MemTeamRepo;
import com.blog.itmc.repositories.MemberRepo;
import com.blog.itmc.repositories.TeamRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo repository;
    
    @Autowired
    MemDepartRepo memDepartRepo;
    
    @Autowired
    MemberRepo memberRepo;
    
    @Autowired
    MemTeamRepo memTeamRepo;
    

    public APIResponse findAll() {
    	APIResponse allTeam = new APIResponse();
    	allTeam.setStatus(true);
    	allTeam.setMessage("Lấy dữ liệu thành công ");
    	allTeam.setData(repository.findAll());
        return allTeam; 
    }
    
    public APIResponse createDepart(Department depart) {
    	 Optional<Department> t2 = repository.findById(depart.getName()) ;
         APIResponse newDepart = new APIResponse();
         if (t2 == null) {
      	   	newDepart.setStatus(true);
         	newDepart.setMessage("");
         	newDepart.setData(depart);
             repository.save(depart);
         }else {
      		newDepart.setStatus(false);
         	newDepart.setMessage("Ban đã tồn tại");
         	newDepart.setData(depart);
         }
         return newDepart ;
    }

    public APIResponse getById(String id)  {
        Department depart = repository.findById(id)
                .orElse(null);
        APIResponse data = new APIResponse();
        if (depart == null) {
     	   	data.setStatus(false);
        	data.setMessage("Team không tồn tại");
        	data.setData(depart);
        }else {
     		data.setStatus(true);
        	data.setMessage("");
        	data.setData(depart);
        }
        return data;
    }
    
    public APIResponse updateDepart(Department depart) {
    	Optional<Department> d = repository.findById(depart.getName()) ;
        APIResponse data = new APIResponse();
        if (d == null) {
        	data.setStatus(false);
       		data.setMessage("Ban không tồn tại");
       		data.setData(depart);
        }else {
        	data.setStatus(true);
    		data.setMessage("");
    		data.setData(depart);
        	repository.save(depart);
        }
        return data ;
    }
    
    
//    public APIResponse deleteDepart(String departId)  {
//    	Department t2 = repository.findById(departId)
//                .orElse(null);
//    	 APIResponse data = new APIResponse();
//         if (t2 == null) {
//         	data.setStatus(false);
//        	data.setMessage("Ban không tồn tại");
//        	data.setData(departId);
//         }else {
//         	data.setStatus(true);
//     		data.setMessage("");
//     		data.setData(t2);
//     		memDepartRepo.deleteByDepartId(departId);
//            repository.deleteById(departId);
//         }
//         return data ;
//    }
    


    public APIResponse addMemDepart(MemDepart mem) throws ResourceNotFoundException, IllegalArgumentException{
        
    	 MemDepartId id  = new MemDepartId();
         id.setMemberId(mem.getMemberId());
         id.setDepartId(mem.getDepartId());
         Optional<MemDepart> l = memDepartRepo.findById(id);
         
         APIResponse data = new APIResponse();
         if (l == null) {
          	data.setStatus(true);
         	data.setMessage("");
         	data.setData(mem);
         	memDepartRepo.save(mem);
          }else {
          	data.setStatus(false);
      		data.setMessage("Thêm mới thất bại do sinh viên đã thuộc ban");
      		data.setData(null);
          }  
         return data ;
     }
    
    public APIResponse updateMemDepart(MemDepart mem) throws ResourceNotFoundException, IllegalArgumentException{
        
    	MemDepartId id  = new MemDepartId();
        id.setMemberId(mem.getMemberId());
        id.setDepartId(mem.getDepartId());
        Optional<MemDepart> l = memDepartRepo.findById(id);
        
        APIResponse data = new APIResponse();
        if (l != null) {
         	data.setStatus(true);
        	data.setMessage("");
        	data.setData(mem);
        	memDepartRepo.save(mem);
         }else {
         	data.setStatus(false);
     		data.setMessage("Cập nhật thất bại do sinh viên chưa thuộc team nào");
     		data.setData(null);
         }  
        return data ;
     }
    
    public APIResponse deteleMemDepart(String departId, int memId){
        
    	MemDepartId id  = new MemDepartId();
        id.setMemberId(memId);
        id.setDepartId(departId);
        Optional<MemDepart> l = memDepartRepo.findById(id);
        
        APIResponse data = new APIResponse();
        if (l != null) {
         	data.setStatus(true);
         	data.setMessage("");
         	data.setData(id);
         	memDepartRepo.deleteById(id);
         	memDepartRepo.deleteByMemId(memId);
        }else {
         	data.setStatus(false);
     		data.setMessage("sinh viên chưa thuộc team này");
     		data.setData(null);
         }  
        return data ;
      } 
	  
    
}
