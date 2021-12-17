import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import {
  Chart,
  PieSeries,
  Title,
  Legend
} from '@devexpress/dx-react-chart-material-ui';
import { Animation } from '@devexpress/dx-react-chart';

const PieChart = (props) => {

    return (
      <Paper>
        <Chart
          data={props.data}
        >
          <PieSeries
            valueField="count"
            argumentField="journal"
          />
          <Title
            text="Analytics per journal"
          />
          <Animation />
          <Legend position="bottom"/>
        </Chart>
      </Paper>
    );
}

export default PieChart;