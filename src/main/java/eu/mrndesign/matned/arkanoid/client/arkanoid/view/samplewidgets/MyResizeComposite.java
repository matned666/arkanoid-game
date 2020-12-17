package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SplitLayoutPanel;

public class MyResizeComposite extends ResizeComposite {

	SplitLayoutPanel slp;

	public MyResizeComposite() {
		slp = new SplitLayoutPanel();
		slp.addWest(new HTML("navigation"), 128);
		slp.addNorth(new HTML("list"), 384);
		slp.add(new HTML("details"));
		
		super.initWidget(slp);
	}

}
