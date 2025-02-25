package com.beyond.basic.b2_board.repository;

import com.beyond.basic.b2_board.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.*;
import java.util.*;
//
//@Repository
//public class MemberJdbcRepository {

//    Datasource는 DB와 JDBC에서 사용하는 DB연결 드라이버 객체
//    application.yml에서 설정한 DB정보가 dataSource로 주입
//    @Autowired
//    private DataSource dataSource;
//
//    public List<Member> findAll(){
//        try{
//            List<Member> memberList = new ArrayList<>();
//            Connection connection = dataSource.getConnection();
//            String query = "select * from member";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
////            while 안에 rs.next를 넣는 이유는 맨처음 rs.next에는 컬럼명이 나오기 때문.
//            while(rs.next()){
//                Long id = rs.getLong("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                Member member = new Member(id,name,email,password);
//                memberList.add(member);
//            }
//            return memberList;
//        }catch (SQLException e){
////            checked예외는 트랜잭션 처리가 되지 않으므로, unchecked로 바꿔 service 레이어 예외 전달
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void save(Member member){
//        try{
//            Connection connection = dataSource.getConnection();
//            String sql = "insert into member(name, email, password) values(?,?,?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1,member.getName());
//            ps.setString(2, member.getEmail());
//            ps.setString(3, member.getPassword());
//
//            ps.executeUpdate();// 추가, 수정의 경우 excuteUpdate. 조회의 경우 excuteQuery
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Optional<Member> findByEmail(String inputEmail){
//        Member member = null;
//        try{
//            Connection connection = dataSource.getConnection();
//            String sql = "select * from member where email=?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1,inputEmail);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                Long id = rs.getLong("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                member = new Member(id,name,email,password);
//            }
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//        return Optional.ofNullable(member);
//    }
//
//    public Optional<Member> findById(Long inputId){
//        Member member = null;
//        try{
//            Connection connection = dataSource.getConnection();
//            String sql = "select * from member where id=?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setLong(1,inputId);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            Long id = rs.getLong("id");
//            String name = rs.getString("name");
//            String email = rs.getString("email");
//            String password = rs.getString("password");
//            member = new Member(id,name,email,password);
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//        return Optional.ofNullable(member);
//    }
//
//}
