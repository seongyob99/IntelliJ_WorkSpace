package com.busanit501.springpratice.controller;

import com.busanit501.springpratice.dto.FoodDTO;
import com.busanit501.springpratice.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller// 1)화면 2)데이터 제공.
@RequestMapping("/food")
//localhost:8080/food/

@Log4j2
@RequiredArgsConstructor
public class FoodController {

    @Autowired
    private FoodService foodService;

    // localhost:8080/food/list
    @RequestMapping("/list")
    public void list(Model model) {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함.");
        List<FoodDTO> list = foodService.getAll();
        log.info("FoodController list 데이터 유무 확인 :" + list);
        model.addAttribute("list", list);
    }

    // localhost:8080/food/register
    // 1) 글작성 폼, 화면 -> get
    // 2) 글작성 로직 처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("FoodController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + foodDTO);

        if(bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러 발생");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        foodService.register(foodDTO);

        return "redirect:/food/list";
    }

    @RequestMapping("/read")
    public void read(Long fno, Model model) {
        FoodDTO foodDTO = foodService.getOne(fno);
        model.addAttribute("foodDTO", foodDTO);

    }


    // 수정 1) 폼
    @RequestMapping("/update")
    public void update(Long fno, Model model) {
        FoodDTO foodDTO = foodService.getOne(fno);
        model.addAttribute("foodDTO", foodDTO);
    }

    // 수정 2) 로직 처리
    @PostMapping("/update")
    public String updateLogic(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update";
        }
        foodService.update(foodDTO);
        return "redirect:/food/list";
    }

    // 삭제
    @PostMapping("/delete")
    public String delete(Long fno) {
        foodService.delete(fno);
        return "redirect:/food/list";
    }

}