import { makeStyles } from "@material-ui/core/styles";
import { Grid } from '@material-ui/core';
import Card from '../ReusableComponents/Card';
import CardApp from '../ReusableComponents/CardApp';

const useStyles = makeStyles({
    gridContainer: {
        paddingLeft: "20px",
        paddingRight: "20px",
        margin: 0,
        width: '100%',
        height: '100%'
    }
})

const CardFill = (props) => {
    const classes = useStyles();

    return (
            <Grid container spacing={6} justifyContent="center" className={classes.gridContainer}>
                {props.app === null ? (
                    <Grid item xs={7}>
                        <Card data={props.elem}/>
                    </Grid>
                ) : (
                    <Grid item xs={7}>
                        <CardApp data={props.elem} app={props.app}/>
                    </Grid>
                )
            }
            </Grid>
    )
}


export default CardFill;
                