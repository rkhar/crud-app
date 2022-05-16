package app.api

import app.domain.Person
import app.domain.api.Request.{CreatePersonRequest, UpdatePersonRequest}
import app.domain.api.Response.{InvalidInputResponse, PersonAlreadyExistsResponse, PersonNotFoundResponse}
import app.domain.repository.RepositoryFailure.RepositoryFailure
import app.repository.PersonRepository
import app.repository.PersonRepository.PersonRepository
import zio.ZIO

object Api {

  def create(request: CreatePersonRequest): ZIO[PersonRepository, Any, Person] =
    Person(request.firstName, request.lastName) match {
      case Some(person) =>
        PersonRepository.get(person.id).flatMap {
          case Some(_) => ZIO.fail(PersonAlreadyExistsResponse(person.id))
          case None => PersonRepository.save(person)
        }
      case None => ZIO.fail(InvalidInputResponse(request.firstName, request.lastName))
    }

  def update(request: UpdatePersonRequest): ZIO[PersonRepository, Any, Person] =
    PersonRepository.get(request.id).flatMap {
      case Some(person) =>
        PersonRepository.save(person.copy(firstName = request.firstName, lastName = request.lastName))
      case None =>
        ZIO.fail(PersonNotFoundResponse(request.id))
    }

  def get(id: Int): ZIO[PersonRepository, RepositoryFailure, Option[Person]] =
    PersonRepository.get(id)

  def getAll(): ZIO[PersonRepository, RepositoryFailure, Seq[Person]] =
    PersonRepository.getAll()

  def remove(id: Int): ZIO[PersonRepository, RepositoryFailure, Unit] =
    PersonRepository.delete(id)
}
