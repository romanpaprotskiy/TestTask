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

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * @param name - department name
     * @return all departments or if name is present return all by name
     */
    @GetMapping
    public List<Department> getDepartments(@RequestParam(required = false) String name) {
        if (name != null)
            return departmentService.getByName(name);
        return departmentService.getDepartments();
    }

    /**
     * @param departmentId - id department
     * @return department by id
     */
    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getById(departmentId);
    }

    /**
     * @param department department object that will be added
     * @return department which was added to database
     */
    @PostMapping
    public Department addDepartment(@Valid @RequestBody Department department) {
        return departmentService.add(department);
    }

    /**
     * @param departmentId id department
     * @param department department object that will be added and update current department
     * @return updated department
     */
    @PutMapping("/{departmentId}")
    public Department updateDepartment(@PathVariable Long departmentId, @Valid @RequestBody Department department) {
        return departmentService.update(departmentId, department);
    }

    /**
     * @param departmentId id department which will be deleted
     * @return 200ok if department was deleted
     */
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        if (departmentService.getById(departmentId) != null) {
            departmentService.delete(departmentId);
            return ResponseEntity.ok().build();
        } else throw new ResourceNotFoundException("DepartmentId " + departmentId + " not found");
    }

    /**
     * @param departmentId id department
     * @return all positions that is in department
     */
    @GetMapping("/{departmentId}/positions")
    public List<Position> getPositions(@PathVariable Long departmentId) {
        return departmentService.getPositions(departmentId);
    }

    /**
     * @param departmentId id department
     * @param positionId id position
     * @return department with added position
     */
    @PutMapping("/{departmentId}/positions")
    public Department addPosition(@PathVariable Long departmentId, @RequestParam(name = "position") Long positionId) {
        return departmentService.addPosition(departmentId, positionId);
    }

    /**
     * @param departmentId id department
     * @param positionId id position
     * @return department without position
     */
    @DeleteMapping("/{departmentId}/positions")
    public Department removePosition(@PathVariable Long departmentId, @RequestParam(name = "position") Long positionId) {
        return departmentService.removePosition(departmentId, positionId);
    }
}
