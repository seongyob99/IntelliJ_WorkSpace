package com.busanit501.springpratice.controller;


import com.busanit501.springpratice.dto.FoodDTO;
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
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "hello2";
    }

    @GetMapping("/ex1")
    // localhost:8080/ex1?foodname=ramen&price=8000
    public void ex1(String foodname, int price) {
        log.info("ex1 name:" + foodname);
        log.info("ex1 age:" + price);

    }

    @GetMapping("/ex2")
    // localhost:8080/ex2?name=ramen&age=7000
    public void ex2(@RequestParam(name = "name", defaultValue = "food") String foodname,
                    @RequestParam(name = "age", defaultValue = "price") int price) {
        log.info("ex2 foodname:" + foodname);
        log.info("ex2 price:" + price);
    }

    @GetMapping("/ex3")
    // localhost:8080/ex3?dueDate=2024-12-03
    // 대책은, 미리, 형이 다른 문자열에 대해서,
    // LocalDate <-> String  , 미리 만들어두기.
    // 시스템에 , 빈으로 등록해서,
    // LocalDate가 문자열로 전송되어도, 시스템이 자동으로  LocalDate 형 변환해줌.
    public void ex3(LocalDate dueDate) {
        log.info("ex3 dueDate:" + dueDate);
    }

    // 앞에 예제들은 , 웹 -> 서버, 전달해서, 서버에서 확인.
    // 방향이 반대. 서버 -> 웹 전달, 화면에 데이터 탑재 전달.
    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("ex4 Model 서버에서 -> 데이터 전달하기. :");
        model.addAttribute("msg"," <script>\n" +
                "                    alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" +
                "                </script>");
    }

    @GetMapping("/ex5")
    // 웹브라우저에서 TodoDTO 멤버 타입 형식으로 받고,
    // 다시 서버 -> 웹브라우저로 전달 하는 방법.
    // 파라미터 , TodoDTO todoDTO 선언되어있으면,
    // 화면에서 , 그대로 사용가능., 예시) ${foodDTO}
    // localhost:8080/ex5?title=asy&writer=안성엽
    // 모델 Model model 사용안해도, 스프링 프레임워크에서 자동으로 화면에서 사용가능.
    public void ex5(FoodDTO foodDTO , Model model) {
        log.info("ex5  :");
        log.info("ex5 : " + foodDTO);
    }

    //리다이렉트 시, 데이터 전달
    // 1) 키, 값의 형태로 데이터 전달,
    // 2) 일회용으로 데이터 전달 예시,
    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes) {
        log.info("ex6  :");
        // 키, 값의 구조로 데이터 전달.
        // 서버 -> 웹 브라우저로 전달,  화면에 데이터를 탑재해서 전달.
        // 주의사항, 쿼리스트링으로 보냄. -> ex7에서 받는 변수가 필요함.
        redirectAttributes.addAttribute("msg","foodtest");
        // 화면에서 일회용으로 바로 사용가능.
        redirectAttributes.addFlashAttribute("msg2","1회용으로 사용하는 데이터");

        //
        return "redirect:/ex7";
    }

    @GetMapping("/ex7")
    public void ex7(String msg, Model model) {
        log.info("ex7");
        log.info("msg:" + msg);
        model.addAttribute("msg",msg);
    }
}
