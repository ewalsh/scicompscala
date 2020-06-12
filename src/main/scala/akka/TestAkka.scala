import akka.actor.Actor 
import akka.actor.ActorSystem 
import akka.actor.Props
import akka.actor.ActorRef

class PingActor(pong: ActorRef) extends Actor {
  def receive: Actor.Receive = {
    case "pong" => {
      println("ping")
      pong ! "ping"
    }
  }
}

class PongActor extends Actor {
  def receive: Actor.Receive = {
    case "ping" => {
      println("pong")
      sender ! "pong"
    }
  }
}

object TestingAkka {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("PingPongSystem")
    val pongActor = system.actorOf(Props[PongActor], name = "pongactor")
    val pingActor = system.actorOf(Props(new PingActor(pongActor)), name = "pingactor")
    pingActor ! "pong"
  }
  
}