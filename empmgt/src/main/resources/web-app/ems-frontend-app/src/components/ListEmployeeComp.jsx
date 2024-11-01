import React, { useState, useEffect} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ListEmployeeComp = () => {

    const [employees, setListEmployee] = useState([]);

    const navigate = useNavigate();

    // js method to get all employees
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

    // js method to addEmployee
    const addEmployee = () => {
        navigate("/add-employee");
    }


    return (
        <>
            <div className="container">
                <h2 className="text-center">List of Employees</h2>
                <div className="r-grid gap-1">
                  <button className="btn btn-primary btn-lg" type="button" onClick={addEmployee}>Add New Employee</button>
                </div>
                <table className="table table-striped table-sm">
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
                                  <button className="btn btn-primary me-2">Edit</button>
                                  <button className="btn btn-danger"><i className="bi bi-trash"></i>Delete</button>
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