package service;

import db.DBConn;
import dto.TelDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelBookSerivice implements CrudInterface {
    // Db 연결하기
    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;
    // select * from telbook > sql;

    @Override
    public int insertData(TelDto dto) {
        System.out.println("[TelBookSerivice.InsertData]");

        try {
            sql = "INSERT INTO telbook(name, age, address, phone, insertedDate) ";
            sql = sql + "VALUES(?, ?, ?, ?, ?) ";
            psmt = conn.prepareStatement(sql);

            // ? 각 자리를 Mapping 해 준다.
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setTimestamp(5, Timestamp.valueOf(dto.getInsertedDate()));

            // 쿼리 실행하기
            int result = psmt.executeUpdate();
            psmt.close();
            return result;


        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return 0;
    }

    @Override
    public int updateData(TelDto dto) {
        System.out.println("[TelBookSerivice.UpdateData]");
        int result = 0;
        try {
            sql = "UPDATE telBook SET ";
            sql = sql + "name = ?,";
            sql = sql + "age = ?,";
            sql = sql + "address = ?,";
            sql = sql + "phone = ?,";
            sql = sql + "updatedDate = ?";
            sql = sql + "WHERE id = ?,";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setTimestamp(5, Timestamp.valueOf(dto.getUpdatedDate()));
            psmt.setInt(6, dto.getId());
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteData(int id) {
        System.out.println("[TelBookSerivice.deleteData]");
        int result = 0;
        try {
            sql = "DELETE FROM telBook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<TelDto> getListAll() {
        System.out.println("[TelBookSerivice.getListAll]");
        // Db에서 select 한 결과를 담을 리스트 선언
        List<TelDto> dtoList = new ArrayList<>();
        ResultSet rs = null;
        // telbook > rs;
        // Object이기 때문에 값을 비어있게 만들어야 함

        try {
            sql = "SELECT * FROM telbook";
            psmt = conn.prepareStatement(sql);
            // SQL 구문 실행
            rs = psmt.executeQuery();

            // ResultSet에 들어온 레코드들을 하나씩 뽑아서
            // DtoList에 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                // 날짜가 null 인지 확인
                if (rs.getTimestamp("insertedDate") != null) {
                    dto.setInsertedDate(rs.getTimestamp("insertedDate").toLocalDateTime());
                } else {
                    dto.setUpdatedDate(null);
                }
                if (rs.getTimestamp("updatedDate") != null) {
                    dto.setUpdatedDate(rs.getTimestamp("updatedDate").toLocalDateTime());
                } else {
                    dto.setUpdatedDate(null);
                }

                // 리스트에 담기
                dtoList.add(dto);
            }
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return List.of();
    }

    @Override
    public TelDto findById(int id) {
        System.out.println("[TelBookSerivice.findById]");
        ResultSet rs = null;
        // 아이디를 받아서 해당 레코드를 읽어오는 작업
        try {
            sql = "SELECT id, name, age, address, phone " +
                    " FROM telBook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            // 레코드 셋의 자료를 while로 순회하면서 읽는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                return dto;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (TelDto) rs;
    }

    @Override
    public List<TelDto> searchList(String keyword) {
        System.out.println("[TelBookSerivice.searchList]");
        ResultSet rs = null;
        List<TelDto> dtoList = new ArrayList<>();
        try {
            sql = "SELECT id, name, age, address, phone";
            sql = sql + " FROM telBook ";
            sql = sql + " WHERE name like ? ";
            sql = sql + " ORDER BY name DESC ";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + keyword + "%");
            rs = psmt.executeQuery();
            // 돌면서 List<TelDto>에 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dtoList.add(dto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }





        return List.of();
    }
}
