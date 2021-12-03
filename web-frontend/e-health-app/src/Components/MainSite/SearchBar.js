import React, { useState } from "react";
import "./SearchBar.css";
import SearchIcon from '@material-ui/icons/Search';
import CloseIcon from '@material-ui/icons/Close';
import { useNavigate, createSearchParams } from 'react-router-dom';

// TODO: change categories api
// TODO: optimize it
function SearchBar({placeholder, data}) {

    const [filteredData, setFilteredData] = useState([]);
    const [wordEntered, setWordEntered] = useState("");
    const history = useNavigate();


    const handleFilter = (event) => {
        const searchWord = event.target.value
        setWordEntered(searchWord)
        const newFilter = data.categories.filter( (value) => {
            return value.toLowerCase().includes(searchWord.toLowerCase());
        })

        if (searchWord === "") {
            setFilteredData([]);
        } else {
            console.log(newFilter);
            setFilteredData(newFilter);
        }
    }

    const clearInput = () => {
        setFilteredData([]);
        setWordEntered("");
    }

    const actualSearch = () => {
        history({
            pathname: "/search",
            search: `?${createSearchParams({
                categories: filteredData
            })}`
        })
    }

    return (
        <div className="search">
            <div className="searchInputs">
                <input type="text" placeholder={placeholder} value={wordEntered} onChange={handleFilter}/>
                <div className="searchIcon">
                    {filteredData.length === 0 ? (
                        <div className="twoIcons">
                            <SearchIcon id="searchBtn" />
                        </div>
                     ) : (
                         <div className="twoIcons">
                            <CloseIcon id="clearBtn"  onClick={clearInput}/>
                            <SearchIcon id="searchBtn" onClick= {actualSearch}/>
                         </div>
                     )}
                </div>
            </div>
            {filteredData.length != 0 && (
                <div className="dataResult">
                    {filteredData.slice(0, 15).map((value, key) => {
                        return <a className="dataItem" href={value.link} target="_blank">
                            <p>{value}</p>
                        </a>
                    })}
                </div>
            )}
        </div>
    );
}

export default SearchBar