package app

import app.repository.PersonRepository.PersonRepository
import zio.{ExitCode, URIO, ZIO}
import zio.console._

import java.io.IOException

object Main extends zio.App {

  type ApplicationEnvironment = Console with PersonRepository

  def run(args: List[String]): URIO[Console, ExitCode] = {
    myAppLogic.exitCode
  }

  val myAppLogic: ZIO[Console, IOException, Unit] =
    for {
      _ <- putStrLn("enter your name")
      name <- getStrLn
      _ <- putStrLn(s"sup, $name?")
    } yield ()

}
