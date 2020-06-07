import argonaut._, Argonaut._

case class Planet(name: String, satellites: List[String], mass: Float)
case class Planets(gasGiants: List[Planet], rockyPlanets: List[Planet])

object Planet {
    implicit def PlanetCodecJson: CodecJson[Planet] =
        casecodec3(Planet.apply, Planet.unapply)("name", "satellites", "mass")
}

object Planets {
    implicit def PlanetsCodecJson: CodecJson[Planets] =
        casecodec2(Planets.apply, Planets.unapply)("gasGiants", "rockyPlanets")
}

object ArgonautTest {
    def main(args: Array[String]){
        val rawText = scala.io.Source.fromFile("./data/planets.json").mkString
        val planets = rawText.decode[Planets]
        println(planets)
    }
}
