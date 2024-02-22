import React, {useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from "./components/Login";
import AppBarComponent from './components/AppBarComponent';
import Home from './components/Home';
import AllProductsFetchingComponent from "./components/fetchingComponents/AllProductsFetchingComponent";
import SoldProductsFetchingComponent from "./components/fetchingComponents/SoldProductsFetchingComponent";
import AllfeedsFetchingComponent from "./components/fetchingComponents/AllfeedsFetchingComponent";
import TotalRevenueFetchingComponent from "./components/fetchingComponents/TotalRevenueFetchingComponent";
import DescritionFetchingComponents from "./components/fetchingComponents/DescritionFetchingComponents";

function App() {
  const [color] = useState("lightgray");

  useEffect(() => {
    document.body.style.backgroundColor = color;
  }, [color]);

  return (
    <Router>
      <>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route exact path="/feeds" element={
            <>
              <AppBarComponent />
              <div className="container mt-2" style={{ marginTop: 20 }}>
              <h2>All Feeds</h2>
              <AllfeedsFetchingComponent/> {}
              </div>
            </>
          } />
          <Route path="/" element={<Login />} />
          <Route path="/home" element={
            <>
              <AppBarComponent />
              <Home />
            </>
          } />
          <Route path="/sales" element={
            <>
              <AppBarComponent />
              <div className="container mt-2" style={{ marginTop: 20 }}>
              <h2>Total Sales</h2>
              <TotalRevenueFetchingComponent /> {}
              </div>
            </>
          } />
          <Route path="/products" element={
            <>
              <AppBarComponent />
              <div className="container mt-2" style={{ marginTop: 20 }}>
              <h2>All Products</h2>
              <AllProductsFetchingComponent/> {}
              </div>
            </>
          } />
          <Route path="/soldproducts/:id" element={
            <>
              <AppBarComponent />
              <div className="container mt-2" style={{ marginTop: 20 }}>
              <h2>Sold Products</h2>
              <SoldProductsFetchingComponent /> {}
              </div>
            </>
          } />
          <Route path="/description/:ean" element={
            <>
              <AppBarComponent />
              <div className="container mt-2" style={{ marginTop: 20 }}>
              <h2>Detail produktu</h2>
              <DescritionFetchingComponents /> {}
              </div>
            </>
          } />

        </Routes>
      </>
    </Router>
  );
}

export default App;
