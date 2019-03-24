import scala.collection.mutable.ArrayBuffer

object MutableCollectionsInvariant extends App {

  // 19.4 Make mutable collections Invariant

  // when creating a collection of elements that can be mutated, its generic type
  // param should be declare as invariant [A]
  // ex. Array, ArrayBuffer

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

  // When creating an ArrayBuffer[Dog], can add both Dog and SuperDog since SuperDog extends Dog

  val dogs = ArrayBuffer[Dog]()
  dogs += hugo // Dog
  dogs += hugoSuper // SuperDog

  def makeDogSpeak(dogs: ArrayBuffer[Dog]) = {
    dogs.foreach(_.speak)
  }

  // makeDogSpeak will be fine if it is just Dogs in the array
  val justDogs = ArrayBuffer[Dog]()
  justDogs += new Dog("jolly")
  justDogs += new Dog("sam")
  makeDogSpeak(justDogs)

  // But cannot compile if it is array of other type
  val superDogs = ArrayBuffer[SuperDog]()
  superDogs += hugoSuper
  //makeDogSpeak(superDogs) // doesn't compile
  makeDogSpeak(dogs)
}