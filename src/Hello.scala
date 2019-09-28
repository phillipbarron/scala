import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Hello extends App {
  def printSomething(index: Int) : Future[Unit] = {
    Future {
      Thread.sleep(500)
      println(s"the index is $index")
    }
  }
  for(i <- 0 to 10) {
    println(s"this is index $i")
    printSomething(i)
  }
}
