package com.os.scau.agriculturalproductssystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPriceForecast extends Fragment {
    private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 2;
    private int numberOfPoints = 12;
    float[][] randomNumbersTab = new float[numberOfLines][numberOfPoints];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_price_forecast, container, false);
        chart = (LineChartView) view.findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());

        generateValues();
        generateData();
        return view;
    }


    private void generateValues() {
        for (int i = 0; i < numberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }
    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            //line.setShape(shape);
            line.setCubic(true);
            //底部填充
            //line.setFilled(isFilled);
            line.setHasLabels(true);
            //点击显示Lable
            // line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            //除线
            // line.setHasLines(hasLines);
            //除点
            // line.setHasPoints(hasPoints);
            //点不同色
            //
//            if (pointsHaveDifferentColor){
//                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
//            }
            lines.add(line);
        }

        data = new LineChartData(lines);

//        if (hasAxes) {
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
//            if (hasAxesNames) {
        axisX.setName("Axis X");
        axisY.setName("Axis Y");
//            }
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
//        } else {
//            data.setAxisXBottom(null);
//            data.setAxisYLeft(null);
//        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }
    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
