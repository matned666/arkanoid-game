package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;

public class MyFlowPanel extends FlowPanel {

	public MyFlowPanel() {
		super();
		
		getElement().getStyle().setBorderColor("red");
		getElement().getStyle().setBorderWidth(5, Unit.PX);
		getElement().getStyle().setBorderStyle(BorderStyle.DOTTED);
	}

}
