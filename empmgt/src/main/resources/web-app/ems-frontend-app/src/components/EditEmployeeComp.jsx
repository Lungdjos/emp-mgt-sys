import React, { useState, useEffect } from 'react';
import axios from 'axios';

const EditEmployeeComp = ({ employee, onClose, onRefresh }) => {
    const [formData, setFormData] = useState({
        id: '',
        firstName: '',
        lastName: '',
        email: '',
        mobile: '',
        gender: '',
        department: '',
        designation: '',
    });

    useEffect(() => {
        if (employee) {
            setFormData({
                id: employee.id,
                firstName: employee.firstName || '',
                lastName: employee.lastName || '',
                email: employee.email || '',
                mobile: employee.mobile || '',
                gender: employee.gender || '',
                department: employee.department || '',
                designation: employee.designation || '',
            });
        }
    }, [employee]); // This runs every time the employee prop changes

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevData => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Send a PUT request to update the employee
        axios.put(`http://localhost:8080/api/v1/employees/${formData.id}`, formData)
            .then(response => {
                onRefresh(); // Refresh the employee list
                onClose(); // Close the modal
            })
            .catch(error => {
                console.error("There was an error updating the employee!", error);
            });
    };

    if (!employee) return null; // Prevent rendering if no employee data is available

    return (
        <div className="modal">
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name:</label>
                    <input
                        type="text"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        placeholder="First Name"
                        required
                    />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input
                        type="text"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        placeholder="Last Name"
                        required
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        placeholder="Email"
                        required
                    />
                </div>
                <div>
                    <label>Mobile:</label>
                    <input
                        type="text"
                        name="mobile"
                        value={formData.mobile}
                        onChange={handleChange}
                        placeholder="Mobile"
                        required
                    />
                </div>
                <div>
                    <label>Gender:</label>
                    <select
                        name="gender"
                        value={formData.gender}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Select Gender</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </div>
                <div>
                    <label>Department:</label>
                    <input
                        type="text"
                        name="department"
                        value={formData.department}
                        onChange={handleChange}
                        placeholder="Department"
                        required
                    />
                </div>
                <div>
                    <label>Designation:</label>
                    <input
                        type="text"
                        name="designation"
                        value={formData.designation}
                        onChange={handleChange}
                        placeholder="Designation"
                        required
                    />
                </div>
                <div>
                    <button type="submit">Save</button>
                    <button type="button" onClick={onClose}>Cancel</button>
                </div>
            </form>
        </div>
    );
};

export default EditEmployeeComp;
