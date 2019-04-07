package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class wordladder {

    @RequestMapping(value = {"/", "/index"})
    public String say() {
        return "<input id=\"input1\" placeholder=\"source\"></input>" +
                "<input id=\"input2\" placeholder=\"target\"></input>" +
                "<script>" +
                "var value1=document.getElementById('input1').value" +
                "var value2=document.getElementById('input2').value" +
                "</script>" +
                "<a href=\"localhost:8080/wordladder?source=value1&target=value2\">" +
                "<button>search</button>" +
                "</a>";

    }

    @RequestMapping("/wordladder")
    public String say(@RequestParam(value = "source", defaultValue = "source") String source,
                      @RequestParam(value = "target", defaultValue = "target") String target) throws FileNotFoundException {
        new ladder();
        return ladder.ladder(source, target);
    }

}