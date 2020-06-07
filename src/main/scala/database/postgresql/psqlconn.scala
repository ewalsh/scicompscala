import java.sql.Driver
import java.sql.Connection

object JdbcPostgresql {
    def main(args: Array[String]) {
        var c: Connection = null
        try {
            Class.forName("postgresql.postgresql.9.1-901-1.jdbc4")
            c = DriverManager.getConnection("")
        }
    }
}
