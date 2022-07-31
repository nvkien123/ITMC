package com.blog.itmc.services;

import com.blog.itmc.exceptions.ResourceNotFoundException;
import com.blog.itmc.apis.APIResponse;
import com.blog.itmc.models.Department;
import com.blog.itmc.models.MemDepart;
import com.blog.itmc.models.MemTeam;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Team;
import com.blog.itmc.models.Member;
import com.blog.itmc.models.Member;
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
public class MemberService {
    @Autowired
    MemberRepo repository;
    
    @Autowired
    TeamRepo TeamRepo;
    
    @Autowired
    DepartmentRepo departRepo;
    
    @Autowired
    MemTeamRepo memTeamRepo;
    
    @Autowired
    MemDepartRepo memDepartRepo;

    public APIResponse findAll() {
    	return new APIResponse(true,"Lấy dữ liệu thành công ",repository.findAll());
    }
    
    public APIResponse createMember(Member newMember){
    	Optional<Member> memberFound = repository.findById(newMember.getId());
    	APIResponse response = new APIResponse(true,"Thêm sinh viên thành công",null);
    	if (memberFound.isEmpty()) {
           	newMember = repository.save(newMember);
           	response.setData(newMember);
           	return response ;
    	}
    	response.setStatus(false);
    	response.setMessage("Mã sinh viên đã tồn tại");   
        return response ;
    }
    
    public APIResponse getById(int memberId)  {	
        Optional<Member> memberFound = repository.findById(memberId);
        APIResponse response = new APIResponse(false,"Mã sinh viên không tồn tại",null);
        if (memberFound.isEmpty()) {
        	return response ;
        }
    	response.setStatus(true);
    	response.setMessage("Lấy dữ liệu thành công");
    	response.setData(memberFound);
        return response;
    }
    
    public APIResponse updateMember(Member member)  {
        Optional<Member> memberFound = repository.findById(member.getId());
        APIResponse response = new APIResponse(false ,"Sinh viên không tồn tại",null);
        if (memberFound.isEmpty()) {
            return response;
        }
        Member oldMember = memberFound.get();
        Member newMember = new Member(member.getId(), "", "","",0);
        newMember.setStudentId(member.getStudentId() == null ? oldMember.getStudentId() : member.getStudentId());
        newMember.setFullName(member.getFullName() == null ? oldMember.getFullName() : member.getFullName());           
        newMember.setPhone(member.getPhone() == null ? oldMember.getPhone() : member.getPhone());
        newMember.setPositionId(member.getPositionId() == 0 ? oldMember.getPositionId() : member.getPositionId());
        newMember = repository.save(newMember);
    	response.setStatus(true);
    	response.setMessage("Cập nhật thành công");
        response.setData(newMember);           
        return response ;
    }
    
    public APIResponse deleteMember(int memberId)  {
        Optional<Member> memberFound = repository.findById(memberId);
        APIResponse response = new APIResponse(false,"Sinh viên không tồn tại",null);
        if (memberFound.isEmpty()) {
            return response;
        } 
        memTeamRepo.deleteByMemId(memberId);
        memDepartRepo.deleteByMemId(memberId);
        repository.deleteById(memberId);
        response.setStatus(true);
        response.setMessage("Xóa sinh viên thành công");
        response.setData(memberId);
        return response;
    }
    
    ///// 
    
    public APIResponse getMemTeamByTeamId(String teamId) {
    	Optional<Team> teamFound = TeamRepo.findById(teamId);
    	APIResponse response = new APIResponse(false,"Team không tồn tại",null);
    	if (teamFound.isEmpty()) {
    		return response ;
    	}
    	List<MemTeam> memTeams = (List) teamFound.get().getMemTeam() ;
    	List<Member> members = new ArrayList<Member>();
    	for(MemTeam memTeam : memTeams) {
    		Optional<Member> member = repository.findById(memTeam.getMemberId()) ;
    		if (member.isEmpty() == false)
    			members.add(member.get());
    	}
        response.setStatus(true);
       	response.setMessage("Lấy dữ liệu thành công");
       	response.setData(members);
    	return response ;
    }
    
    public APIResponse getMemDepartByDepartId(String departId) {
    	Optional<Department> departFound = departRepo.findById(departId);
    	APIResponse response = new APIResponse(false,"Ban không tồn tại",null);
		if (departFound.isEmpty()) {
    		return response ;
    	}
    	List<MemDepart> memDeparts = (List) departFound.get().getMemDepart() ;
    	List<Member> members = new ArrayList<Member>();
    	for(MemDepart memDepart : memDeparts) {
    		Optional<Member> member = repository.findById(memDepart.getMemberId()) ;
    		if (member.isEmpty() == false)
    			members.add(member.get());
    	}
    	response.setStatus(true);
       	response.setMessage("Lấy dữ liệu thành công");
       	response.setData(members);
    	return response ;
    }
}
