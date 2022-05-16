package app.domain.api

object Response {
  sealed trait Response

  final case class PersonAlreadyExistsResponse(id: Int) extends Response

  final case class InvalidInputResponse(firstName: String, lastName: String) extends Response

  final case class PersonNotFoundResponse(id: Int)
}