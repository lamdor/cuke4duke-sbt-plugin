import cuke4duke.ScalaDsl
import org.junit.Assert._

class AddNumbersSteps extends ScalaDsl {
  val adder = new Adder

  Given("first number is \"(\\d+)\"") { i: Int =>
    adder.firstNumber = i
  }


  Given("second number is \"(\\d+)\"") {i: Int =>
    adder.secondNumber = i
  }

  Then("the result should be \"(\\d+)\"") { expected: Int =>
    assertEquals(expected, adder.result)
  }
}
