package org.example.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/live")
public class LiveAuthController {

    @PostMapping("/authlive")
    public String authPushStream(@RequestParam("name") String name,
                                 @RequestParam("pass") String pass,
                                 HttpServletResponse httpServletResponse) {

        if(name.equals("admin") && pass.equals("123456")) {

            return "ok";

        } else {

            httpServletResponse.setStatus(500);

            return "not ok";

        }

    }


    @GetMapping("/test")
    public String test() {
        return "Hello, Spring Cloud";
    }

}
