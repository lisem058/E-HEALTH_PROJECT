import React, { Component } from "react";
import SearchBar from "./SearchBar";
import SearchPreference from "./SearchPreference";
import BookData from '../../Data.json';


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
                    <SearchBar placeholder="Enter an App name..." data={BookData}/>
                </div>
            )    
        } else {
            return (
                <div>
                    <SearchPreference functionCallFromParent={this.parentFunction.bind(this)}/>
                    <SearchBar placeholder="Enter Category..." data={BookData}/>
                </div>
            )    
        }
    }
}

export default MainPage;