package repository.impl;

import database.DataAccessException;
import database.Db;
import database.Transactions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import mapper.RowMapper;
import repository.Generic;

public abstract class AbstractDAO<T> implements Generic<T> {

    @Override
    public List<T> findAll(String sql, RowMapper<T> mapper, Object... parameters) {
        return query(sql, mapper, parameters);
    }

    protected <R> List<R> query(String sql, RowMapper<R> mapper, Object... parameters) {
        Connection connection = null;
        boolean owned = false;
        try {
            Connection current = Transactions.current();
            if (current != null) {
                connection = current;
            } else {
                connection = Db.open();
                owned = true;
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                setParameters(statement, parameters);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<R> rows = new ArrayList<>();
                    while (resultSet.next()) {
                        R row = mapper.mapper(resultSet);
                        if (row == null) {
                            throw new SQLException("Mapper trả về null tại dòng " + (rows.size() + 1));
                        }
                        rows.add(row);
                    }
                    return rows;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Lỗi truy vấn dữ liệu", e);
        } finally {
            closeOwned(connection, owned);
        }
    }

    protected <R> R one(String sql, RowMapper<R> mapper, Object... parameters) {
        List<R> rows = query(sql, mapper, parameters);
        return rows.isEmpty() ? null : rows.get(0);
    }

    protected <R> List<R> queryIn(String sqlBeforeIn, List<String> ids, RowMapper<R> mapper) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        String sql = sqlBeforeIn + " IN (" + marks(ids.size()) + ")";

        return query(sql, mapper, ids.toArray());
    }

    protected long queryLong(String sql, Object... parameters) {
        Connection connection = null;
        boolean owned = false;
        try {
            Connection current = Transactions.current();
            if (current != null) {
                connection = current;
            } else {
                connection = Db.open();
                owned = true;
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                setParameters(statement, parameters);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (!resultSet.next()) {
                        return 0L;
                    }
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Lỗi truy vấn dữ liệu", e);
        } finally {
            closeOwned(connection, owned);
        }
    }

    @Override
    public Integer add(String sql, Object... parameters) {
        return execute(sql, parameters);
    }

    @Override
    public void update(String sql, Object... parameters) {
        execute(sql, parameters);
    }

    @Override
    public void delete(String sql, Object... parameters) {
        execute(sql, parameters);
    }

    protected int execute(String sql, Object... parameters) {
        Connection connection = null;
        boolean owned = false;
        try {
            Connection current = Transactions.current();
            if (current != null) {
                connection = current;
            } else {
                connection = Db.open();
                owned = true;
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                setParameters(statement, parameters);
                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Lỗi ghi dữ liệu", e);
        } finally {
            closeOwned(connection, owned);
        }
    }

    protected int executeIn(String sqlBeforeIn, List<String> ids, Object... leading) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }

        Object[] params = new Object[leading.length + ids.size()];

        System.arraycopy(leading, 0, params, 0, leading.length);

        for (int i = 0; i < ids.size(); i++) {
            params[leading.length + i] = ids.get(i);
        }

        String sql = sqlBeforeIn + " IN (" + marks(ids.size()) + ")";

        return execute(sql, params);
    }

    public void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        if (parameters == null) {
            return;
        }
        for (int i = 0; i < parameters.length; i++) {
            Object value = parameters[i];
            int index = i + 1;
            if (value == null) {
                statement.setObject(index, null);
            } else if (value instanceof String text) {
                statement.setString(index, text);
            } else if (value instanceof Integer number) {
                statement.setInt(index, number);
            } else if (value instanceof Long number) {
                statement.setLong(index, number);
            } else if (value instanceof LocalDate date) {
                statement.setDate(index, java.sql.Date.valueOf(date));
            } else if (value instanceof Float number) {
                statement.setFloat(index, number);
            } else if (value instanceof Boolean flag) {
                statement.setBoolean(index, flag);
            } else if (value instanceof LocalTime time) {
                statement.setTime(index, Time.valueOf(time));
            } else if (value instanceof Double number) {
                statement.setDouble(index, number);
            } else if (value instanceof LocalDateTime dateTime) {
                statement.setTimestamp(index, Timestamp.valueOf(dateTime));
            } else {
                statement.setObject(index, value);
            }
        }
    }

    private static String marks(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append('?');
        }
        return builder.toString();
    }

    private static void closeOwned(Connection connection, boolean owned) {
        if (!owned || connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
