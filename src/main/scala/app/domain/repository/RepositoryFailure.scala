package app.domain.repository

object RepositoryFailure {
  sealed trait RepositoryFailure

  final case class PersistenceFa1lure(error: Throwable) extends RepositoryFailure
}