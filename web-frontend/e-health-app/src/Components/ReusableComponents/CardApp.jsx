import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Link from "@material-ui/core/Link";
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles({
  root: {
    minWidth: 200
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

export default function OutlinedCard({data, app}) {
  const classes = useStyles();

  const renderTypography = (item, index) => {

    return (
      <Typography variant="body2" component="li">
        {item}
      </Typography>
    )
  }

  return (
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography
          className={classes.title}
          color="textSecondary"
          gutterBottom
        >
          {data.classMark}
        </Typography>
        <Typography variant="h5" component="h2">
          Application - "{app}"
        </Typography>
        <Typography className={classes.pos} color="textSecondary">
          {data.title}
        </Typography>
        {data.keywords.map(renderTypography)}
      </CardContent>
      <CardActions>
        <Link href={`https://pubmed.ncbi.nlm.nih.gov/${data.pubmedId}`}
              rel="noopener noreferrer" target="_blank">Article</Link>
      </CardActions>
    </Card>
  );
}
