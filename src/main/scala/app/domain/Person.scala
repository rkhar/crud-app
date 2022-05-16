package app.domain

final case class Person private(id: Int, firstName: String, lastName: String)

object Person {

  private var n = 0

  def apply(firstName: String, lastName: String): Option[Person] = {
    if (firstName.nonEmpty && lastName.nonEmpty) {
      n += 1
      Some(Person(n, firstName, lastName))
    } else None
  }
}
