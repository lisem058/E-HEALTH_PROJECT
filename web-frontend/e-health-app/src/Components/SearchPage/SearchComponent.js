import { useLocation } from "react-router";
import { makeStyles } from "@material-ui/core/styles";
import { Grid } from '@material-ui/core';
import Card from '../ReusableComponents/Card';
import CategoryData from '../../Category1.json';

const useStyles = makeStyles({
    gridContainer: {
        paddingLeft: "20px",
        paddingRight: "20px",
        margin: 0,
        width: '100%',
        height: '100%'
    }
})


const SearchComponent = () => {

    const location = useLocation().search;
    const app = new URLSearchParams(location).get('app')
    const category = new URLSearchParams(location).get('categories')
    const urlSearch = !app  ?  category : app;

    const classes = useStyles();

    const renderCard = (card, index) => {
        return (
            <Grid item xs={7}>
                <Card data={card}/>
            </Grid>
        )
    }

    return (
        <div className="App">
            <Grid container spacing={6} justifyContent="center" className={classes.gridContainer}>
                {CategoryData[0].data.map(renderCard)}
            </Grid>
        </div>
    )
}

export default SearchComponent