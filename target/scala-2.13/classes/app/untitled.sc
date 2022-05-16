import scala.util.Try

Try(1/0).toOption.map(x => x + 5)