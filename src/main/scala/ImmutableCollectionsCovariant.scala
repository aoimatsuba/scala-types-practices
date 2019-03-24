object ImmutableCollectionsCovariant extends App {

  // 19.5 Make Immutable collections covariant

  // For those collections that are immutable,
  // it will be much more flexible if those are declare that the type parameters covariant.
  // ex. List, Vector, Seq

  trait Animal {
    def speak
  }

  class Dog(var name: String) extends Animal {
    def speak = println("woof")
  }

  class SuperDog(name: String) extends Dog(name) {
    def useSuperPower = println("Super woof!")
  }

  val hugo = new Dog("Hugo")
  val hugoSuper = new SuperDog("super hugo")
  val giraffe = new SuperDog("super giraffe")

  def makeDogSpeak(dogs: Seq[Dog]) = {
    dogs.foreach(_.speak)
  }

  // This works
  val justDogs = Seq(new Dog("jolly"), new Dog("sam"))
  makeDogSpeak(justDogs)

  // This works too because Seq is immutable and defined with a covariant param type
  val superDogs = Seq(new SuperDog("superHugo"))
  makeDogSpeak(superDogs) // doesn't compile

  // Example with created immutable container class
  def makeDogsInContainerSpeak(dogsInContainer: Container[Dog]) = {
    dogsInContainer.elem.speak
  }

  // This of course works
  val containerDogs = new Container(new Dog("pochi"))
  makeDogsInContainerSpeak(containerDogs)
  // This also works
  val containerSuperDogs = new Container(new SuperDog("POCHI"))
  makeDogsInContainerSpeak(containerSuperDogs)
}

class Container[+A](val elem: A)