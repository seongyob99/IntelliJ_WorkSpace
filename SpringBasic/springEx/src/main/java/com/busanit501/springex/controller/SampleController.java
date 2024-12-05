package com.busanit501.springex.controller;

import com.busanit501.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }


    @GetMapping("/hello2")
    public String hello2() {
        log.info("hello2");
        return "helloTest";
    }

    @GetMapping("/ex1")
    // 파라미터 수집 여부만 확인, 뷰없이, 콘솔에서 확인.
    // localhost:8080/ex1?name=asy&age=25
    public void ex1(String name, int age) {
        log.info("ex1 name:" + name);
        log.info("ex1 age:" + age);
    }

    @GetMapping("/ex2")
    // 파라미터 수집 여부만 확인, 뷰없이, 콘솔에서 확인.
    // localhost:8080/ex2?name=lsy&age=41
    public void ex2(@RequestParam(name = "name", defaultValue = "LSY") String name,
                    @RequestParam(name = "age", defaultValue = "30")int age) {

        log.info("ex2 name:" + name);
        log.info("ex2 age:" + age);
    }

    @GetMapping("/ex3")
    // 웹브라우저에서 넘어온 데이터 타입은 문자열이어서,
    // 받을 때 타입 불일치 오류 확인.
    public void ex3(LocalDate dueDate) {
        log.info("ex3 dueDate:" + dueDate);
    }

    @GetMapping("/ex4")
    // 웹브라우저에서 넘어온 데이터 타입은 문자열이어서,
    // 받을 때 타입 불일치 오류 확인.
    public void ex4(Model model) {
        log.info("ex4 Model 서버에서 -> 데이터 전달하기. :");
        model.addAttribute("msg"," <script>\n" +
                "                    alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" +
                "                </script>");
    }

    @GetMapping("/ex5")
    // localhost:8080/ex5?title=asy&writer=안성엽
    public void ex5(TodoDTO todoDTO, Model model) {
        log.info("ex5 : ");
        log.info("ex5 : " + todoDTO);
    }

    //리다이렉트 시 , 데이터 전달
    // 1) 키, 값의 형태로 데이터 전달,
    // 2) 일회용으로 데이터 전달 예시,
    @GetMapping("/ex6")
    // localhost:8080/ex5?title=asy&writer=안성엽
    public String ex6(RedirectAttributes redirectAttributes) {
        log.info("ex6 : ");
        //키, 값의 구조로 데이터 전달.
        //서버 -> 웹 브라우저로 전달, 화면에 데이터 탑재해서 전달.
        redirectAttributes.addAttribute("msg", "test");
        redirectAttributes.addAttribute("msg2", "1회용으로 사용");

        //
        return "redirect:/ex7";
    }

        @GetMapping("/ex7")
        public void ex7(String msg, Model model) {
            log.info("ex7");
            log.info("msg : " + msg);
            model.addAttribute("msg", msg);
        }

    // 타입 불일치로 강제로 예외 발생 하는 예시
    @GetMapping("/ex8")
    public void ex8(String name, int age) {
        log.info("ex8 name :" + name);
        log.info("ex8 age :" + age);

    }
    }
