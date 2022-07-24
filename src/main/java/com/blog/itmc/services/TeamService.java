
package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Account;
import com.blog.itmc.models.MemTeam;
import com.blog.itmc.models.MemTeamId;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Team;
import com.blog.itmc.repositories.MemDepartRepo;
import com.blog.itmc.repositories.MemTeamRepo;
import com.blog.itmc.repositories.MemberRepo;
import com.blog.itmc.repositories.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.itmc.apis.APIResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepo repository;
    
    @Autowired
    MemDepartRepo memDepartRepo;
    
    @Autowired
    MemTeamRepo memTeamRepo;
    
    @Autowired
    MemberRepo MemberRepo;

    public APIResponse findAll() {
    	APIResponse allTeam = new APIResponse();
    	allTeam.setStatus(true);
    	allTeam.setMessage("Lấy dữ liệu thành công ");
    	allTeam.setData(repository.findAll());
        return allTeam; 
    }
    
    public APIResponse createTeam(Team team){
       Optional<Team> t2 = repository.findById(team.getName()) ;
       APIResponse newTeam = new APIResponse();
       if (t2 == null) {
    	   	newTeam.setStatus(true);
       		newTeam.setMessage("");
       		newTeam.setData(team);
           	repository.save(team);
       }else {
    		newTeam.setStatus(false);
       		newTeam.setMessage("Team đã tồn tại");
       		newTeam.setData(team);
       }
       return newTeam ;
    }

    public APIResponse getById(String id)  {
        Team team = repository.findById(id)
                .orElse(null);
        APIResponse newTeam = new APIResponse();
        if (team == null) {
     	   	newTeam.setStatus(false);
        	newTeam.setMessage("Team không tồn tại");
        	newTeam.setData(team);
        }else {
     		newTeam.setStatus(true);
        	newTeam.setMessage("");
        	newTeam.setData(team);
        }
        return newTeam;
    }
    
    public APIResponse updateTeam(Team team) {
    	Optional<Team> t2 = repository.findById(team.getName()) ;
        APIResponse newTeam = new APIResponse();
        if (t2 == null) {
        	newTeam.setStatus(false);
       		newTeam.setMessage("Team không tồn tại");
       		newTeam.setData(team);
        }else {
        	newTeam.setStatus(true);
    		newTeam.setMessage("");
    		newTeam.setData(team);
        	repository.save(team);
        }
        return newTeam ;
    }
    
    public APIResponse deleteTeam(String teamId)  {
    	Team t2 = repository.findById(teamId)
                .orElse(null);
    	 APIResponse newTeam = new APIResponse();
         if (t2 == null) {
         	newTeam.setStatus(false);
        	newTeam.setMessage("Team không tồn tại");
        	newTeam.setData(teamId);
         }else {
         	newTeam.setStatus(true);
     		newTeam.setMessage("");
     		newTeam.setData(t2);
     		memTeamRepo.deleteByTeamId(teamId);
            repository.deleteById(teamId);
         }
     
        return newTeam ;
    }

    
   /// Memteam
    

    public APIResponse createMemTeam(MemTeam mem) {
        
//        Member m = MemberRepo.findById(mem.getMemberId())
//        		.orElse(null);
        
        MemTeamId id  = new MemTeamId();
        id.setMemberId(mem.getMemberId());
        id.setTeamId(mem.getTeamId());
        Optional<MemTeam> l = memTeamRepo.findById(id);
        
        APIResponse data = new APIResponse();
        if (l == null) {
         	data.setStatus(true);
        	data.setMessage("");
        	data.setData(mem);
        	memTeamRepo.save(mem);
         }else {
         	data.setStatus(false);
     		data.setMessage("Thêm mới thất bại do sinh viên đã thuộc team");
     		data.setData(null);
         }  
        return data ;
     }
    
    public APIResponse updateMemTeam(MemTeam mem){
        
    	 MemTeamId id  = new MemTeamId();
         id.setMemberId(mem.getMemberId());
         id.setTeamId(mem.getTeamId());
         Optional<MemTeam> l = memTeamRepo.findById(id);
         
         APIResponse data = new APIResponse();
         if (l != null) {
          	data.setStatus(true);
         	data.setMessage("");
         	data.setData(mem);
         	memTeamRepo.save(mem);
          }else {
          	data.setStatus(false);
      		data.setMessage("Cập nhật thất bại do sinh viên chưa thuộc team nào");
      		data.setData(null);
          }  
         return data ;
     }
    
   public APIResponse deteleMemTeam(String teamId, int memId){
        
	   MemTeamId id  = new MemTeamId();
       id.setMemberId(memId);
       id.setTeamId(teamId);
       Optional<MemTeam> l = memTeamRepo.findById(id);
       
       APIResponse data = new APIResponse();
       if (l != null) {
        	data.setStatus(true);
        	data.setMessage("");
        	data.setData(id);
        	memTeamRepo.deleteById(id);
       }else {
        	data.setStatus(false);
    		data.setMessage("sinh viên chưa thuộc team này");
    		data.setData(null);
        }  
       return data ;
     } 
    
    
    
    
    
}
