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
    	APIResponse allMember = new APIResponse();
    	allMember.setStatus(true);
    	allMember.setMessage("Lấy dữ liệu thành công ");
    	allMember.setData(repository.findAll());
        return allMember;
    }
    
    public APIResponse createMember(Member member) throws ResourceNotFoundException, IllegalArgumentException{
       
       APIResponse newMember = new APIResponse();
       Optional<Member> m = repository.findById(member.getId());
    
       if (m == null)
           	repository.save(member);
       else {
           	newMember.setStatus(false);
           	newMember.setMessage("Mã sinh viên đã tồn tại");
           	newMember.setData(null);
       }
       return newMember ;
    }
    
    public APIResponse getById(int id) throws ResourceNotFoundException, IllegalArgumentException  {
    	
    	APIResponse getMember = new APIResponse();
        Member member = repository.findById(id)
                .orElse(null);
        if (member == null ) {
        	getMember.setStatus(false);
        	getMember.setMessage("id không tồn tại");
        	getMember.setData(null);
        }
        else {
        	getMember.setStatus(true);
        	getMember.setMessage("");
        	getMember.setData(member);
        }
        return getMember;
    }
    public APIResponse updateMember(Member member)  {
    	
    	APIResponse getMember = new APIResponse();
        Member m = repository.findById(member.getId())
                .orElse(null);
        
        if (m == null ) {
        	getMember.setStatus(false);
        	getMember.setMessage("id không tồn tại");
        	getMember.setData(null);
        }
        else {
        	getMember.setStatus(true);
        	getMember.setMessage("");
        	getMember.setData(member);
        	repository.save(member);
        }
        return getMember;
    }
    
    public APIResponse deleteMember(int id)  {
        Member m = repository.findById(id)
                .orElseThrow(null);
        APIResponse getMember = new APIResponse();
        if (m == null ) {
        	getMember.setStatus(false);
        	getMember.setMessage("id không tồn tại");
        	getMember.setData(null);
        }
        else {
        	getMember.setStatus(true);
        	getMember.setMessage("");
        	getMember.setData(m);
        	repository.save(m);
        	memTeamRepo.deleteByMemId(m.getId());
            memDepartRepo.deleteByMemId(m.getId());
            repository.deleteById(m.getId());
        }
        return getMember;
    }
    
    
    /////
    
    public APIResponse getAllMemTeamByTeamId(String teamId) {
    	Team team = TeamRepo.findById(teamId)
                .orElse(null);
    	List<MemTeam> l = (List) team.getMemTeam() ;
    	List<Member> myList = new ArrayList<Member>();
    	for(MemTeam x : l) {
    		Optional<Member> member = repository.findById(x.getMemberId()) ;
    		myList.add(member.get());
    	}
    	APIResponse data = new APIResponse();
        data.setStatus(true);
       	data.setMessage("");
       	data.setData(myList);
    	
    	
    	return data ;
    }
    
    public APIResponse getMemDepartById(String nameDepart) {
    	Department MemDepart = departRepo.findById(nameDepart)
    			 .orElse(null);
    	List<MemDepart> l = (List) MemDepart.getMemDepart() ;
    	List<Member> myList = new ArrayList<Member>();
    	for(MemDepart x : l) {
    		Optional<Member> member = repository.findById(x.getMemberId()) ;
    		myList.add(member.get());
    	}
    	
    	APIResponse data = new APIResponse();
        data.setStatus(true);
       	data.setMessage("");
       	data.setData(myList);
    	return data ;
    }
}
