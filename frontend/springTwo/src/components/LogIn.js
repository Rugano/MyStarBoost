import React, { Component } from 'react'
import './LogIn.css'
import axios from 'axios'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { Link, Navigate } from 'react-router-dom';


class LogIn extends Component {
    constructor(props) {

        super(props);
        this.state = {
            username: '',
            password: '',
            showPassword: false
        };
    }

   /*
    componentDidMount() {
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        if (isLoggedIn === 'true') {
            return <Navigate to="/profile" />; // Redirect to /profile
        }
    }
    */

    handleInputChange = (event) => {
        const { name, value } = event.target;
        this.setState({
            [name]: value
        });
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const { username, password } = this.state;
        console.log('Username:', username);
        console.log('Password:', password);

        //preparing the data to send to the backend
        const userData = {
            username: username,
            hashedPassword: password
        };

        this.setState({
            username: '',
            password: ''
        });

        // Send POST request to register endpoint
        axios.post('http://localhost:8080/api/v1/star/log-in', userData)
            .then(response => {
                console.log('Log In successful:', response.data)
                localStorage.setItem('isLoggedIn', 'true');
                console.log("isLoggedIn = true");
                this.setState({});
            })
            .catch(error => {
                console.error('Log In error:', error);
                if (error.response && error.response.data) {
                    alert(`Error: ${error.response.data}`);
                    // You can access more specific details from error.response.data if needed
                } else {
                    alert('An unexpected error occurred.');
                   
                }
            });

       
        

    }

    togglePasswordVisibility = () => {
        this.setState((prevState) => ({
            showPassword: !prevState.showPassword
        }));
    }

  

    render() {
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        if (isLoggedIn === 'true') {
            return <Navigate to="/profile" />; // Redirect to /profile
        }

        return (
            <div className="login-container">
                <h2>Log In</h2>
                <form onSubmit={this.handleSubmit}>
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
                                type={this.state.showPassword ? 'text' : 'password'}
                                id="password"
                                name="password"
                                value={this.state.password}
                                onChange={this.handleInputChange}
                                required
                            />
                            <span onClick={this.togglePasswordVisibility} className="password-toggle-icon">
                                <FontAwesomeIcon icon={this.state.showPassword ? faEyeSlash : faEye} />
                            </span>
                        </div>
                    
                    <button type="submit" className="btn btn-primary">Log In</button>
                </form>
                <nav>
                    <ul>
                        <li>
                            <Link className="link-to-register" to="/register"> Don't have an account, click here</Link>
                        </li>
                    </ul>
                </nav>

            </div>
        );
    }
}

export default LogIn;
