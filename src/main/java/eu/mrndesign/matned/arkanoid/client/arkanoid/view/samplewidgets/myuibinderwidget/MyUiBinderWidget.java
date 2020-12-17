package eu.mrndesign.matned.arkanoid.client.samplewidgets.myuibinderwidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;


public class MyUiBinderWidget extends Composite implements HasText {

    private static MyUiBinderWidgetUiBinder uiBinder = GWT.create(MyUiBinderWidgetUiBinder.class);

    interface MyUiBinderWidgetUiBinder extends UiBinder<Widget, MyUiBinderWidget> {

    }

    @UiField
    Button myButton;

    public MyUiBinderWidget() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("myButton")
    void onClick(ClickEvent e){
        Window.alert("GAME OVER");
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void setText(String s) {

    }



}
