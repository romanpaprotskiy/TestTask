package com.kindgeek.testtask.controller;

import com.kindgeek.testtask.entity.Department;
import com.kindgeek.testtask.entity.Person;
import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.service.DepartmentService;
import com.kindgeek.testtask.service.PersonService;
import com.kindgeek.testtask.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;
    private final DepartmentService departmentService;
    private final PersonService personService;

    @Autowired
    public PositionController(PositionService positionService, DepartmentService departmentService, PersonService personService) {
        this.positionService = positionService;
        this.departmentService = departmentService;
        this.personService = personService;
    }

    /**
     * @param name - search by name like
     * @return all positions from database or if name is present return all by name like
     */
    @GetMapping
    public List<Position> getAllPositions(@RequestParam(required = false) String name) {
        if (name != null) {
            return positionService.getByName(name);
        }
        return positionService.getPositions();
    }

    /**
     * @param positionId - id position
     * @return position with given id
     */
    @GetMapping("/{positionId}")
    public Position getPositionById(@PathVariable Long positionId) {
        return positionService.getById(positionId);
    }

    /**
     * @param position     - position object that will be added to database
     * @param personId     - if position object does not have person add person by id
     * @param departmentId - if position object does not have department add department by id
     * @return added position
     */
    @PostMapping
    public Position addPosition(@Valid @RequestBody Position position, @RequestParam(name = "person", required = false) Long personId,
                                @RequestParam(name = "department", required = false) Long departmentId) {
        if (position.getDepartment() == null && departmentId != null) {
            Department department = departmentService.getById(departmentId);
            department.addPosition(position);
        }
        Position add = positionService.add(position);
        if (position.getPerson() == null && personId != null) {
            Person person = personService.getById(personId);
            person.addPosition(add);
        }
        return positionService.getById(add.getId());
    }

    /**
     * @param positionId - id position what you need to update
     * @param position   - position object which is required put in database
     * @param personId - if position object does not have person update person by id
     * @param departmentId - if position object does not have department update department by id
     * @return updated position
     */
    @PutMapping("/{positionId}")
    public Position updatePosition(@PathVariable Long positionId, @Valid @RequestBody Position position,
                                   @RequestParam(name = "person", required = false) Long personId,
                                   @RequestParam(name = "department", required = false) Long departmentId) {

        if (position.getPerson() != null && personId != null)
            personService.addPosition(personId, positionId);
        if (position.getDepartment() != null && departmentId != null)
            departmentService.addPosition(departmentId, positionId);
        return positionService.update(positionId, position);
    }

    /**
     * @param positionId - id position what you need to delete
     * @return - 200ok if position was deleted
     */
    @DeleteMapping("/{positionId}")
    public ResponseEntity<?> deletePosition(@PathVariable Long positionId) {
        if (positionService.getById(positionId) != null) {
            positionService.delete(positionId);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("personId " + positionId + " not found");
        }
    }

    /**
     * @param positionId - id position
     * @return person who work on this position
     */
    @GetMapping("/{positionId}/person")
    public Person getPerson(@PathVariable Long positionId) {
        return positionService.getById(positionId).getPerson();
    }


    /**
     * @param positionId - id position
     * @param personId - id person which will be set on position
     * @return position with person
     */
    @PutMapping("/{positionId}/person")
    public Position addPerson(@PathVariable Long positionId, @RequestParam(name = "person") Long personId) {
        Person person = personService.getById(personId);
        Position position = positionService.getById(positionId);
        person.addPosition(position);
        return positionService.update(positionId, position);
    }

    /**
     * @param positionId id position
     * @param personId id person which will be deleted from position
     * @return updated position
     */
    @DeleteMapping("/{positionId}/person")
    public Position removePerson(@PathVariable Long positionId, @RequestParam(name = "person") Long personId) {
        Person person = personService.getById(personId);
        Position position = positionService.getById(positionId);
        person.removePosition(position);
        return positionService.update(positionId, position);
    }

    /**
     * @param positionId id position
     * @return position department
     */
    @GetMapping("/{positionId}/department")
    public Department getDepartment(@PathVariable Long positionId) {
        return positionService.getById(positionId).getDepartment();
    }


}
