import React from 'react'
import { JsonEditor as Editor } from 'jsoneditor-react'
import 'jsoneditor-react/es/editor.min.css'

export default class JsonEditor extends React.Component {
    render() {
        let json = {
            'test': 1
        }
        return (
            <div>
                <Editor
                    value={json}
                    onChange={this.handleChange}
                />
            </div>
        )
    }
}

