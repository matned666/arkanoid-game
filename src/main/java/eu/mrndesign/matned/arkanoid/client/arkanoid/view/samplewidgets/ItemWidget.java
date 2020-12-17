package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets.events.HasItemDeleteHandlers;
import eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets.events.ItemDeleteEvent;
import eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets.events.ItemDeleteHandler;

public class ItemWidget extends Composite implements HasItemDeleteHandlers<Integer> {


    public ItemWidget() {
        FlowPanel flowPanel = new FlowPanel();
        Button button = new Button("FUCK");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                ItemDeleteEvent.fire(ItemWidget.this, 1);
            }
        });

        flowPanel.add(new HTML("item"));
        flowPanel.add(button);
        initWidget(flowPanel);
    }

    public HandlerRegistration addClickHandler(ClickHandler handler){
        return addDomHandler(handler, ClickEvent.getType());
    }

    public HandlerRegistration mouseDownHandler(MouseDownHandler handler){
        return addDomHandler(handler, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addDeleteHandler(ItemDeleteHandler<Integer> handler) {
        return addHandler(handler, ItemDeleteEvent.getType());
    }
}
