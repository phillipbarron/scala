import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Hello extends App {
  print(s"Number of cores: ${Runtime.getRuntime().availableProcessors()}\n")
  def printSomething(index: Int) : Future[Unit] = {
    val startTime = System.nanoTime
    Future {
      Thread.sleep(100)
      println(s"the index is $index, we are using thread ${Thread.currentThread().getId}\nthe time elapsed for this call is ${(System.nanoTime() - startTime) / 1e9d}")
    }
  }
  var fruits = new ListBuffer[Future[Unit]]()
  for(i <- 0 to 10) {
    fruits += printSomething(i)
  }
  fruits.map(f => Await.result(f, Duration.Inf))
}
