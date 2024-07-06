import React, { Component } from 'react';
import axios from 'axios';
import './Profile.css';
import { Link } from 'react-router-dom';
import logo from '../logo.svg';

class Profile extends Component {
    constructor(props) {
        super(props);

        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            dob: '',
            username: '',
            profilePic: null,
            profilePicUrl: null // Add a state for the image URL
        };

        // Create a ref for the hidden file input
        this.fileInputRef = React.createRef();
    }

    componentDidMount() {
        this.fetchProfileData();
    }

    // Function to fetch profile data
    fetchProfileData = () => {
        const isLoggedIn = localStorage.getItem('isLoggedIn');

        if (isLoggedIn === 'true') {
            axios.get('http://localhost:8080/api/v1/star/profile')
                .then(response => {
                    console.log(response);
                    const { firstName, lastName, email, dob, username } = response.data;
                    this.setState({
                        firstName,
                        lastName,
                        email,
                        dob,
                        username
                    });
                })
                .catch(error => {
                    console.log(error);
                });
            axios.get('http://localhost:8080/api/v1/star/image/get')
                .then(response => {
                    console.log(response);
                    const { contentType, data } = response.data;
                    const imageUrl = `data:${contentType};base64,${data}`;
                    this.setState({
                        profilePic: response.data,
                        profilePicUrl: imageUrl // Set the URL of the profile picture
                    });

                }).catch(error => {
                    console.log("Big Balagan 3" +error);
                });
        }
    }

    // Function to trigger file selection
    handleFileSelect = () => {
        this.fileInputRef.current.click();
    };

    // Function to handle file selection
    handleFileChange = (event) => {
        const file = event.target.files[0];
        this.setState({
            profilePic: file,
            profilePicUrl: URL.createObjectURL(file) // Create a URL for the selected file
        }, () => {
            // Call handleChangePicture after setting state
            this.handleChangePicture();
        });
    };

    // Function to handle picture upload
    handleChangePicture = () => {
        const { profilePic } = this.state;

        if (!profilePic) {
            console.log('cant upload the pic');
            alert('Please select a file first.');
            return;
        }

        const formData = new FormData();
        formData.append('file', profilePic); // Append the selected file

        // Make a POST request to upload the file
        axios.post('http://localhost:8080/api/v1/star/image/upload', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(response => {
                console.log('Image uploaded successfully:', response.data);
                // Optionally update state or show a success message
                // Fetch updated profile data after successful upload
                this.fetchProfileData();
            })
            .catch(error => {
                console.error('Error uploading image:', error);
                // Handle error, e.g., show an error message
            });
        this.setState({});
    };

    handleLogOut = () => {
        const isLoggedIn = localStorage.getItem('isLoggedIn')

        if (isLoggedIn === 'true') {

            axios.delete('http://localhost:8080/api/v1/star/log-out')
                .then(response => {
                    console.log("Logged out successfully");
                    localStorage.setItem('isLoggedIn', 'false');
                    console.log("isLoggedIn = false");
                    this.setState({});
                })
                .catch(error => {
                    console.error("An error occured");
                });

            this.setState({
                firstName: '',
                lastName: '',
                email: '',
                dob: '',
                username: '',
                profilePic: null,
                profilePicUrl: null // Reset the image URL on logout
            });
        }
        else {
            this.setState({});
        }
        
    }

    render() {
        const { firstName, lastName, email, dob, username, profilePicUrl } = this.state;

        if (localStorage.getItem('isLoggedIn') === 'false') {
            return (
                <div className="profile-container">
                    <h2>Profile</h2>
                    <Link to="/log-in">
                        <button className="log-out-btn">Log In</button>
                    </Link>
                    <div className="profile-info">
                        <p><strong>You need to log in first.. </strong> </p>
                    </div>
                </div>
            );
        }
        return (
            <div className="profile-container">
                <h2>Profile</h2>
                <button className="log-out-btn" onClick={this.handleLogOut}>Log-out</button>
                <div className="profile-info">
                    <div className="profile-picture-container">
                        {profilePicUrl ? (
                            <img src={profilePicUrl} alt="Profile" className="profile-picture" />
                        ) : (
                            <img src={logo} alt="Default Profile" className="profile-picture" />
                        )}
                        {/* Hidden file input */}
                        <input
                            ref={this.fileInputRef}
                            type="file"
                            accept="image/*"
                            onChange={this.handleFileChange}
                            style={{ display: 'none' }}
                        />
                        {/* Change photo button */}
                        <button className="upload-picture-btn" onClick={this.handleFileSelect}>Change photo</button>
                    </div>
                    <p><strong>First Name:</strong> {firstName}</p>
                    <p><strong>Last Name:</strong> {lastName}</p>
                    <p><strong>Email:</strong> {email}</p>
                    <p><strong>Date of Birth:</strong> {dob}</p>
                    <p><strong>Username:</strong> {username}</p>
                </div>
            </div>
        );
    }
}

export default Profile;
