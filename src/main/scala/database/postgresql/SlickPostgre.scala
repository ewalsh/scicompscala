import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global

object SlickPostgre {
    def createPlanet: DBIO[Int] =
        sqlu"""CREATE TABLE planet(
            ID INT PRIMARY KEY NOT NULL,
            NAME TEXT NOT NULL,
            MASS REAL NOT NULL,
            PERIOD REAL NOT NULL
        )
        """

    def main(args: Array[String]) {
        val db = Database.forURL("postgres://postgres@localhost:54320/planets",
            driver="org.postgresql.Driver"
        )
        try {
            db.run(createPlanet)
        } finally db.close()
    }

    def populatePlanet: DBIO[Unit] = DBIO.seq(
        sqlu"""INSERT INTO planet VALUES (0, "Mercury", 3.3011E23, 0.240864)""",
        sqlu"""INSERT INTO planet VALUES (1, "Venus", 4.8675E24, 0.615)""",
        sqlu"""INSERT INTO planet VALUES (2, "Earth", 5.97237E24, 1)""",
        sqlu"""INSERT INTO planet VALUES (3, "Mars", 6.4171E23, 1.881)""",
        sqlu"""INSERT INTO planet VALUES (4, "Jupiter", 1.8986E27, 11.86)""",
        sqlu"""INSERT INTO planet VALUES (5, "Saturn", 5.6836E26, 29.46)""",
        sqlu"""INSERT INTO planet VALUES (6, "Uranus", 8.6810E25, 84.01)""",
        sqlu"""INSERT INTO planet VALUES (7, "Neptune", 1.0243E26, 164.8)"""
    )
}
