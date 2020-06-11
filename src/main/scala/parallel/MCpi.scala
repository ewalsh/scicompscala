import scala.util.Random

object PiSerial {
  def inside(x: Double, y: Double) = {
    x * x + y * y < 1.0
  }

  def main(args: Array[String]): Unit = {
    var c = 0.0
    val n = 100000000
    for(i <- 0 to n){
      val x = Random.nextDouble
      val y = Random.nextDouble
      if(inside(x, y)){
        c = c + 1.0
      }
    }
    println(4.0 * (c/n))
  }
}

object Accumulator {
  var c = 0.0
  def inc(): Unit = {
    this.synchronized {
      c = c + 1.0
    }
  }
}

class SamplingThread(n: Int) extends Runnable {
  def run(): Unit = {
    for(i <- 0 until n){
      val x = Random.nextDouble()
      val y = Random.nextDouble()
      if(x*x + y*y < 1.0){
        Accumulator.inc()
      }
    }
  }
}

object PiParallel{
  def main(n: Int, nproc: Int): Unit = {
    val threads = for(i <- 0 until nproc) yield {
      new Thread(new SamplingThread(n))
    }
    threads.foreach {
      (thread: Thread) => thread.start()
    }
    threads.foreach {
      (thread: Thread) => thread.join()
    }
    println(4.0*(Accumulator.c/(n*nproc)))
  }
}
