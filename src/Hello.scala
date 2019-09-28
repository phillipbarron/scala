import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object Hello extends App {
  print(s"Number of cores: ${Runtime.getRuntime().availableProcessors()}\n")
  def printSomething(index: Int) : Future[Unit] = {
    Await.result(Future {
      Thread.sleep(10)
      println(s"the index is $index, we are using thread ${Thread.currentThread().getId}")
    }, Duration.Inf)
  }
  for(i <- 0 to 10) {
    printSomething(i)
  }
}
