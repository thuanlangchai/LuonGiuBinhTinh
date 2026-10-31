package repository;

import java.util.List;
import mapper.RowMapper;


public interface Generic<T> {
    List<T> findAll(String sql, RowMapper<T> mapper, Object... parameters);
    Integer add(String sql, Object... parameters);
    void update(String sql, Object... parameters);
    void delete(String sql, Object... parameters);  
}
