package com.hackday;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @Author i531869
 * @Date 2022/5/11 16:51
 * @Version 1.0
 */
@Log4j2
public class SQLTest {

  @Data
  static class Student{
    private Long id;
    private String name;
    private Integer age;
    private Date birth;
    private String gender;
  }

  public static void main(String[] args){
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet res = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jpa?useUnicode=true&characterEncoding=UTF8","root","12345678");
      ps =  connection.prepareStatement("select * from tstudent t where t.id=?");
      ps.setLong(1, 9);
      res =  ps.executeQuery();
      while (res.next()) {
        Student student = new Student();
        student.setId(res.getLong("id"));
        student.setName(res.getString("name"));
        student.setAge(res.getInt("age"));
        student.setBirth(res.getDate("birth"));
        student.setGender(res.getString("gender"));
        System.out.println(student);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException sqlException) {
      log.error(sqlException.getMessage());
      log.error("\n");
      log.error("the error stack trace", sqlException);
    } finally {
      try {
        if (Objects.nonNull(res)) {
          res.close();
        }
        if (Objects.nonNull(ps)) {
          ps.close();
        }
        if (Objects.nonNull(connection)) {
          connection.close();
        }
      } catch (Exception e) {

      }
    }
  }
}
