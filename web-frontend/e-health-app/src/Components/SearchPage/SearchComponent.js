import { useLocation } from "react-router";
import { makeStyles } from "@material-ui/core/styles";
import { Grid } from '@material-ui/core';
import Card from '../ReusableComponents/Card';

const useStyles = makeStyles({
    gridContainer: {
        paddingLeft: "20px",
        paddingRight: "20px",
        margin: 0,
        width: '100vw',
        height: '100vh'
    }
})


const SearchComponent = () => {

    const location = useLocation().search;
    const category = new URLSearchParams(location).get('categories');

    const classes = useStyles();

    const cardData = [
        "", 
        "",
        "",
        "",
        ""
    ]  

    const renderCard = (card, index) => {
        return (
            <Grid item xs={7}>
                <Card />
            </Grid>
        )
    }


    return (
        <Grid container spacing={8} justify="center" className={classes.gridContainer}>
            {cardData.map(renderCard)}
        </Grid>
    )
}

export default SearchComponent