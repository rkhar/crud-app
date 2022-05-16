package app.api

import app.Main.ApplicationEnvironment
import app.domain.Person
import app.domain.api.Request.{CreatePersonRequest, UpdatePersonRequest}
import zio.ZIO
import zio.console.{getStrLn, putStrLn}

object Controller {

  val create: ZIO[ApplicationEnvironment, Any, Person] =
    for {
      _ <- putStrLn("Enter first name:")
      firstName <- getStrLn
      _ <- putStrLn("Enter last name:")
      lastName <- getStrLn
      newPerson <- Api.create(CreatePersonRequest(firstName, lastName))
    } yield newPerson

  val update: ZIO[ApplicationEnvironment, Any, Person] =
    for {
      _ <- putStrLn("Enter id:")
      id <- getStrLn
      _ <- putStrLn("Enter first name:")
      firstName <- getStrLn
      _ <- putStrLn("Enter last name:")
      lastName <- getStrLn
      updatedPerson <- Api.update(UpdatePersonRequest(id.toInt, firstName, lastName))
    } yield updatedPerson

  val get: ZIO[ApplicationEnvironment, Any, Option[Person]] =
    for {
      _ <- putStrLn("Enter id:")
      id <- getStrLn
      person <- Api.get(id.toInt)
    } yield person

  val getAll: ZIO[ApplicationEnvironment, Any, Seq[Person]] =
    Api.getAll()

  val delete: ZIO[ApplicationEnvironment, Any, Unit] =
    for {
      _ <- putStrLn("Enter id:")
      id <- getStrLn
      _ <- Api.remove(id.toInt)
    } yield ()
}
