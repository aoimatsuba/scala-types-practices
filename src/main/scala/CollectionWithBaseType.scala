import CollectionWithBaseType.{CrewMember, StarfleetTrained}

import scala.collection.mutable.ArrayBuffer

object CollectionWithBaseType extends App {

  // 19.6 Create a collection whose elements are all of some base type

  trait CrewMember
  class Officer extends CrewMember
  class RedShirt extends CrewMember

  trait Captain
  trait FirstOfficer
  trait ShipsDoctor
  trait StarfleetTrained


  val cap = new Officer with Captain
  val firstOfficer = new Officer with FirstOfficer
  val doc = new Officer with ShipsDoctor
  val red = new RedShirt
  val redSpecial = new RedShirt with StarfleetTrained

  // Now create a collection of all the officers
  val officers = new Crew[Officer]()
  officers += cap
  officers += firstOfficer
  officers += doc

  val redshirts = new Crew[RedShirt]()
  redshirts += red

  val specialReds = new CrewSpecial[RedShirt with StarfleetTrained]()
  specialReds += redSpecial // this works
  //specialReds += red  // this doesn't because red doesn't have StarfleetTrained
}

// all the crew needs to be subclass of CrewMember (which means either Officer or RedShirt)
// This is "upper bound"
class Crew[A <: CrewMember] extends ArrayBuffer[A]

// can also limit to object that has certain type of trait
class CrewSpecial[A <: CrewMember with StarfleetTrained] extends ArrayBuffer[A]