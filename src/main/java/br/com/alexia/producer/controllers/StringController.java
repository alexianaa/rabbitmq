package br.com.alexia.producer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexia.producer.services.StringService;

@RestController 
@RequestMapping(value = "/produces")
public class StringController {
    private final StringService stringService;

    public StringController(StringService stringService) {
        this.stringService = stringService;
    }

    @GetMapping
    public ResponseEntity<String> produces(@RequestParam String message) {
        stringService.produce(message);
        return ResponseEntity.ok("Message sent to RabbitMQ: " + message);
    }
}
