object MethodWithGenericType extends App {

  // 19.2 create a method that takes generic type

  // not really necessary to specify generic return type
  def randomElement[A](elements: Seq[A]): A = {
    val randomNum = util.Random.nextInt(elements.length)
    println(elements(randomNum))
    elements(randomNum)
  }
  // with generic types, the method can be called with various types
  randomElement(Seq("Aoi", "Russell", "Izuki", "Kyupio"))
  randomElement(List(1, 4, 7, 34, 23, 7))
}