package repository.impl;

import repository.Generic;
import java.util.List;
import mapper.RowMapper;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class AbstractDAO<T> implements Generic<T> {

    @Override
    public List<T> findAll(String sql, RowMapper<T> mapper, Object... parameters) {
        List<T> listT = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stm = conn.prepareStatement(sql);
            setParameters(stm, parameters);
            rs = stm.executeQuery();
            while (rs.next()) {
                listT.add(mapper.mapper(rs));
            }
            return listT;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
                stm.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    @Override
    public Integer add(String sql, Object... parameters) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameters(stm, parameters);
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            conn.commit();
            return id;
        } catch (SQLException e) {
            try {
                e.printStackTrace();
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } finally {
            try {
                conn.close();
                stm.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    ;

    @Override
    public void update(String sql, Object... parameters) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = getConnection();
            stm = conn.prepareStatement(sql);
            setParameters(stm, parameters);
            stm.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
                stm.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    ;

    @Override
    public void delete(String sql, Object... parameters) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = getConnection();
            stm = conn.prepareStatement(sql);
            setParameters(stm, parameters);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conn.close();
                stm.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    ;
    
    public void setParameters(PreparedStatement stm, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] == null) {
                stm.setObject(i + 1, null);
            } else if (parameters[i] instanceof String string) {
                stm.setString(i + 1, string);
            } else if (parameters[i] instanceof Integer integer) {
                stm.setInt(i + 1, integer);
            } else if (parameters[i] instanceof LocalDate date) {
                stm.setDate(i + 1, Date.valueOf(date));
            } else if (parameters[i] instanceof Float f) {
                stm.setFloat(i + 1, f);
            } else if (parameters[i] instanceof Boolean b) {
                stm.setBoolean(i + 1, b);
            } else if (parameters[i] instanceof LocalTime time) {
                stm.setTime(i + 1, Time.valueOf(time));
            } else if (parameters[i] instanceof Double d) {
                stm.setDouble(i + 1, d);
            } else if (parameters[i] instanceof LocalDateTime dateTime) {
                stm.setTimestamp(i + 1, Timestamp.valueOf(dateTime));
            } else if (parameters[i] instanceof Long l) {
                stm.setLong(i + 1, l);
            }
        }
    }

    

    public static Connection getConnection() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaKhoa;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "langchai297";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
