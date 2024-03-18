import React, { useEffect, useState, useCallback } from 'react';
import axios from 'axios';
import LoadingComponent from '../LoadingComponent';
import _ from 'lodash';

const API_URL = 'http://localhost:8080/statistics/store';

const LazyTable = React.lazy(() => import('../Table'));
import TOTALREVENUECOLUMNS from '../columns/TotalRevenueColumns';

const TotalRevenueFetchingComponent = () => {
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

  if (!LazyTable || !TOTALREVENUECOLUMNS) {
    return <div>Error: Failed to load table components.</div>;
  }

  if (!Array.isArray(TOTALREVENUECOLUMNS)) {
    return <div>Error: Columns data is not an array.</div>;
  }

  return (
    <React.Suspense fallback={<LoadingComponent />}>
      <LazyTable columns={TOTALREVENUECOLUMNS} data={data} />
    </React.Suspense>
  );
};

export default TotalRevenueFetchingComponent;
