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
    	List<Department> departs = repository.findAll();
    	for (Department depart:departs) {
    		depart.setMemDepart(null);
    		depart.setTeams(null);
    	}
        return new APIResponse(true, "Lấy dữ liệu thành công", departs);
    }

    public APIResponse createDepart(Department newDepart) {
        Optional<Department> departFound = repository.findById(newDepart.getName());
        APIResponse response = new APIResponse(true, "Ban đã tạo thành công", null);
        if (departFound.isEmpty()) {
            newDepart = repository.save(newDepart);
            response.setData(newDepart);
            return response;
        }
        response.setStatus(false);
        response.setMessage("Ban đã tồn tại");
        response.setData(null);
        return response;
    }

    public APIResponse getById(String id) {
        Optional<Department> departFound = repository.findById(id);
        APIResponse response = new APIResponse(false, "Ban không tồn tại", null);
        if (departFound.isEmpty()) {
            return response;
        }
        departFound.get().setTeams(null);
        departFound.get().setMemDepart(null);
        response.setStatus(true);
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(departFound.get());
        return response;
    }

    public APIResponse updateDepart(Department depart) {
        Optional<Department> departFound = repository.findById(depart.getName());
        APIResponse response = new APIResponse(false, "Ban không tồn tại", null);
        if (departFound.isEmpty()) {
            return response;
        }
        Department oldDepart = departFound.get();
        Department newDepart = new Department(depart.getName(), null, null, oldDepart.getMemDepart());
        newDepart.setIcon(depart.getIcon() == null ? oldDepart.getIcon() : depart.getIcon());
        newDepart.setDescription(depart.getDescription() == null ? oldDepart.getDescription() : depart.getDescription());

        newDepart = repository.save(newDepart);
        newDepart.setMemDepart(null);
        response.setStatus(true);
        response.setMessage("Cập nhật thành công ");
        response.setData(newDepart);
        return response;
    }


    public APIResponse deleteDepart(String departId)  {
    	Optional<Department> departFound = repository.findById(departId);
    	APIResponse response = new APIResponse(false,"Ban không tồn tại",null);
        if (departFound.isEmpty()) {
        	return response ;
        }
        List<Team> teams = (List) departFound.get().getTeams() ;
        for(Team team:teams) {
        	memTeamRepo.deleteByTeamId(team.getName());
        }
 		memDepartRepo.deleteByDepartId(departId);
        repository.deleteById(departId);
        response.setStatus(true);
        response.setMessage("Xóa ban thành công");
        response.setData(departId);
        return response ;
    }
    
    
    ///memDepart


    public APIResponse addMemDepart(MemDepart member) {
    	MemDepartId id = new MemDepartId(member.getMemberId(),member.getDepartId());        
        Optional<MemDepart> memFound = memDepartRepo.findById(id);
        APIResponse response = new APIResponse(true, "Thêm thành công", null);
        if (memFound.isEmpty()){
            member = memDepartRepo.save(member);
            response.setData(member);
            return response;
        } 
        response.setStatus(false);
        response.setMessage("Thêm mới thất bại do sinh viên đã thuộc ban");
        response.setData(null);
        return response;
    }

    public APIResponse updateMemDepart(MemDepart member) throws ResourceNotFoundException, IllegalArgumentException {
        MemDepartId id = new MemDepartId(member.getMemberId(),member.getDepartId());
        Optional<MemDepart> memberFound = memDepartRepo.findById(id);
        APIResponse response = new APIResponse(true ,"Cập nhật thành công",null);
        if (memberFound.isEmpty()) {
            MemDepart newMember = memDepartRepo.save(member);
            response.setData(newMember);
            return response ;
        }
        response.setStatus(false);
        response.setMessage("Cập nhật thất bại do sinh viên chưa thuộc ban nào");
        return response;
    }

    public APIResponse deteleMemDepart(String departId, int memId) {
        MemDepartId id = new MemDepartId(memId, departId);
        Optional<MemDepart> memberFound = memDepartRepo.findById(id);
        APIResponse response = new APIResponse(true,"Xóa sinh viên ra ban thành công",null);
        if (memberFound != null) {
            memDepartRepo.deleteById(id);
            memDepartRepo.deleteByMemId(memId);
            memTeamRepo.deleteByMemId(memId);
            return response ;
        }
    	response.setStatus(false);
    	response.setMessage("sinh viên chưa thuộc ban này");
        return response;
    }


}
