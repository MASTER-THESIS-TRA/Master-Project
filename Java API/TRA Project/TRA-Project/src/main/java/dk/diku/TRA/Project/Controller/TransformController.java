package dk.diku.TRA.Project.controller;

import dk.diku.TRA.Project.Classes.Transformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transform")
public class TransformController {

    @CrossOrigin
    @PostMapping(path = "createTransform")
    public Transformation CreateTransformation() {
        return null;
    }
}
