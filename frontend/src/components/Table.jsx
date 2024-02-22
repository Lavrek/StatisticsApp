import { useTable, useSortBy, usePagination} from "react-table";
import {TableContainer, Paper} from '@mui/material';
import React from 'react';
import { Link } from 'react-router-dom';
import DOMPurify from 'dompurify';


export default function Table ({ columns, data }) {  
 
  const {
    getTableProps, 
    getTableBodyProps, 
    headerGroups, 
    prepareRow,
    page,
    canPreviousPage,
    canNextPage,
    pageOptions,
    pageCount,
    gotoPage,
    nextPage,
    previousPage,
    setPageSize,
    state: { pageIndex, pageSize },
  } = useTable({
    columns,
    data,
  },
    useSortBy,
    usePagination 
  );

  const handleLinkClick = async (id) => {
      window.location.href = `/soldproducts/${id}`;
      console.error(error);
  };

  const handleEanClick = async (ean) => {
    window.location.href = `/description/${ean}`;
    console.error(error);
};
  
  return (
    <><TableContainer component={Paper}
      sx={{
        padding: "20px 0px",
        borderBlock: "1.5px solid black",
        borderRight: "1.5px solid black",
        borderLeft: "1.5px solid black",
        backgroundColor: "lightblue",
        fontSize: "0.9rem"
      }}>
    
      <table {...getTableProps()}>
        <thead>
          {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps(column.getSortByToggleProps())}
                  className={column.isSorted
                    ? column.isSortedDesc
                      ? "sort-desc"
                      : "sort-asc"
                    : ""}
                >
                  {column.render("Header")}
                  <span>
                    {column.isSorted
                      ? column.isSortedDesc
                        ? " 🔽"
                        : " 🔼"
                      : ""}
                  </span>
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {page.map((row) => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                  {row.cells.map((cell, cellIndex) => {
                    if (
                      cell.column.id === "disappear" ||
                      cell.column.Header === "disappear"
                    ) {
                      return (
                        <td
                          {...cell.getCellProps()}
                          style={{ cursor: "pointer" }}
                          onClick={() => handleLinkClick(row.values.id)}
                        >
                          <div style={{
                              textDecoration: "underline",
                              color: "dark green",
                              cursor: "pointer",
                            }}>
                              {cell.value} 

                            </div>
                          <Link to={`/soldproducts/${row.values.id}`}
                            style={{
                              textDecoration: "underline",
                              color: "blue",
                              cursor: "pointer",
                            }}></Link>                                                    
                        </td>
                      );
                    } else if (cell.column.id === "ean" ||
                    cell.column.Header === "EAN"
                    ){
                      return (
                        <td
                          {...cell.getCellProps()}
                          style={{ cursor: "pointer" }}
                          onClick={() => handleEanClick(row.values.ean)}
                        >
                          <div style={{
                              textDecoration: "underline",
                              color: "dark green",
                              cursor: "pointer",
                            }}>
                              {cell.value} 
                            </div>
                            <Link to={`/description/${row.values.id}`}
                            style={{
                              textDecoration: "underline",
                              color: "blue",
                              cursor: "pointer",
                            }}></Link>
                                                
                        </td>
                      );
                    }
                    else if (cell.column.id === "large_description" ||
                    cell.column.Header === "Detailed description" ||
                    cell.column.id === "short_description" ||
                    cell.column.Header === "Short description") {
                      return (
                        <td
                          {...cell.getCellProps()}
                          style={{ cursor: "pointer" }}
                          dangerouslySetInnerHTML={{
                            __html: DOMPurify.sanitize(cell.render("Cell")),
                          }}
                        />
                      );
                    }
                    else {
                      return (
                        <td
                          {...cell.getCellProps()}
                          style={{ cursor: "pointer" }}
                        >
                          {cell.render("Cell")}
                        </td>
                      );
                    }
                  })}
                </tr>
            );
          })}
        </tbody>
      </table>

      
    </TableContainer><div className="pagination">
        <button onClick={() => gotoPage(0)} disabled={!canPreviousPage}>
          {"<<"}
        </button>{" "}
        <button onClick={() => previousPage()} disabled={!canPreviousPage}>
          {"<"}
        </button>{" "}
        <button onClick={() => nextPage()} disabled={!canNextPage}>
          {">"}
        </button>{" "}
        <button onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}>
          {">>"}
        </button>{" "}
        <span>
          Page{" "}
          <strong>
            {pageIndex + 1} of {pageOptions.length}
          </strong>{" "}
        </span>
        <span>
          | Go to page:{" "}
          <input
            type="number"
            defaultValue={pageIndex + 1}
            onChange={(e) => {
              const page = e.target.value ? Number(e.target.value) - 1 : 0;
              gotoPage(page);
            } }
            style={{ width: "50px" }} />
        </span>{" "}
        <select
          value={pageSize}
          onChange={(e) => {
            setPageSize(Number(e.target.value));
          } }
        >
          {[10, 20, 30, 40, 50].map((pageSize) => (
            <option key={pageSize} value={pageSize}>
              Show {pageSize}
            </option>
          ))}
        </select>
      </div></>
  );
}

