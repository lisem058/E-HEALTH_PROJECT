import React, { useState } from "react";
import "./SearchBar.css";
import SearchIcon from '@material-ui/icons/Search';
import CloseIcon from '@material-ui/icons/Close';
import {Box} from '@material-ui/core';
import { useNavigate, createSearchParams } from 'react-router-dom';

// TODO: change categories api
// TODO: optimize it
function SearchBar({placeholder, data, preference}) {

    const [filteredData, setFilteredData] = useState([]);
    const [wordEntered, setWordEntered] = useState("");
    const history = useNavigate();


    const handleFilter = (event) => {
        const searchWord = event.target.value
        setWordEntered(searchWord)
        const newFilter = data.filter( (value) => {
            return value.toLowerCase().includes(searchWord.toLowerCase());
        })

        if (searchWord === "") {
            setFilteredData([]);
        } else {
            setFilteredData(newFilter);
        }
    }

    const clearInput = () => {
        setFilteredData([]);
        setWordEntered("");
    }

    const actualSearch = (value) => {
        history({
            pathname: "/search",
            search: `?${createSearchParams({
                categories: value
            })}`
        })
    }

    const appSearch = (value) => {
        history({
            pathname: "/search",
            search: `?${createSearchParams({
                app: value
            })}`
        })
    }

    // TODO: solve the problem with the buttons (need to make one of them just as a box)
    return (
        <div className="search">
            <div className="searchInputs">
                <input type="text" placeholder={placeholder} value={wordEntered} onChange={handleFilter}/>
                <div className="searchIcon">
                    {filteredData.length === 0 ? (
                        <div className="twoIcons">
                            <Box>
                                <Box display="none">
                                    <CloseIcon id="clearBtn"/>
                                </Box>
                                <Box display="inline">
                                    <SearchIcon id="searchBtn"/>
                                </Box>
                            </Box>
                        </div>
                     ) : (
                         <div className="twoIcons">
                            <CloseIcon id="clearBtn"  onClick={clearInput}/>
                            {preference === "app" ? (
                                <SearchIcon id="searchBtn" onClick={appSearch} />
                            ) : (
                                <SearchIcon id="searchBtn" onClick= {actualSearch}/>
                            )}
                         </div>
                     )}
                </div>
            </div>
            {filteredData.length != 0 && preference === "app" && (
                <div className="dataResult">
                    {filteredData.slice(0, 15).map((value, key) => {
                        return (
                        <div className="dataItem" onClick={() => appSearch(value)}>
                            <p>{value}</p>
                        </div>)
                    })}
                </div>
            )}
            {filteredData.length != 0 && preference === "category" && (
                <div className="dataResult">
                    {filteredData.slice(0, 15).map((value, key) => {
                        return (
                        <div className="dataItem" onClick={() => actualSearch(value)}>
                            <p>{value}</p>
                        </div>)
                    })}
                </div>
            )}

        </div>
    );
}

export default SearchBar