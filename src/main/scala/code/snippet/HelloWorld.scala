package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._
import net.liftweb.http.{RoundTripInfo, S, RoundTripHandlerFunc}
import net.liftweb.json.JsonAST._
import net.liftweb.http.js.JE.JsRaw
import net.liftweb.http.js.JsCmds.Script

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  def howdy = {
  ((for {
      session <- S.session
    } yield <lift:tail>{Script(
        JsRaw(s"var pageFunctions = ${session.buildRoundtrip(roundTrips).toJsCmd}").cmd
      )}</lift:tail>) openOr NodeSeq.Empty)
  }

  def booleanIssue(value : JValue, func: RoundTripHandlerFunc) {
    val JBool(bool) = value \ "bool"
    func.send(JBool(bool))
  }

  def roundTrips : List[RoundTripInfo] = ("booleanIssue" -> booleanIssue _) :: Nil
}

