package com.payalabs.slinky.hooks

import scalajs.js
import org.scalajs.dom.window
import org.scalajs.dom.raw.Event

import slinky.core.facade.Hooks._

object WindowsHooks {
  /**
   * Hook to track window size
   * @return the current width and height of the window
   *  
   */
  def useWindowResize(): (Double, Double) = {
    val (width, setWidth) = useState(window.innerWidth)
    val (height, setHeight) = useState(window.innerWidth)

    useEffect(
      () => {
        val windowResizeListener: js.Function1[Event, Unit] = (_: Event) => {
          setWidth(window.innerWidth)
          setHeight(window.innerHeight)
        }

        window.addEventListener("resize", windowResizeListener)
        () => {
          window.removeEventListener("resize", windowResizeListener)
        }
      },
      Seq.empty
    )

    (width, height)
  }
}