package app.http

import org.http4s.dsl.Http4sDsl
import org.http4s.ember.client.EmberClientBuilder
import zio.{IO, Task, ZIO}

object ioz extends Http4sDsl[Task]

object Server {

}
