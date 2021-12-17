import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import {
  Chart,
  ArgumentAxis,
  ValueAxis,
  LineSeries,
  Title,
  Legend,
} from '@devexpress/dx-react-chart-material-ui';
import { ValueScale } from '@devexpress/dx-react-chart';
import { scaleLinear } from 'd3-scale';
import { withStyles } from '@material-ui/core/styles';
import { Animation } from '@devexpress/dx-react-chart';

const format = () => tick => tick;

const demoStyles = () => ({
  chart: {
    paddingRight: '20px',
  },
  title: {
    whiteSpace: 'pre',
  },
});

const ValueLabel = (props) => {
  const { text } = props;
  return (
    <ValueAxis.Label
      {...props}
      text={`${text}`}
    />
  );
};



const titleStyles = {
  title: {
    whiteSpace: 'pre',
  },
};

const TitleText = withStyles(titleStyles)(({ classes, ...props }) => (
  <Title.Text {...props} className={classes.title} />
));



const LineChart = (props) => {

  const max = props.data.reduce(function(prev, current) {
    return (prev.count > current.count) ? prev : current
  }) //returns object

  const modifyDomain = () => [0, max.count + 10];


  return (
      <Paper>
        <Chart
          data={props.data}
        >
          {console.log(max)}
          <ValueScale factory={scaleLinear} modifyDomain={modifyDomain}/>
          <ArgumentAxis tickFormat={format}             
            valueMarginsEnabled={false}
            discreteAxisDivisionMode="crossLabels"
          />
          <ValueAxis
            labelComponent={ValueLabel}
            synchrono
          />

          <LineSeries
            name="Count"
            valueField="count"
            argumentField="date"
          />
          <Legend position="bottom" />
          <Title
            text={`Game per year`}
            textComponent={TitleText}
          />
          <Animation />
        </Chart>
      </Paper>
  );
}

export default withStyles(demoStyles, { name: 'LineChart' })(LineChart);
