import { useLocation } from "react-router";
import CategoryData from '../../Category1.json';
import AppDataApps from '../../AppData.json';
import SearchBar from '../MainSite/SearchBar';
import Tabs from '../ReusableComponents/Tabs';
import React from 'react';

// Actually do this stuff is not secure. Like at all, but for the student's project it is fine
const SearchComponent = () => {

    const location = useLocation().search;
    const app = new URLSearchParams(location).get('app')
    const category = new URLSearchParams(location).get('categories')
    var data = app === null ? CategoryData.categories : AppDataApps.app;
    var state = app === null ? "category" : "app";


    // TODO: solve the problem with the automatic layout
    return (
        <div className="App">
            <SearchBar placeholder="Enter a name..." data={data} preference={state}/>
            <div style={{height: '50px'}}></div>
            { state === "app" ? (
                <Tabs state={state} data={app}>
                </Tabs>
            ) : (
                <Tabs state={state} data={category}>
                </Tabs>    
            )}
        </div>
    )
}

export default SearchComponent