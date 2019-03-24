object DuckTyping extends App {

  // 19.3 Using duck typing: structural types

  // This means the function require the object type param have a speak method
  // <: is upper bound syntax which require object A to be subtype of
  // object that has method speak()
  def callSpeak[A <: { def speak(): Unit}](obj: A): Unit = {
    obj.speak()
  }

  callSpeak(new Dog)
  callSpeak(new Cat)
}

class Dog {
  def speak() = println("Woof!")
}

class Cat {
  def speak() = println("Meowwww!!")
}