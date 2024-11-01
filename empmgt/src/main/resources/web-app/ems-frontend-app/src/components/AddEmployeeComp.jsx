import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AddEmployeeComp = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [mobile, setMobile] = useState('');
    const [gender, setGender] = useState('Male'); // Set default value
    const [department, setDepartment] = useState('');
    const [designation, setDesignation] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault(); // Prevent the default form submission
        const employee = { firstName, lastName, email, mobile, gender, department, designation };
        addEmployee(employee);
    };

    // js method to addEmployee
    const addEmployee = (employee) => {
        axios.post("http://localhost:8080/api/v1/employees", employee)
            .then((response) => {
                navigate("/employees");
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <div className="d-flex justify-content-center align-items-center mt-5">
            <div className="card text-bg-light mb-3" style={{ maxWidth: '60rem' }}>
                <div className="card-header text-center fw-bold">Add New Employee</div>
                <div className="card-body">
                    <form className="row g-2" onSubmit={handleSubmit}>
                        <div className="col-md-6">
                            <label>First Name</label>
                            <input type="text" className="form-control" placeholder="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
                        </div>
                        <div className="col-md-6">
                            <label>Last Name</label>
                            <input type="text" className="form-control" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} required />
                        </div>
                        <div className="col-md-6">
                            <label>Email</label>
                            <input type="email" className="form-control" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                        </div>
                        <div className="col-md-6">
                            <label>Mobile</label>
                            <input type="text" className="form-control" placeholder="Mobile" value={mobile} onChange={(e) => setMobile(e.target.value)} required />
                        </div>
                        <div className="row">
                            <div className="col-md-2">
                                <label className="form-label" htmlFor="autoSizingSelect">Gender</label>
                                <select className="form-select small" id="autoSizingSelect" value={gender} onChange={(e) => setGender(e.target.value)}>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                            <div className="col-md-5">
                                <label>Department</label>
                                <input type="text" className="form-control" placeholder="Department" value={department} onChange={(e) => setDepartment(e.target.value)} required />
                            </div>
                            <div className="col-md-5">
                                <label>Designation</label>
                                <input type="text" className="form-control" placeholder="Designation" value={designation} onChange={(e) => setDesignation(e.target.value)} required />
                            </div>
                        </div>
                        <button type="submit" className="btn btn-primary btn-sm">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AddEmployeeComp;
