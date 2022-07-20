package com.example.demo.app.survey;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Add annotations here
 */
@Controller
@RequestMapping("/survey")
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @GetMapping
  public String index(Model model) {

    // hands-on
    List<Survey> list = surveyService.getAll();

    model.addAttribute("surveyList", list);
    model.addAttribute("title", "Index Form");
    return "survey/index";
  }

  @GetMapping("/form")
  public String form(
      SurveyForm surveyForm, Model model, @ModelAttribute("complete") String complete) {

    // hands-on
    model.addAttribute("title", "SurveyForm");
    return "survey/form";
  }

  @PostMapping("/form")
  public String formGoBack(SurveyForm surveyForm, Model model) {

    // hands-on
    model.addAttribute("title", "SurveyForm");
    return "survey/form";
  }

  @PostMapping("/confirm")
  public String confirm(@Validated SurveyForm surveyForm, BindingResult result, Model model) {

    // hands-on
    if (result.hasErrors()) {
      model.addAttribute("title", "SurveyForm");
      return "survey/form";
    }
    model.addAttribute("title", "Confirm Page");
    return "survey/confirm";
  }

  @PostMapping("/complete")
  public String complete(
      @Validated SurveyForm surveyForm,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {

    // hands-on
    if (result.hasErrors()) {
      model.addAttribute("title", "SurveyForm");
      return "survey/form";
    }
    Survey survey = new Survey();
    survey.setAge(surveyForm.getAge());
    survey.setSatisfaction(surveyForm.getSatisfaction());
    survey.setComment(surveyForm.getComment());
    survey.setCreated(LocalDateTime.now());

    surveyService.save(survey);
    redirectAttributes.addFlashAttribute("complete", "更新が完了しました！");
    return "redirect:/survey/form";
  }
}
