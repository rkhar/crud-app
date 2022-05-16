package app.repository

import app.domain.Person
import app.domain.repository.RepositoryFailure.RepositoryFailure
import zio.IO

trait Repository {

  def save(person: Person): IO[RepositoryFailure, Person]

  def get(id: Int): IO[RepositoryFailure, Option[Person]]

  def getAll(): IO[RepositoryFailure, Seq[Person]]

  def delete(id: Int): IO[RepositoryFailure, Unit]

}
