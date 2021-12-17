import React, { Component } from "react";
import SearchBar from "./SearchBar";
import SearchPreference from "./SearchPreference";
import CategoryData from '../../CategoryData.json';
import AppData from '../../AppData.json';

class MainPage extends Component {

    constructor() {
        super();
        this.state = {
            preference: "app"
        }
    }

    parentFunction = (dataFromChild) => {
        this.setState({
            preference: dataFromChild
        })
    }

    render() {

        if (this.state.preference === "app") {
            return (
                <div>
                    <SearchPreference functionCallFromParent={this.parentFunction.bind(this)}/>
                    <SearchBar placeholder="Enter an App name..." data={AppData.app} preference={this.state.preference}/>
                </div>
            )    
        } else {
            return (
                <div>
                    <SearchPreference functionCallFromParent={this.parentFunction.bind(this)}/>
                    <SearchBar placeholder="Enter Category..." data={CategoryData.categories} preference={this.state.preference}/>
                </div>
            )    
        }
    }
}

export default MainPage;