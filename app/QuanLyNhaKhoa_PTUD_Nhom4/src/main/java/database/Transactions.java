package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Transactions {

    private static final Logger LOG = Logger.getLogger(Transactions.class.getName());
    private static final ThreadLocal<Connection> CURRENT = new ThreadLocal<>();

    private Transactions() {
    }

    public static Connection current() {
        return CURRENT.get();
    }

    public static void run(SqlWork work) {
        if (CURRENT.get() != null) {
            invoke(work);
            return;
        }
        try (Connection connection = Db.open()) {
            connection.setAutoCommit(false);
            CURRENT.set(connection);
            try {
                invoke(work);
                connection.commit();
            } catch (RuntimeException e) {
                rollback(connection);
                throw e;
            } finally {
                CURRENT.remove();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Không mở được kết nối cơ sở dữ liệu", e);
        }
    }

    private static void invoke(SqlWork work) {
        try {
            work.run();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    private static void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Rollback thất bại", e);
        }
    }

    @FunctionalInterface
    public interface SqlWork {
        void run() throws Exception;
    }
}
