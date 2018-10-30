//--------------------------------------
//
// FluentdStandaloneTest.scala
// Since: 2013/12/16 10:50 AM
//
//--------------------------------------

package xerial.fluentd

import wvlet.airframe.control.Shell

class FluentdStandaloneTest extends MySpec {
  "launch fluentd" in {
    val fluentd = FluentdStandalone.start()

    try {
      val port       = fluentd.port
      val catCommand = s"${fluentd.config.fluentCatCmd} -p ${port} debug"
      val ret        = Shell.exec("echo '{\"message\":\"hello\"}' | " + catCommand)
      Thread.sleep(1000)
      ret shouldBe (0)
    } finally {
      fluentd.stop
    }
  }
}
