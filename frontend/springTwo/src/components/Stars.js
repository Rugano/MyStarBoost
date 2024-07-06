import React, { Component } from 'react'
import axios from 'axios'

class Stars extends Component {

    constructor(props) {
        super(props)

        this.state = {
            stars: []
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/v1/star')
            .then(response => {
                console.log(response)
                this.setState({stars: response.data})
            })
            .catch(error => {
                console.log(error)
            })
    }

    render() {
        const { stars } = this.state //binding stars to this.state (meaning I can write just stars without this.state.stars)
        return (
            <div>
                {
                    stars.length > 0 ?
                        stars.map(star => <div key={star.id}> First name is: {star.firstName} </div>) :
                    <p> NO STARS AVAILABLE</p>
                }
            </div>
        )
    }
}

export default Stars