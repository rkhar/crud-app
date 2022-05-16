package app.repository

import app.domain.Person
import app.domain.repository.RepositoryFailure.RepositoryFailure
import zio.{Has, ZIO}

object PersonRepository {

  type PersonRepository = Has[Repository]

  def save(person: Person): ZIO[PersonRepository, RepositoryFailure, Person] =
    ZIO.accessM(_.get.save(person))

  def get(id: Int): ZIO[PersonRepository, RepositoryFailure, Option[Person]] =
    ZIO.accessM(_.get.get(id))

  def getAll(): ZIO[PersonRepository, RepositoryFailure, Seq[Person]] =
    ZIO.accessM(_.get.getAll())

  def delete(id: Int): ZIO[PersonRepository, RepositoryFailure, Unit] =
    ZIO.accessM(_.get.delete(id))
}
