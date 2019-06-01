package com.payalabs.slinky.hooks

import slinky.core.facade.Hooks._

object GenericHooks {
  /**
   * Hook to track the previous value of the given value
   * @param value current value
   * @return previous value
   */
  def usePrevious[T](value: T): T = {
    val ref = useRef[T](value)

    useEffect(() => {
      ref.current = value
    }, Seq(value))

    ref.current
  }

  /**
   * Hook to force component update. Use it only if you must! 
   */
  def useForceUpdate(): () => Unit = {
    val (epoch, setEpoch) = useState(0)

    () => setEpoch(epoch + 1)
  }
}
