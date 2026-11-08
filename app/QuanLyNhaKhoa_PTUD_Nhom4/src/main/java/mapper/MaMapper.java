package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaMapper implements RowMapper<String> {

    private final String column;

    public MaMapper(String column) {
        this.column = column;
    }

    @Override
    public String mapper(ResultSet rs) throws SQLException {
        String value = rs.getString(column);
        if (value == null) {
            throw new SQLException("Cột " + column + " bị NULL");
        }
        return value;
    }
}
