package app.repository
import app.domain.Person
import app.domain.repository.RepositoryFailure
import app.domain.repository.RepositoryFailure.UnexpectedFa1lure
import zio.IO

object PersonMapRepository extends Repository {

  private var db: Map[Int, Person] = Map()

  override def save(person: Person): IO[RepositoryFailure.RepositoryFailure, Person] =
    IO {
      db += (person.id -> person)
      person
    }.mapError(UnexpectedFa1lure)

  override def get(id: Int): IO[RepositoryFailure.RepositoryFailure, Option[Person]] =
    IO {
      db.get(id)
    }.mapError(UnexpectedFa1lure)

  override def getAll(): IO[RepositoryFailure.RepositoryFailure, Seq[Person]] =
    IO {
      db.values.toSeq
    }.mapError(UnexpectedFa1lure)

  override def delete(id: Int): IO[RepositoryFailure.RepositoryFailure, Unit] =
    IO {
      db -= id
      ()
    }.mapError(UnexpectedFa1lure)
}
