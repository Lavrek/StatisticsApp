import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Table from '../Table';
import { ALLFEEDSCOLUMNS } from '../columns/AllFeedsColumns';
import LoadingComponent from '../LoadingComponent';


const AllfeedsFetchingComponent = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const urlParts = window.location.pathname.split('/');
    const id = urlParts[urlParts.length - 1];

    axios.get(`http://localhost:8080/statistics/store`)
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

  return <Table columns={ALLFEEDSCOLUMNS} data={data} />;
};

export default AllfeedsFetchingComponent;
