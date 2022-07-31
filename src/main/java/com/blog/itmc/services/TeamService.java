
package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.models.Account;
import com.blog.itmc.models.Department;
import com.blog.itmc.models.MemTeam;
import com.blog.itmc.models.MemTeamId;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Team;
import com.blog.itmc.repositories.DepartmentRepo;
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
    
    @Autowired
    DepartmentRepo departRepo;

    public APIResponse findAll() {
    	List<Team> teams = repository.findAll();
    	for (Team team:teams) {
    		team.setMemTeam(null);
    	}
    	return new APIResponse(true,"Lấy dữ liệu thành công ",teams);
    }
    
    public APIResponse createTeam(Team newTeam){
    	Optional<Team> teamFound = repository.findById(newTeam.getName()) ;
       	APIResponse response = new APIResponse(true,"Tạo team thành công",null);
       	if (teamFound.isEmpty()) {
    	   newTeam = repository.save(newTeam);
           response.setData(newTeam);
           return response;
       	}
		response.setStatus(false);
	    response.setMessage("Team đã tồn tại"); 
        return response ;
    }

    public APIResponse getById(String id)  {
        Optional<Team> teamFound = repository.findById(id);
        APIResponse response = new APIResponse(false,"Team không tồn tại",null);
        if (teamFound.isEmpty()) {
     	   	return response ;
        }
        teamFound.get().setMemTeam(null);
 		response.setStatus(true);
    	response.setMessage("Lấy dữ liệu thành công");
    	response.setData(teamFound.get());
        return response;
    }
    
    public APIResponse updateTeam(Team team) {
    	Optional<Team> teamFound = repository.findById(team.getName()) ;
        APIResponse response = new APIResponse(true,"Cập nhật thành công",null);
        if (teamFound.isEmpty() == false) {
        	Team oldTeam = teamFound.get();
            Team newTeam = new Team(team.getName(), null, null,null, oldTeam.getMemTeam());
            newTeam.setIcon(team.getIcon() == null ? oldTeam.getIcon() : team.getIcon());
            newTeam.setDescription(team.getDescription() == null ? oldTeam.getDescription() : team.getDescription());           
            newTeam.setDepart(team.getDepart() == null ? oldTeam.getDepart() : team.getDepart());
            newTeam = repository.save(newTeam);
            newTeam.setMemTeam(null);
            response.setData(newTeam);           
            return response ;
        }
    	response.setStatus(false);
		response.setMessage("Team không tồn tại");
		response.setData(null);
        return response ;
    }
    
    public APIResponse deleteTeam(String teamId)  {
    	Optional<Team> teamFound = repository.findById(teamId);             
    	APIResponse response = new APIResponse(false,"Team không tồn tại",null);
        if (teamFound.isEmpty()) {
        	return response ;
        }
        memTeamRepo.deleteByTeamId(teamId);
        repository.deleteById(teamId);
     	response.setStatus(true);
 		response.setMessage("Xóa team thành công");
 		response.setData(teamId);
        return response ;
    }
    
  ///get team
    public APIResponse getTeamByDepartId(String departId)  {
    	Optional<Department> departFound = departRepo.findById(departId);
    	APIResponse response = new APIResponse(false,"Ban không tồn tại",null);
        if (departFound.isEmpty()) {
        	return response ;
        }
        List<Team> teams = (List) departFound.get().getTeams() ;
        for(Team team:teams) {
        	team.setMemTeam(null);
        }
        response.setStatus(true);
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(teams);
        return response;
    }
    
   /// Memteam
    
    public APIResponse createMemTeam(MemTeam newMem) {      
        MemTeamId id  = new MemTeamId(newMem.getMemberId(),newMem.getTeamId());    
        Optional<MemTeam> memFound = memTeamRepo.findById(id);      
        APIResponse response = new APIResponse(true,"Thêm sinh viên vào team thành công",null);
        if (memFound.isEmpty()) {
        	newMem = memTeamRepo.save(newMem);
        	response.setData(newMem);
        	return response ;   	
        }
     	response.setStatus(false);
 		response.setMessage("Thêm mới thất bại do sinh viên đã thuộc team");
 		response.setData(null);
        return response ;
    }
    
    public APIResponse updateMemTeam(MemTeam mem){       
    	MemTeamId id  = new MemTeamId(mem.getMemberId(),mem.getTeamId());  
        Optional<MemTeam> memFound = memTeamRepo.findById(id);        
        APIResponse response = new APIResponse(true,"Cập nhật thành công",null);
        if (memFound.isEmpty()) {
        	mem = memTeamRepo.save(mem);
         	response.setData(mem);
         	return response ;
        }
      	response.setStatus(false);
  		response.setMessage("Cập nhật thất bại do sinh viên chưa thuộc team nào");
  		response.setData(null);         
        return response ;
   }
    
   public APIResponse deteleMemTeam(String teamId, int memId){
        
	   	MemTeamId id  = new MemTeamId(memId,teamId);
	   	
	   	Optional<MemTeam> memFound = memTeamRepo.findById(id);
       
	   	APIResponse response = new APIResponse(true,"Xóa sinh viên ra team thành công",null);
	   	if (memFound != null) {
        	memTeamRepo.deleteById(id);
        	return response ;
	   	}
    	response.setStatus(false);
		response.setMessage("sinh viên chưa thuộc team này");
		return response ;
   }  
}
