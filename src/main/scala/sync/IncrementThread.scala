object Incrementor {
  var a = 0
  def inc(): Unit = {
    this.synchronized{
      a = a + 1
    }
  }
}

class IncrementThread extends Runnable {
  def run(): Unit = {
    for(i <- 0 until 1000){
      Incrementor.inc()
    }
  }
}

object SyncThread {
  def main(args: Array[String]): Unit = {
    val threads = for(i <- 1 to 5) yield new Thread(new IncrementThread)
    for(thread <- threads) {
      thread.start()
    }
    for(thread <- threads){
      thread.join()
    }
    println(Incrementor.a)
  }
}
