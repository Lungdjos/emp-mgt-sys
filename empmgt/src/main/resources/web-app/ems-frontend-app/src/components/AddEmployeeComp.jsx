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

    // form validation
    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        email: '',
        mobile: '',
        gender: '',
        department: '',
        designation: ''
    });

    const handleSubmit = (e) => {
        e.preventDefault(); // Prevent the default form submission
        const employee = { firstName, lastName, email, mobile, gender, department, designation };

        // validate form fields and add employee
        const validationErrors = validate();
        if (validationErrors) {
            setErrors(validationErrors);
        } else {
            addEmployee(employee);
        }
    };

    // js method to addEmployee
    const addEmployee = (employee) => {
        axios.post("http://localhost:8080/api/v1/employees", employee)
            .then((response) => {
                navigate("/employees");
            })
            .catch((error) => {
                console.error(error);
            });
    };

    // function to validate form fields
    const validate = () => {
        const errorsCopy = {
            firstName: '',
            lastName: '',
            email: '',
            mobile: '',
            gender: '',
            department: '',
            designation: ''
        };
        let valid = true;

        if (!firstName) {
            errorsCopy.firstName = 'First Name is required';
            valid = false;
        }

        if (!lastName) {
            errorsCopy.lastName = 'Last Name is required';
            valid = false;
        }

        if (!email) {
            errorsCopy.email = 'Email is required';
            valid = false;
        }

        if (!mobile) {
            errorsCopy.mobile = 'Mobile is required';
            valid = false;
        }

        if (!department) {
            errorsCopy.department = 'Department is required';
            valid = false;
        }

        if (!designation) {
            errorsCopy.designation = 'Designation is required';
            valid = false;
        }

        setErrors(errorsCopy);
        return valid ? null : errorsCopy; // Return errors if not valid
    };

    return (
        <div className="d-flex justify-content-center align-items-center mt-5">
            <div className="card text-bg-light mb-3" style={{ maxWidth: '60rem' }}>
                <div className="card-header text-center fw-bold">Add New Employee</div>
                <div className="card-body">
                    <form className="row g-2" onSubmit={handleSubmit}>
                        <div className="col-md-6">
                            <label>First Name</label>
                            <input type="text" className="form-control" placeholder="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                            {errors.firstName && <div className="text-danger">{errors.firstName}</div>}
                        </div>
                        <div className="col-md-6">
                            <label>Last Name</label>
                            <input type="text" className="form-control" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                            {errors.lastName && <div className="text-danger">{errors.lastName}</div>}
                        </div>
                        <div className="col-md-6">
                            <label>Email</label>
                            <input type="email" className="form-control" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
                            {errors.email && <div className="text-danger">{errors.email}</div>}
                        </div>
                        <div className="col-md-6">
                            <label>Mobile</label>
                            <input type="text" className="form-control" placeholder="Mobile" value={mobile} onChange={(e) => setMobile(e.target.value)} />
                            {errors.mobile && <div className="text-danger">{errors.mobile}</div>}
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
                                <input type="text" className="form-control" placeholder="Department" value={department} onChange={(e) => setDepartment(e.target.value)} />
                                {errors.department && <div className="text-danger">{errors.department}</div>}
                            </div>
                            <div className="col-md-5">
                                <label>Designation</label>
                                <input type="text" className="form-control" placeholder="Designation" value={designation} onChange={(e) => setDesignation(e.target.value)} />
                                {errors.designation && <div className="text-danger">{errors.designation}</div>}
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
