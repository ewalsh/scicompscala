import java.sql.DriverManager
import java.sql.Connection

object JdbcPostgresql {
    def main(args: Array[String]) {
        var c: Connection = null
        try {
            Class.forName("postgresql.postgresql.9.1-901-1jdbc4")
            c = DriverManager.getConnection("jdbc:postgresql:hmda:postgres")
        } catch {
            case e: Throwable => e.printStackTrace()
        }
    }
}
