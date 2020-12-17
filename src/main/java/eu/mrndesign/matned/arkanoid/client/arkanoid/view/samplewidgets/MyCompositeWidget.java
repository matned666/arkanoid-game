package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

public class MyCompositeWidget extends Composite {

	FlowPanel widget = new FlowPanel();

	public MyCompositeWidget() {
		widget.add(new HTML("row a"));
		widget.add(new HTML("row b"));
		widget.add(new HTML("row c"));

		initWidget(widget);

	}
	
	public void setSomething(boolean setIt){
		super.setVisible(setIt);
	}

}
