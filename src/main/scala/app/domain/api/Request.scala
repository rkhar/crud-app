package app.domain.api

object Request {
  sealed trait Request

  case class CreatePersonRequest(firstName: String, lastName: String) extends Request

  case class UpdatePersonRequest(id: Int, firstName: String, lastName: String) extends Request
}