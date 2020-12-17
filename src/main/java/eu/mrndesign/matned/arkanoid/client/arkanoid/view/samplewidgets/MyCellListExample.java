package eu.mrndesign.matned.arkanoid.client.arkanoid.view.samplewidgets;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.LinkedList;
import java.util.List;

public class MyCellListExample implements IsWidget {

    @Override
    public Widget asWidget() {
        AbstractCell<String> abstractCell = new AbstractCell<String>("click"){

            @Override
            public void render(Context context, String s, SafeHtmlBuilder safeHtmlBuilder) {
                safeHtmlBuilder.append(SafeHtmlUtils.fromString(s));
            }
        };

        TextCell textCell = new TextCell();

        List<String> values = new LinkedList<>();
        values.add("rowA");
        values.add("rowB");
        values.add("rowC");

        CellList<String> cellList = new CellList<>(textCell);

        cellList.setRowCount(5);
        cellList.setRowData(values);
        return cellList;
    }
}
