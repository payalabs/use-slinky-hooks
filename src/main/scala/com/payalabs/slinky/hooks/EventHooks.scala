package com.payalabs.slinky.hooks

import scalajs.js
import org.scalajs.dom.window
import org.scalajs.dom.raw.Event
import org.scalajs.dom.raw.EventTarget

import slinky.core.facade.Hooks._

object EventHooks {
  /**
   * Hook to handle adding and removing listener.
   *
   * @param eventName name of the event such as `mousedown`
   * @param handler event handler
   * @param install condition to install the listener
   * @param element element to add listener to (defaults to window)
   * @param eventOptions options to pass to event listener such as `bubbles`
   * 
   */
  def useEventListener[E <: Event](
    eventName: String,
    handler: E => Unit,
    install: Boolean,
    element: EventTarget = window,
    eventOptions: js.UndefOr[js.Dynamic] = js.undefined
  ): Unit = {
    val savedHandler = useRef(null: E => Unit)

    useEffect(
      () => savedHandler.current = handler: E => Unit,
      Seq(handler)
    )

    useEffect(
      () => {
        // Must use the function creation form i.e. handler = saveHandler.current is not sufficient
        // since we want to delegate to the current handler from the listener we add below.
        //
        // Must cast to js.Function to make sure that the conversion from scala Function to js function
        // occurs only once and the same reference that is used is available when we remove the handler.
        val handler: js.Function1[E, Unit] = (e: E) => savedHandler.current(e)
        if (install) {
          element.asInstanceOf[js.Dynamic].addEventListener(eventName, handler, eventOptions)
        }
        () =>
          if (install) {
            element.asInstanceOf[js.Dynamic].removeEventListener(eventName, handler, eventOptions)
          }
      },
      Seq(eventName, element, install)
    )
  }
}