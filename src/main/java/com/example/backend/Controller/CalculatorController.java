package com.example.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.Service.CalculatorService;

@RestController
@RequestMapping("/api/calc")
public class CalculatorController {
    @Autowired
    CalculatorService calculatorService;
    @GetMapping("/fintot")
    public ResponseEntity<String> finto(@RequestParam String input) {
        return ResponseEntity.ok(calculatorService.calculate(input));
    }
}