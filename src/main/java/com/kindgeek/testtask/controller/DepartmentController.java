package com.kindgeek.testtask.controller;


import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getById(departmentId);
    }

    @PostMapping
    public Department addDepartment(@Valid @RequestBody Department department){
        return departmentService.add(department);
    }

    @PutMapping("/{departmentId}")
    public Department updateDepartment(@PathVariable Long departmentId,@Valid @RequestBody Department department){
        return departmentService.update(departmentId,department);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId){
        if (departmentService.getById(departmentId) != null){
            departmentService.delete(departmentId);
            return ResponseEntity.ok().build();
        } else throw new ResourceNotFoundException("DepartmentId " + departmentId + " not found");
    }

    @GetMapping("/{departmentId}/positions")
    public List<Position> getPositions(@PathVariable Long departmentId){
        return departmentService.getPositions(departmentId);
    }

    @PutMapping("/{departmentId}/positions/{positionId}")
    public Department addPosition(@PathVariable Long departmentId, @PathVariable Long positionId){
        return departmentService.addPosition(departmentId,positionId);
    }

    @DeleteMapping("/{departmentId}/positions/{positionId}")
    public Department removePosition(@PathVariable Long departmentId, @PathVariable Long positionId){
        return departmentService.removePosition(departmentId,positionId);
    }
}
