import BehaviorToClosedModel.Doggo

object BehaviorToClosedModel extends App {

  // 19.7 Selectively adding new behavior to a closed model

  // Existing closed model
  trait Animal
  final case class Doggo(name: String) extends Animal
  final case class Catto(name: String) extends Animal

  import Humanish.HumanLike

  def makeHumanLikeThigSpeak[A](animal: A)(implicit humanLike: HumanLike[A]) = {
    humanLike.speak(animal)
  }

  // Since HumanLike is implemented for a Dog, it will work for Dog but not cat
  //makeHumanLikeThigSpeak(Doggo("Hugo"))
  // makeHumanLikeThigSpeak(Catto("Noonoo")) // Doesn't compile
}

object Humanish {
  // defines an abstract method
  trait HumanLike[A] {
    def speak(speaker: A): Unit
  }

  // companion object
  object HumanLike {
    // implement the behavior for each desired type
    implicit object DogIsHumanLike extends HumanLike[Doggo] { // here, desired type is Doggo
       def speak(dog: Doggo): Unit = println(s"Dog speaking! I am ${dog.name}")
    }
  }
}