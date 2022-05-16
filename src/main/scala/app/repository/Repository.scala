package app.repository

import app.domain.{Failure, Person}
import zio.IO

trait Repository {

  def save(person: Person): IO[Failure, Person]

  def get(id: Int): IO[Failure, Option[Person]]

  def getAll(): IO[Failure, Seq[Person]]

  def delete(id: Int): IO[Failure, Unit]

}
