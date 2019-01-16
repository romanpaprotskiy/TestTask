package com.kindgeek.testtask.controller;


import com.kindgeek.testtask.entity.Position;
import com.kindgeek.testtask.exception.ResourceNotFoundException;
import com.kindgeek.testtask.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public List<Position> getAllPositions(){
        return positionService.getPositions();
    }

    @GetMapping("/{positionId}")
    public Position getById(@PathVariable Long positionId){
        return positionService.getById(positionId);
    }

    @PostMapping
    public Position addPosition(@Valid @RequestBody Position position){
        return positionService.add(position);
    }

    @PutMapping("/{positionId}")
    public Position updatePosition(@PathVariable Long positionId,@Valid @RequestBody Position position){
        return positionService.update(positionId,position);
    }

    @DeleteMapping("/{positionId}")
    public ResponseEntity<?> deletePosition(@PathVariable Long positionId){
        if (positionService.getById(positionId) != null) {
            positionService.delete(positionId);
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("personId " + positionId + " not found");
        }
    }



}
