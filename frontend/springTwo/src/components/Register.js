import React, { Component , createRef} from 'react'
import './Register.css'
import axios from 'axios';
import 'react-datepicker/dist/react-datepicker.css';
import DateOfBirth from './DateOfBirth'
import { Link, Navigate } from 'react-router-dom'



class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            firstname: '',
            lastname: '',
            username: '',
            password: '',
            email: '',
            dob: ''
        };
        this.dateOfBirthRef = createRef();

    }

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({
            [name]: value
        });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const {firstname, lastname, username, password, email, dob} = this.state;
        console.log('Username:', username);
        console.log('Password:', password);
        console.log('Email:', email);
        console.log('Dob:', dob);
        //Check for validation!!!!

        
        // Prepare the data object to send
        const userData = {
            firstName: firstname,
            lastName: lastname,
            username: username,
            hashedPassword: password, // Note: Password should be hashed securely on the backend
            email: email,
            dob: dob
        };

        // Send POST request to register endpoint
        axios.post('http://localhost:8080/api/v1/star/register', userData)
            .then(response => {
                console.log('Registration successful:', response.data)
            })
                .catch (error => {
                    console.error('Registration error:', error);
                    alert("There has been an error");
                    // Handle error states or display error messages to the user
                });

        //Reset the form
        this.dateOfBirthRef.current.reset();


        this.setState({
            firstname: '',
            lastname: '',
            username: '',
            password: '',
            email: '',
            dob: ''
        });
                 

    }




    render() {
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        if (isLoggedIn === 'true') {
            return <Navigate to="/profile" />; // Redirect to /profile
        }

        return (
            <div className="login-container">
                <h2>Register</h2>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="firstname">First Name</label>
                        <input
                            type="text"
                            id="firstname"
                            name="firstname"
                            value={this.state.firstname}
                            onChange={this.handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="username">Last name</label>
                        <input
                            type="text"
                            id="lastname"
                            name="lastname"
                            value={this.state.lastname}
                            onChange={this.handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={this.state.username}
                            onChange={this.handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input
                            type='text'
                            id="password"
                            name="password"
                            value={this.state.password}
                            onChange={this.handleInputChange}
                            required
                        />
                        </div>
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input
                                type='email'
                                id="email"
                                name="email"
                                value={this.state.email}
                                onChange={this.handleInputChange}
                                required
                            />
                    </div>
                    <div className="form-group">
                        <label>Date of Birth</label>
                        <DateOfBirth ref={this.dateOfBirthRef} updateDob={(dob) => this.setState({ dob })} />
                    </div>
                    <button type="submit" className="btn btn-primary">Register Now</button>
                    <nav>
                        <ul>
                            <li>
                                <Link className="link-to-log-in" to="/log-in"> Have an account already, click here to log in</Link>
                            </li>
                        </ul>
                    </nav>
                </form>
            </div>
        );
    }
}

export default Register