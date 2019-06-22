import React, { Component } from 'react'
import { JsonEditor as Editor } from 'jsoneditor-react'
import 'jsoneditor-react/es/editor.min.css'

class App extends React.Component {
  render() {
    let json = {
      'test': 1
    }
    return (
      <div>
        <h1>Hello, Electron teststtt!</h1>

        <p>I hope you enjoy using basic-electron-react-boilerplate to start your dev off right!</p>

        <Editor
          value={json}
          onChange={this.handleChange}
        />
      </div>
    )
  }
}

export default App
