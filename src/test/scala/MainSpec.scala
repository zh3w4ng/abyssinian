import org.scalatest.{Matchers, WordSpec}

class MainSpec extends WordSpec with Matchers {
  "msg" should {
    "equal to hello" in {
      Main.msg shouldEqual "hello"
    }
  }
}
