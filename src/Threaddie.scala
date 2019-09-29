import java.util.concurrent.Executors

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration

object Threaddie extends App {
  val executionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(100))
  print(s"Number of cores: ${Runtime.getRuntime.availableProcessors()}\n")
  def printSomething(index: Int) : Future[Unit] = {
    val startTime = System.nanoTime
    Future {
      Thread.sleep(100)
      println(s"the index is $index, we are using thread ${Thread.currentThread().getId}\nthe time elapsed for this call is ${(System.nanoTime() - startTime) / 1e9d}")
    }(executionContext)
  }
  var allTheFutures = new ListBuffer[Future[Unit]]()
  for(i <- 0 to 10) {
    allTheFutures += printSomething(i)
  }
  allTheFutures.map(f => Await.result(f, Duration.Inf))
}
