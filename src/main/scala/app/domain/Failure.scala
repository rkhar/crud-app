package app.domain

sealed trait Failure
final case class PersistenceFailure(error: Throwable) extends Failure

