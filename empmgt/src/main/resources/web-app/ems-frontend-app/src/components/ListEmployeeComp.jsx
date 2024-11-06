import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import EditEmployeeComp from './EditEmployeeComp'; // Import your modal component

const ListEmployeeComp = () => {
    const [employees, setListEmployee] = useState([]);
    const [selectedEmployee, setSelectedEmployee] = useState(null); // State for selected employee
    const [showModal, setShowModal] = useState(false); // State to control modal visibility
    const navigate = useNavigate();

    const getAllEmployees = () => {
        return axios.get("http://localhost:8080/api/v1/employees");
    };

    useEffect(() => {
        getAllEmployees()
            .then((response) => {
                setListEmployee(response.data.data);
            })
            .catch((error) => {
                console.log(error);
                setListEmployee([]);
            });
    }, []);

    const openEditModal = (employee) => {
        setSelectedEmployee(employee); // Set selected employee data
        setShowModal(true); // Open the modal
    };

    const closeModal = () => {
        setShowModal(false);
        setSelectedEmployee(null); // Clear selected employee data
    };

    return (
        <>
            <div className="container">
                <h2 className="text-center">List of Employees</h2>
                <button className="btn btn-primary btn-lg" type="button" onClick={() => navigate("/add-employee")}>
                    Add New Employee
                </button>
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
                        {Array.isArray(employees) && employees.length > 0 ? (
                            employees.map(employee => (
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
                                        <button className="btn btn-primary me-2" onClick={() => openEditModal(employee)}>Edit</button>
                                        <button className="btn btn-danger">Delete</button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="9" className="text-center">No employees found.</td>
                            </tr>
                        )}
                    </tbody>
                </table>

                {showModal && (
                    <EditEmployeeComp
                        employee={selectedEmployee}
                        onClose={closeModal}
                        onRefresh={getAllEmployees} // Pass refresh function
                    />
                )}
            </div>
        </>
    );
};

export default ListEmployeeComp;
