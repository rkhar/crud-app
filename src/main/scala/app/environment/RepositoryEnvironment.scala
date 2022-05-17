package app.environment

import app.repository.{PersonMapRepository, Repository}
import zio.{Has, Layer, ZLayer}

object RepositoryEnvironment {

  val map: Layer[Nothing, Has[Repository]] = ZLayer.succeed(PersonMapRepository)

}
