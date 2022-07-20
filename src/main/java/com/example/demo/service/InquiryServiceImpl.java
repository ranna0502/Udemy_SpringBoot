package com.example.demo.service;

import com.example.demo.entity.Inquiry;
import com.example.demo.repository.InquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Add an annotation here
 */
@Service
public class InquiryServiceImpl implements InquiryService {

  //	インターフェイス名の型にすること
  private final InquiryDao dao;

  @Autowired
  public InquiryServiceImpl(InquiryDao dao) {
    this.dao = dao;
  }

  @Override
  public void save(Inquiry inquiry) {
    // hands-on
    dao.insertInquiry(inquiry);
  }

  //  This method is used in the latter chapter
  //	@Override
  //	public void update(Inquiry inquiry) {
  //
  //		//return dao.updateInquiry(inquiry);
  //		if(dao.updateInquiry(inquiry) == 0) {
  //			throw new InquiryNotFoundException("can't find the same ID");
  //		}
  //	}

  @Override
  public List<Inquiry> getAll() {

    // hands-on
    return dao.getAll();
  }
}
