object ClassWithGenericTypes extends App {

  // 19.1 Create classes that use generic type
  // invariant/covariant/contravariant
  val ints = new LinkedList[Int]()
  ints.add(3)
  ints.add(6)
  ints.add(1)
  ints.add(8)

  ints.printAll()

  // works for other types too
  val chars = new LinkedList[Char]
  chars.add('a')
  chars.add('t')
  chars.add('e')
  chars.add('b')

  chars.printAll()

  //
  trait Animal
  class DogParent extends Animal
  class Dog extends DogParent

  val dogParents = new LinkedList[DogParent]

  val hugo = new Dog
  val rupert = new DogParent

  dogParents.add(hugo)
  dogParents.add(rupert)

  // But this get tricky when you have generic typed class method param
  def printDogType(dogs: LinkedList[DogParent]) = {
    dogs.printAll()
  }

  // This compiles
  val parents = new LinkedList[DogParent]()
  val sally = new DogParent
  val wolly = new DogParent
  parents.add(sally)
  parents.add(wolly)
  printDogType(parents)

  // But this doesn't
  // LinkedList elements are mutable
  val dogs = new LinkedList[Dog]
  dogs.add(hugo)
  //printDogType(dogs)

  /*
  Error:(52, 16) type mismatch;
  found   : LinkedList[ClassWithGenericTypes.Dog]
  required: LinkedList[ClassWithGenericTypes.DogParent]
  Note: ClassWithGenericTypes.Dog <: ClassWithGenericTypes.DogParent, but class LinkedList is invariant in type A.
  You may wish to define A as +A instead. (SLS 4.5)
  printDogType(dogs)
  */
}

class LinkedList[A] {
  private class Node[A] (elem: A) {
    var next: Node[A] = _
    override def toString: String = elem.toString
  }

  private var head: Node[A] = _

  def add(elem: A): Unit = {
    val n = new Node(elem)
    n.next = head
    head = n // newly added one become head
  }

  private def printNodes(n: Node[A]): Unit = {
    if (n != null) {
      print(n)
      printNodes(n.next) // recursively print
    }
  }

  def printAll() {printNodes(head)}
}

