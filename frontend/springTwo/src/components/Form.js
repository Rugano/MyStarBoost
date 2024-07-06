import React, { Component } from 'react'


class Form extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            comments: '',
            topic: 'react'
        }
    }

    handleUsernameChange = (event) => {
        this.setState({
            username: event.target.value
        })
    }

    handleCommentsChange = (event) => {
        this.setState({
            comments: event.target.value
        })
    }

    handleTopicChange = (event) => {
        this.setState({
            topic: event.target.value
        })
    }

    handleSubmit = (event) => {
        alert(`your username is: ${this.state.username} your comments are: ${this.state.comments} and you selected the topic: ${this.state.topic}`)
        event.preventDefault()
    }

    render() {
        const { username, comments, topic } = this.state //Simply having the ability to remove this.state. before each of the following: username, comments, topic (i.e. input value)
        return (
            <form onSubmit={this.handleSubmit}>
            <div>
                <label>Username</label>
                <input
                    type='text'
                    value={username}
                    onChange={this.handleUsernameChange}
                />
                </div>
                <div>
                    <label>Comments</label>
                    <textarea
                        value={this.state.comments}
                        onChange={this.handleCommentsChange}></textarea>
                </div>
                <div>
                    <label>Topic</label>
                    <select value={this.state.topic} onChange={this.handleTopicChange}>
                        <option value = "react">React</option>
                        <option value = "angular">Angular</option>
                        <option value = "vue">Vue</option>
                    </select>
                    <button type="submit">Submit</button>
                </div>
            </form>
        )
    }
}

export default Form