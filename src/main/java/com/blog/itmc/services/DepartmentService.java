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
        return new APIResponse(true, "Lấy dữ liệu thành công", repository.findAll());
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
        Optional<Department> depart = repository.findById(id);
        APIResponse response = new APIResponse(false, "Ban không tồn tại", null);
        if (depart.isEmpty()) {
            return response;
        }
        response.setStatus(true);
        response.setMessage("Lấy dữ liệu thành công");
        response.setData(depart);
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
        response.setStatus(true);
        response.setMessage("");
        response.setData(newDepart);
        return response;
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


    public APIResponse addMemDepart(MemDepart mem) {

        MemDepartId id = new MemDepartId();
        id.setMemberId(mem.getMemberId());
        id.setDepartId(mem.getDepartId());
        Optional<MemDepart> l = memDepartRepo.findById(id);

        APIResponse response = new APIResponse(true, "Thêm thành công", null);
        if (l.isPresent()) {
            memDepartRepo.save(mem);
            response.setData(mem);
            return response;
        } else {
            response.setStatus(false);
            response.setMessage("Thêm mới thất bại do sinh viên đã thuộc ban");
            response.setData(null);
        }
        return response;
    }

    public APIResponse updateMemDepart(MemDepart mem) throws ResourceNotFoundException, IllegalArgumentException {

        MemDepartId id = new MemDepartId();
        id.setMemberId(mem.getMemberId());
        id.setDepartId(mem.getDepartId());
        Optional<MemDepart> l = memDepartRepo.findById(id);

        APIResponse data = new APIResponse();
        if (l != null) {
            data.setStatus(true);
            data.setMessage("");
            data.setData(mem);
            memDepartRepo.save(mem);
        } else {
            data.setStatus(false);
            data.setMessage("Cập nhật thất bại do sinh viên chưa thuộc team nào");
            data.setData(null);
        }
        return data;
    }

    public APIResponse deteleMemDepart(String departId, int memId) {

        MemDepartId id = new MemDepartId();
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
        } else {
            data.setStatus(false);
            data.setMessage("sinh viên chưa thuộc team này");
            data.setData(null);
        }
        return data;
    }


}
