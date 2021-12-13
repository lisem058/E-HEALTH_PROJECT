import { useLocation } from "react-router";
import CategoryData from '../../Category1.json';
import AppDataApps from '../../AppData.json';
import SearchBar from '../MainSite/SearchBar';
import Tabs from '../ReusableComponents/Tabs';
import React from 'react';

const SearchComponent = () => {

    const location = useLocation().search;
    const app = new URLSearchParams(location).get('app')
    var data = app === null ? CategoryData.categories : AppDataApps.app;
    var state = app === null ? "category" : "app";


    // TODO: solve the problem with the automatic layout
    return (
        <div className="App">
            <SearchBar placeholder="Enter a name..." data={data} preference={state}/>
            <div style={{height: '50px'}}></div>
            <Tabs app={app}>
            </Tabs>
        </div>
    )
}

export default SearchComponent