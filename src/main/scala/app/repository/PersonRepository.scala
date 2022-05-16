package app.repository

import app.domain.{Failure, Person}
import zio.{Has, ZIO}

object PersonRepository {

  type PersonRepository = Has[Repository]

  def save(person: Person): ZIO[PersonRepository, Failure, Person] =
    ZIO.accessM(_.get.save(person))

  def get(id: Int): ZIO[PersonRepository, Failure, Option[Person]] =
    ZIO.accessM(_.get.get(id))

  def getAll(): ZIO[PersonRepository, Failure, Seq[Person]] =
    ZIO.accessM(_.get.getAll())

  def delete(id: Int): ZIO[PersonRepository, Failure, Unit] =
    ZIO.accessM(_.get.delete(id))
}
