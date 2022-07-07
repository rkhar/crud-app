package app

import app.api.Controller
import app.environment.RepositoryEnvironment
import app.repository.PersonRepository.PersonRepository
import app.repository.Repository
import zio.console._
import zio.{ExitCode, Has, ZEnv, ZIO, ZLayer}

object Main extends zio.App {

  type ApplicationEnvironment = Console with PersonRepository

  val localApplicationEnvironment: ZLayer[Any, Nothing, Console with Has[Repository]] = Console.live ++ RepositoryEnvironment.map

  def run(args: List[String]): ZIO[ZEnv, Nothing, ExitCode] = {
    val env = args.headOption.getOrElse("console")

    if (env == "console")
      program().provideLayer(localApplicationEnvironment).exitCode
    else
      program().provideLayer(localApplicationEnvironment).exitCode

  }

  def program(): ZIO[ApplicationEnvironment, Nothing, Int] =
    (for {
      _ <- putStrLn("Select operation to perform:")
      _ <- putStrLn("c for create") *>
        putStrLn("g for get") *>
        putStrLn("u for update") *>
        putStrLn("ga for get all") *>
        putStrLn("d for delete") *>
        putStrLn("e for exit")
      operation <- getStrLn
      _ <- selectOperation(operation)
        .tapError(e => putStrLn(s"Failed with $e"))
        .flatMap(s => putStrLn(s"Succeeded with $s") *> program()) orElse program()
    } yield 0).tapError(e => putStrLn(s"Unexpected failure: $e")) orElse ZIO.succeed(1)

  private def selectOperation(operation: String): ZIO[ApplicationEnvironment, Any, Any] = operation match {
    case "c" => Controller.create
    case "g" => Controller.get
    case "u" => Controller.update
    case "ga" => Controller.getAll
    case "d" => Controller.delete
    case "e" => ZIO.fail("that's it")
    case _ => ZIO.fail("not a valid selection")
  }
}
