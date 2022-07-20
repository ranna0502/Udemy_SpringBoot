package com.example.demo.service;

import com.example.demo.entity.Survey;
import com.example.demo.repository.SurveyDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

  private final SurveyDao dao;

  SurveyServiceImpl(SurveyDao dao) {
    this.dao = dao;
  }

  @Override
  public void save(Survey survey) {
    // hands-on
    dao.insertSurvey(survey);
  }

  @Override
  public List<Survey> getAll() {
    if (dao.getAll().isEmpty()) {
      throw new InquiryNotFoundException("SQL error");
    }
    return dao.getAll();
  }
}
