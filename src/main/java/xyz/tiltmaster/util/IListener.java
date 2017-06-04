package xyz.tiltmaster.util;

/**
 * <p>
 *     The IListener interface works together with {@link Notifier} to create the main
 *     communication method between threads in TiltMaster9000. This communication method is a variation of
 *     the observer design pattern where messages sent from the subject to the observer include a parameter of
 *     type E. The IListener is the observer in this case. It registers with the Notifier and receives messages sent
 *     from the Notifier through the receive method.
 * </p>
 *
 * @param <E> The type of message to receive
 * @author Julian Brendl
 * @version 1.0
 */
public interface IListener<E> {
    /**
     * <p>
     *     Gets a message from a Notifier this IListener is registered to along with a parameter of
     *     type M.
     * </p>
     *
     * @param event The event that is sent from the Notifier to the IListener.
     */
    void activate(E event);
}
