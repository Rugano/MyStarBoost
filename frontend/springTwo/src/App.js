import React, { Component } from 'react';
import './App.css';
import logo from './logo.svg'
import { BrowserRouter as Router, Route, Link, Routes  } from 'react-router-dom';
import Stars from './components/Stars'
import About from './components/About'
import LogIn from './components/LogIn'
import Home from './components/Home'
import Register from './components/Register'
import Profile from './components/Profile'
import NoProfile from './components/NoProfile'



class App extends Component {

    componentDidMount() {
        const hasInitialized = localStorage.getItem('hasInitialized');

        if (!hasInitialized) {
            // Perform initialization tasks here
            localStorage.setItem('isLoggedIn', 'false');
            localStorage.setItem('hasInitialized', 'true');
            console.log("Initialized isLoggedIn to false");
            console.log("isLoggedIn == false (from index.js)");
        }
    }

    render() {

        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        console.log(isLoggedIn);
        
        return (

            <Router>
            <div className="App">
                <header className="header">
                        <a href="/">
                            <img src={logo} className="App-logo" alt="logo" />
                        </a>
                        <div className="dropdown">
                            <button className="dropdown-button" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Menu
                            </button>
                            <ul className="dropdown-menu">
                                <li><Link className="dropdown-item" to="/">Home</Link></li>
                                <li><Link className="dropdown-item" to="/stars">Stars</Link></li>
                                <li><Link className="dropdown-item" to="/about">About Us</Link></li>
                                <li><Link className="dropdown-item" to="/log-in">Log In</Link></li>
                                <li><Link className="dropdown-item" to="/register">Register</Link></li>
                                <li><Link className="dropdown-item" to="/profile">Profile</Link></li>
                            </ul>
                        </div>

                    
                    </header>
                    <main className="App-main">
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/stars" element={<Stars />} />
                            <Route path="/about" element={<About />} />
                            <Route path="/log-in" element={<LogIn />} />
                            <Route path="/register" element={<Register />} />
                            <Route path="/profile" element={<Profile />} />
                            <Route path="/no-profile" element={<NoProfile />} />
                        </Routes>

                </main>
                <footer className="App-footer">
                    <p>&copy; 2024 MyStarBoost. All rights reserved.</p>
                </footer>
                </div>
                </Router>
        );
    }
}

export default App;
