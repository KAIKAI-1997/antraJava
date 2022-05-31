package com.HWday19.Controller;


import com.HWday19.Service.playerService;
import com.HWday19.domain.players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("players")
public class controller {
    private final playerService playerService;

    @Autowired
    public controller(playerService playerService){
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<players> getplayerByID(@PathVariable long ID) {
        return new ResponseEntity<>(playerService.getPlayerById(ID), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<players>> getAllEmployeesbyGroup() {
        return new ResponseEntity<>(playerService.getAll(), HttpStatus.OK);
    }
}
