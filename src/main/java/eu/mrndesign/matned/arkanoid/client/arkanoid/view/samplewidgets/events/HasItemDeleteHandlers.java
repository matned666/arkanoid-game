package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets.events;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;


public interface HasItemDeleteHandlers<T> extends HasHandlers {
    HandlerRegistration addDeleteHandler(ItemDeleteHandler<T> var1);
}

