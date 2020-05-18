package Forms.Evenement;

import Entities.ChartModel;
import Entities.Evenement;
import Services.EvenementService;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

import java.util.ArrayList;
import java.util.List;

public class ChartDemo extends Form
{

    public ChartDemo()
    {
        createPieChartForm().show();
    }


private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(35);
    renderer.setLegendTextSize(35);
    renderer.setMargins(new int[]{});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param title the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, ArrayList<ChartModel>  values) {
    CategorySeries series = new CategorySeries(title);

    for (ChartModel value : values) {
        Evenement e=EvenementService.getInstance().getEvent(value.getId());

        series.add("Evenement : "+ e.getTitre()+" ", value.getNb());
    }

    return series;
}

public Form createPieChartForm() {


    ArrayList<ChartModel> chm=EvenementService.getInstance().ListeParticipantEvenement();

    // Generate the values

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(200);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(4, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Participations au evenements", chm), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Statistiques", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    return f;

}
}