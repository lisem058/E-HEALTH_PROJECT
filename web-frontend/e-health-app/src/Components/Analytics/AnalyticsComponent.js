import BarChart from './BarChart'
import LineChart from './LineChart'
import PieChart from './PieChart'
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
    root: {
      minWidth: 900
    },
    bullet: {
      display: "inline-block",
      margin: "0 2px",
      transform: "scale(0.8)"
    },
    title: {
      fontSize: 14
    },
    pos: {
      marginBottom: 12
    }
});  


const AnalyticsComponent = (props) => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <BarChart data={props.dataBar}/>
            <div style={{height: '50px'}}></div>
            <LineChart data={props.dataLine}/>
            <div style={{height: '50px'}}></div>
            <PieChart data={props.dataPie}/>
        </div>
    )

}

export default AnalyticsComponent