import React from 'react'
import PropTypes from 'prop-types'
import {JsonEditor as Editor} from 'jsoneditor-react'
import 'jsoneditor-react/es/editor.min.css'

export default class TiltFileEditor extends React.Component {

	static propTypes = {
		json: PropTypes.object.isRequired
	}

	render() {
		return (
			<div>
				<Editor
					value={this.props.json}
					onChange={this.handleChange}
				/>
			</div>
		)
	}
}

