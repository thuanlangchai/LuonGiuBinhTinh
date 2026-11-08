package database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class Db {

    private static final HikariDataSource DATA_SOURCE = openPool();

    private Db() {
    }

    public static Connection open() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    private static HikariDataSource openPool() {
        Properties properties = new Properties();
        try (InputStream in = Db.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (in == null) {
                throw new IllegalStateException("Không tìm thấy database.properties trên classpath");
            }
            properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Không đọc được database.properties", e);
        }
        String password = System.getenv("DB_PASSWORD");
        if (password == null || password.isBlank()) {
            password = properties.getProperty("jdbc.password");
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUsername(properties.getProperty("jdbc.username"));
        config.setPassword(password);
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("jdbc.pool.size", "8")));
        config.setPoolName("QuanLyNhaKhoa");
        config.setAutoCommit(true);
        return new HikariDataSource(config);
    }
}
