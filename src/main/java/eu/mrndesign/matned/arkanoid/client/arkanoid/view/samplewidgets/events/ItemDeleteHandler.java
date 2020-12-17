package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets.events;


import com.google.gwt.event.shared.EventHandler;

public interface ItemDeleteHandler<T> extends EventHandler {
    void onDelete(ItemDeleteEvent<T> var1);
}
