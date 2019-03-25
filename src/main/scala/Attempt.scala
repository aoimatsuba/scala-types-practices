final case class Succeeded[A](value: A) extends Attempt[A]
final case class Failed[A](exception: Throwable) extends Attempt[A]

sealed class Attempt[A] // sealed class: only can be extended in the same source file as it is declared

// companion object
object Attempt {

  def apply[A](func: => A): Attempt[A] = {
    try {
      val result = func
      Succeeded(result)
    } catch {
      case e: Exception => Failed(e)
    }
  }

  // now it is better to have it have getOrElse method that give information from the result
  // whether that result happens to be a type of Succeeded or Failed
}

final case class Succeeded2[A](result: A) extends Attempt2[A] {
  isSuccess = false
  def get= result
}
final case class Failed2[A](exception: Throwable) extends Attempt2[A] {
  isSuccess = false
  def get: A = throw exception
}


sealed abstract class Attempt2[A] {
  // lower bound: type B is what user supplies. and is presumably a substitute for A
  // type param B is a super type of A
  def getOrElse[B >: A](default: => B): B = if (isSuccess) get else default
  var isSuccess = false
  def get: A
}

