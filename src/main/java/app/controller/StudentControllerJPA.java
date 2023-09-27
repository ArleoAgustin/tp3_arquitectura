package app.controller;

import app.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentControllerJPA {



    @Qualifier("studentRepository")
    @Autowired
    private final PersonaRepository repository;
}
