import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Table from '../Table';
import { ALLPRODUCTSCOLUMNS } from '../columns/AllProductsColumns';
import LoadingComponent from '../LoadingComponent';


const AllProductsFetchingComponent = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const urlParts = window.location.pathname.split('/');
    const id = urlParts[urlParts.length - 1];

    axios.get(`http://localhost:8080/statistics/products/getAll`)
      .then(response => {
        setData(response.data);
        setLoading(false);
      })
      .catch(err => {
        setError(err);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>
       <LoadingComponent />
    </div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return <Table columns={ALLPRODUCTSCOLUMNS} data={data} />;
};

export default AllProductsFetchingComponent;
