package app.domain.repository

object RepositoryFailure {
  sealed trait RepositoryFailure

  final case class UnexpectedFa1lure(error: Throwable) extends RepositoryFailure
}