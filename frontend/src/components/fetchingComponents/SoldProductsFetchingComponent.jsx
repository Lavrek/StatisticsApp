import React, { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import LoadingComponent from '../LoadingComponent';
import _ from 'lodash';

const API_URL = 'http://localhost:8080/statistics/products/getSelectedGoods/';
const LazyTable = React.lazy(() => import('../Table'));
import SOLDPRODUCTSCOLUMNS from '../columns/SoldProductsColumns';

const SoldProductsFetchingComponent = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async () => {
    try {
      const urlParts = window.location.pathname.split('/');
      const id = urlParts[urlParts.length - 1];
      const response = await axios.get(API_URL + id);

      setData(response.data);
      setLoading(false);
    } catch (error) {
      setError(error);
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  if (loading) {
    return <LoadingComponent />;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <React.Suspense fallback={<LoadingComponent />}>
      <LazyTable columns={SOLDPRODUCTSCOLUMNS} data={data} />
    </React.Suspense>
  );
};
export default SoldProductsFetchingComponent;
