import React, { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import LoadingComponent from '../LoadingComponent';
import _ from 'lodash';

const API_URL = 'http://localhost:8080/statistics/products/getAll';

const LazyTable = React.lazy(() => import('../Table'));
import ALLPRODUCTSCOLUMNS from '../columns/AllProductsColumns';

const AllProductsFetchingComponent = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async () => {
    try {
      setLoading(true);
      const response = await axios.get(API_URL);
      setData(response.data);
      setLoading(false);
    } catch (error) {
      setError(error.message);
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
    return <div>Error: {error}</div>;
  }

  if (!LazyTable || !ALLPRODUCTSCOLUMNS) {
    return <div>Error: Failed to load table components.</div>;
  }

  if (!Array.isArray(ALLPRODUCTSCOLUMNS)) {
    return <div>Error: Columns data is not an array.</div>;
  }

  return (
    <React.Suspense fallback={<LoadingComponent />}>
      <LazyTable columns={ALLPRODUCTSCOLUMNS} data={data} />
    </React.Suspense>
  );
};

export default AllProductsFetchingComponent;
