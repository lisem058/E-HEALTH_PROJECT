import React from "react";
import Paper from '@material-ui/core/Paper';
import {
  Chart,
  BarSeries,
  Title,
  ArgumentAxis,
  ValueAxis,
} from '@devexpress/dx-react-chart-material-ui';
import { Animation } from '@devexpress/dx-react-chart';


const BarChart = (props) => {

    return (
        <Paper>
          <Chart
            data={props.data}
          >
            <ArgumentAxis />
            <ValueAxis max={10} />
  
            <BarSeries
              valueField="count"
              argumentField="className"
            />
            <Title text="Application Per Class" />
            <Animation />
          </Chart>
        </Paper>
    );
}

export default BarChart;



