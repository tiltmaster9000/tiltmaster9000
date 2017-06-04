package xyz.tiltmaster.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>
 *     The Notifier class works together the {@link IListener} to create the main
 *     communication method between threads in TruffleHog. This communication method is a variation of
 *     the observer design pattern where messages sent from the subject to the observer include a
 *     parameter of type E. The Notifier is the subject in this case. IListeners register with the
 *     Notifier and can then be notified through the fire method. Unlike in the classic observer pattern, the
 *     notifyListeners method offers the possibility to pass along a parameter of type E.
 * </p>
 *
 * @param <E> The type of event to fire.
 * @author Julian Brendl
 * @version 1.0
 */
public abstract class Notifier<E> {
    private final Collection<IListener<E>> listeners = new ConcurrentLinkedQueue<>();

    /**
     * <p>
     *    Registers an IListener with this Notifier. This IListener will then be notified on the
     *    {@link #fire(E)} method call.
     * </p>
     *
     * @param listener The IListeners to register with this Notifier.
     * @return True, if the listener list changed due to this call.
     * @throws NullPointerException If the supplied listener is null.
     */
    public boolean subscribe(final IListener<E> listener) {
        if (listener == null) {
            throw new NullPointerException("Listener to add should not be null!");
        }
        return listeners.add(listener);
    }

    /**
     * <p>
     *    Removes an IListener from this Notifier. This IListener will then not be notified
     *    anymore on the {@link #fire(E)}} method call.
     * </p>
     *
     * @param listener The IListeners to register with this Notifier.
     * @return True, if a listener was removed due to this call.
     * @throws NullPointerException If the supplied listener is null.
     */
    public boolean unsubscribe(final IListener listener) {
        if (listener == null) {
            throw new NullPointerException("Listener to should must not be null!");
        }
        return listeners.remove(listener);
    }

    /**
     * <p>
     *     Notifies all IListeners that are registered with this Notifier. It sends along a message of type E.
     * </p>
     *
     * @param event The message to send along with the notification to each IListener.
     * @throws NullPointerException If the supplied message is null.
     */
    public void fire(final E event) {
        if (event == null) {
            throw new NullPointerException("Message to be sent should not be null!");
        }
        listeners.forEach(listener -> listener.activate(event));
    }
}
