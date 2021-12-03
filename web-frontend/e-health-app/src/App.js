import React from "react";
import './App.css';
import MainPage from "./Components/MainSite/MainPage";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; 
import SearchComponent from './Components/SearchPage/SearchComponent';
import AnalyticsComponent from './Components/Analytics/AnalyticsComponent';

function App() {

  return (
    <Router>
      <div className="App">
        <Routes>
          <Route exact path="/" element={<MainPage />} />
          <Route exact path="/search" element={<SearchComponent />}/>
          <Route exact path="/analytics" element={<AnalyticsComponent />}/>
        </Routes>
      </div>
    </Router>
  )
}

export default App;