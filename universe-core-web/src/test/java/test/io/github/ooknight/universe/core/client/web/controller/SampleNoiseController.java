package test.io.github.ooknight.universe.core.client.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleNoiseController {

    @GetMapping("/noise")
    public String demo() {
        return "this is noise";
    }
}
