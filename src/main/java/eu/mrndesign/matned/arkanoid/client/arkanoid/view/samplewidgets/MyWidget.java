package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class MyWidget implements IsWidget {

	public FlowPanel widget;
	
	public Widget asWidget() {

	
		if(widget == null){
		widget.add(new HTML("RowA"));
		widget.add(new HTML("RowB"));
		widget.add(new HTML("RowC"));
		widget = new FlowPanel();
		}
		return null;
	}

}
