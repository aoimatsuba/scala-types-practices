
object FunctionalityTypes extends App {

  // 19.8 Building Functionality with Types

  // Create timer method to get the time of running some algorithm
  def timer[A](blockOfCode: => A) = { // call-by-name syntax
    // return time of blockOf Code method is A
    val startTime = System.nanoTime
    val result = blockOfCode
    val stopTime = System.nanoTime
    val delta = stopTime - startTime
    (result, delta/1000000d)
  }

  val (result, time) = timer { println("Hello")}
  println(result + " " + time.toString)


}