import React, { useState, useEffect} from 'react';
import axios from 'axios';

const ListEmployeeComp = () => {

    const [employees, setListEmployee] = useState([])

    const getAllEmployees = () => {
        return axios.get("http://localhost:8080/api/v1/employees");
    }

    useEffect(() => {
        getAllEmployees().then((response) => {
            setListEmployee(response.data);
        }).catch(error => {
            console.log(error);
        })
    }, [])


    return (
        <>
            <div className="container">
                <h2 className="text-center">List of Employees</h2>
                <table className="table">
                  <thead>
                    <tr>
                      <th scope="col">No.</th>
                      <th scope="col">First Name</th>
                      <th scope="col">Last Name</th>
                      <th scope="col">Email</th>
                      <th scope="col">Mobile</th>
                      <th scope="col">Gender</th>
                      <th scope="col">Department</th>
                      <th scope="col">Designation</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                      {employees.map(employee => (
                          <tr key={employee.id}>
                              <th scope="row">{employee.id}</th>
                              <td>{employee.firstName}</td>
                              <td>{employee.lastName}</td>
                              <td>{employee.email}</td>
                              <td>{employee.mobile}</td>
                              <td>{employee.gender}</td>
                              <td>{employee.department}</td>
                              <td>{employee.designation}</td>
                              <td>
                                  <button className="btn btn-primary">Edit</button>
                                  <button className="btn btn-danger">Delete</button>
                              </td>
                          </tr>
                      ))}
                  </tbody>
                </table>
              </div>
        </>
    )
}
export default ListEmployeeComp