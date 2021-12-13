import { TabPanel, useTabs } from 'react-headless-tabs';
import CardFill from '../SearchPage/CardFill';
import '../SearchPage/pagination.scss';
import AppData from '../../App.json';
import CategoryData from '../../Category1.json';
import { useMemo, useState } from 'react';
import Pagination from '../SearchPage/Pagination';
import AnalyticsComponent from '../Analytics/AnalyticsComponent';

function Tabs({app}) {
  const items = [
    'analytics',
    'searched',
  ]
  const [selectedTab, setSelectedTab] = useTabs(items);

  let PageSize = 5;

  const [currentPage, setCurrentPage] = useState(1);

  const currentData = useMemo(() => {
        const firstPageIndex = (currentPage - 1) * PageSize;
        const lastPageIndex = firstPageIndex + PageSize;
        if (app === null) {
          return CategoryData[0].data.slice(firstPageIndex, lastPageIndex);
        } else {
          return AppData.articles.slice(firstPageIndex, lastPageIndex);
        }
  }, [currentPage]);



  const changeTab = (e) => {
    e.preventDefault();
    const target = (e.target).dataset.tab;
    if (typeof target != "string") {
      return;
    }

    setSelectedTab(target);
  }

    const getSelectedTabIndex = () => items.findIndex((item) => item === selectedTab);
      return (
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
          <AnalyticsComponent />
        </TabPanel>
        <TabPanel key={"searched"} hidden={selectedTab !== "searched"}>
          {
            currentData.map(item => {
              return <CardFill elem={item} app={app}></CardFill>
            })
          }
          <Pagination
                className="pagination-bar"
                currentPage={currentPage}
                totalCount={ app === null ? CategoryData[0].metadata.total : AppData.articles.length}
                pageSize={PageSize}
                onPageChange={page => setCurrentPage(page)}
          />
        </TabPanel>    
      </div>
    </div>
  );

}

export default Tabs