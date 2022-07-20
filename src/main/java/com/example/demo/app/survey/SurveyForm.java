package com.example.demo.app.survey;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class SurveyForm {

  /*Add parameters(0~150) 引数を追加(0~150)*/
  @Range(min = 0, max = 150, message = "150歳以上は設定できません")
  @NotNull
  private int age;

  /*Add parameters(1~5) 引数を追加(1~5)*/
  @Range(min = 1, max = 5, message = "1~5で選択してください")
  @NotNull
  private int satisfaction;

  /*Add parameters(200 characters or less) 引数を追加(200文字以内)*/
  @Length(min = 0, max = 200, message = "200文字以内で入力してください")
  @NotNull
  private String comment;

  public SurveyForm() {}

  public SurveyForm(int age, int satisfaction, String comment) {
    this.age = age;
    this.satisfaction = satisfaction;
    this.comment = comment;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getSatisfaction() {
    return satisfaction;
  }

  public void setSatisfaction(int satisfaction) {
    this.satisfaction = satisfaction;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
