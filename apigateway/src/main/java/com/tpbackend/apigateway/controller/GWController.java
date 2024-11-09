package com.tpbackend.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    Este GWController, nos indica el estado actual de la AGW
*/

@RestController
public class GWController {
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("La API GateWay está en funcionamiento.");
    }
}
