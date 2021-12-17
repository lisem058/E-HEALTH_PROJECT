import { TabPanel, useTabs } from 'react-headless-tabs';
import CardFill from '../SearchPage/CardFill';
import '../SearchPage/pagination.scss';
import { useMemo, useState, useEffect } from 'react';
import Pagination from '../SearchPage/Pagination';
import AnalyticsComponent from '../Analytics/AnalyticsComponent';

function Tabs(props) {
  const items = [
    'analytics',
    'searched',
  ]
  const [selectedTab, setSelectedTab] = useTabs(items);
  const [dataCard, setDataCard] = useState(null)
  const [dataBar, setDataBar] = useState(null)
  const [dataLine, setDataLine] = useState(null)
  const [dataPie, setDataPie] = useState(null)

  let PageSize = 5;

  const [currentPage, setCurrentPage] = useState(1);

  useEffect( () => {
    if (props.state === "category") {
      fetch(`https://radiant-tundra-87631.herokuapp.com/api/v1/category/search?categories=${encodeURIComponent(props.data)}`, {
        method: "GET",
        headers: {'Content-Type': 'application/json'},
      })
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw response;
        }
      })
      .then(data => setDataCard(data))
    } else {
      fetch(`https://radiant-tundra-87631.herokuapp.com/api/v1/analytics/class?app=${encodeURIComponent(props.data)}`, {
        method: "GET"
      })
      .then(response =>  {
        if(response.ok) {
          return response.json();
        } else {
          throw response;
        }
      })
      .then(data => setDataBar(data))
      fetch(`https://radiant-tundra-87631.herokuapp.com/api/v1/analytics/date?app=${encodeURIComponent(props.data)}`, {
        method: "GET"
      })
      .then(response =>  {
        if(response.ok) {
          return response.json();
        } else {
          throw response;
        }
      })
      .then(data => {
        data.sort((a, b) => (a.date > b.date) ? 1 : -1)
        const newData = data.map((value, index) => {
          const year = new Date(value.date * 1000).getFullYear() 
          const month = new Date(value.date).getMonth() + 1
          const day = new Date(value.date).getDay() + 1
          return {date: year + "/" + month + "/" + day,
                  count : value.count};
        })
        var reduced = [] 
        newData.reduce((res, value) => {
          if(!res[value.date]) {
            res[value.date] = {date: value.date, count: value.count}
            reduced.push(res[value.date])
          }
          res[value.date].count += value.count
          return res
        }, {})
        setDataLine(reduced)
      })
      fetch(`https://radiant-tundra-87631.herokuapp.com/api/v1/analytics/journal?app=${encodeURIComponent(props.data)}`, {
        method: "GET"
      })
      .then(response =>  {
        if(response.ok) {
          return response.json();
        } else {
          throw response;
        }
      })
      .then(data => {
        setDataPie(data)
      })
      fetch(`https://radiant-tundra-87631.herokuapp.com/api/v1/app/search?app=${encodeURIComponent(props.data)}`, {
        method: "GET"
      })
      .then(response =>  {
        if(response.ok) {
          return response.json();
        } else {
          throw response;
        }
      })
      .then(data => setDataCard(data))
    }
  }, [props.state, props.data])


  const currentData = useMemo(() => {
        const firstPageIndex = (currentPage - 1) * PageSize;
        const lastPageIndex = firstPageIndex + PageSize;
        if (dataCard !== null) {
          if (props.state === "category") {
            return dataCard.data.slice(firstPageIndex, lastPageIndex);
          } else {
            return dataCard.articles.slice(firstPageIndex, lastPageIndex);
          }  
        }
  }, [dataCard, currentPage, props.state, PageSize]);



  const changeTab = (e) => {
    e.preventDefault();
    const target = (e.target).dataset.tab;
    if (typeof target != "string") {
      return;
    }

    setSelectedTab(target);
  }

    const getSelectedTabIndex = () => items.findIndex((item) => item === selectedTab);
      return typeof currentData !== 'undefined' && dataBar !== null && dataCard !== null && dataPie !== null ? (
        <div className='App'>
          <nav>
          <div
            style={{
              position: "absolute",
              left: `calc((100% / ${items.length}) * ${getSelectedTabIndex()})`,
              width: `calc(100% / ${items.length})`,
              background: "red",
              transition: "all ease 0.2s"
            }}
          />
          <div
            style={{
              display: "flex"
            }}
          >
            {items.map((item) => {
              return (
                <a
                  href="#tab"
                  key={item}
                  style={{
                    flexGrow: 1,
                    display: "block",
                    padding: "1rem",
                    textDecoration: "none",
                    color: selectedTab === item ? "red" : "black",
                    background: selectedTab === item ? "#fcfcfc" : "#fff"
                  }}
                  onClick={changeTab}
                  data-tab={item}
                >
                  {item}
                </a>
              );
            })}
          </div>
        </nav>
        <div
          style={{
            padding: "2rem",
          }}
        >
        <TabPanel key={"analytics"} hidden={selectedTab !== "analytics"}>
          {props.state === "app" ? (
            <AnalyticsComponent dataBar={dataBar} dataLine={dataLine} dataPie={dataPie}/>
          ): (
            <span>
              We don't see any good analytics solution for keywords that could be useful
            </span>
          )}
        </TabPanel>
        <TabPanel key={"searched"} hidden={selectedTab !== "searched"}>
          {
            currentData.map(item => {
              return <CardFill elem={item} app={props.state} data={props.data}></CardFill>
            })
          }
          <Pagination
                className="pagination-bar"
                currentPage={currentPage}
                totalCount={ props.state === "category" ? dataCard.data.length : dataCard.articles.length}
                pageSize={PageSize}
                onPageChange={page => setCurrentPage(page)}
          />
        </TabPanel>    
      </div>
    </div>
  ) : (
    <span>
      Loading...
    </span>
  );

}

export default Tabs