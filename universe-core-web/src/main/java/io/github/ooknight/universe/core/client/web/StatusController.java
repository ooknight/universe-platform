package io.github.ooknight.universe.core.client.web;

import static io.github.ooknight.universe.support.utils.COMBINE.x;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public String status() {
        return "OK, " + x.datetime.now();
    }
}
