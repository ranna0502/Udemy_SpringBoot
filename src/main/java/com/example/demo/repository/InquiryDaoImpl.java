package com.example.demo.repository;

import com.example.demo.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Add an annotation here
 */
@Repository
public class InquiryDaoImpl implements InquiryDao {

  //  DBの初期化
  private final JdbcTemplate jdbcTemplate;

  //  DIコンテナで生成されたデータを代入する
  @Autowired
  public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  //  １件ずつデータを保存する機能
  @Override
  public void insertInquiry(Inquiry inquiry) {
    // hands-on
    jdbcTemplate.update(
        "INSERT INTO inquiry(name,email,contents,created) VALUES(?,?,?,?)",
        inquiry.getName(),
        inquiry.getEmail(),
        inquiry.getContents(),
        inquiry.getCreated());
  }

  //  This method is used in the latter chapter
  //	@Override
  //	public int updateInquiry(Inquiry inquiry) {
  //		return jdbcTemplate.update("UPDATE inquiry SET name = ?, email = ?,contents = ? WHERE id = ?",
  //				 inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getId() );
  //	}

  //  全件取得し、リスト化する機能
  @Override
  public List<Inquiry> getAll() {

    // make SQL
    String sql = "SELECT id,name,email,contents,created FROM inquiry";
    List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
    List<Inquiry> list = new ArrayList<>();
    for (Map<String, Object> result : resultList) {
      Inquiry inquiry = new Inquiry();
      inquiry.setId((int) result.get("id"));
      inquiry.setName((String) result.get("name"));
      inquiry.setEmail((String) result.get("email"));
      inquiry.setContents((String) result.get("contents"));
      inquiry.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
      list.add(inquiry);
    }

    // Set the data form database into Inquiry instance

    return list;
  }
}
