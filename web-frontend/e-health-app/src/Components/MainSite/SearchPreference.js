import { FormControl, FormControlLabel, makeStyles, Radio } from "@material-ui/core"
import { RadioGroup } from "@material-ui/core"
import React, { useState } from "react"
import './SearchPreference.css'


function SearchPreference(props) {

    const [category, setCategory] = useState('app')

    const childFunction = (e) => {
        e.preventDefault();
        props.functionCallFromParent(e.target.value);
        setCategory(e.target.value);
    }


    return(
        <div className="radio-button-search-preference-group">
            <FormControl>
                <RadioGroup row aria-label="search" name="search-preference-radio-group"  
                        value={category} onChange={childFunction} /*{(e) => setCategory(e.target.value)}*/>
                    <FormControlLabel value="app" 
                        control={<Radio color="primary"/>} 
                        label="Search Application" 
                    />
                    <FormControlLabel value="category" 
                        control={<Radio color="primary"/>} 
                        label="Search Category" 
                    />
                </RadioGroup>
            </FormControl>
        </div>
    )
}

export default SearchPreference