import React, { Component } from 'react'
import axios from 'axios'

class About extends Component {

    constructor(props) {
        super(props)

       
    }

    render() {
        return (
            <div>
                {
                    <p>StarBoost is a crowdfunding platform designed to support aspiring sports stars, including professional poker players and
                        tennis players, by connecting them with sponsors and supporters. Built with Java (Spring Boot), JavaScript(React)
                        HTML, CSS, and MongoDB, this platform aims to help athletes raise funds for tournaments, training, and equipment.
                    </p>

                }
            </div>
        )
    }
}

export default About