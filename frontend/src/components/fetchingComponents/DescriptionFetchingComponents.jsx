import React, { useEffect, useState } from 'react';
import axios from 'axios';
import LoadingComponent from '../LoadingComponent';

const DescriptionFetchingComponents = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const urlParts = window.location.pathname.split('/');
        const ean = urlParts[urlParts.length - 1];
        const response = await axios.get(`http://localhost:8080/statistics/products/getDescription/${ean}`);

        const modifiedData = response.data.map(item => ({
          ...item,
          large_description: String(item.large_description),
          short_description: String(item.short_description),
        }));

        setData(modifiedData);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <div>
      <LoadingComponent />
    </div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div>
      {data.map(item => (
        <div key={item.id}>
          <h2>{item.title}</h2>
          <p dangerouslySetInnerHTML={{ __html: item.short_description }} />
          <p dangerouslySetInnerHTML={{ __html: item.large_description }} />
        </div>
      ))}
    </div>
  );
};

export default DescriptionFetchingComponents;