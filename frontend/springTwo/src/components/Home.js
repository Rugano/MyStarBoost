import React, { Component } from 'react'

class Home extends Component {

    constructor(props) {
        super(props)


    }

    render() {

        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        console.log(isLoggedIn);

        if (localStorage.getItem('isLoggedIn') === 'false') {
            return (
                <div>
                    <div>
                        <h2>Supporting Aspiring Sports Stars</h2>
                        <p>Connect with sponsors and supporters to raise funds for your tournaments, training, and equipment.</p>
                        <h1> NEED TO ADD SOME MORE "HOME" CONTENT </h1>
                    </div>
                </div>
            )
        }
        return (
            <div>
                    <h2>Supporting Aspiring Sports Stars</h2>
                <p>Connect with sponsors and supporters to raise funds for your tournaments, training, and equipment.</p>
                <button className="upload-project-btn"> Upload a new Project </button>
                </div>
            )
    }
}

export default Home
