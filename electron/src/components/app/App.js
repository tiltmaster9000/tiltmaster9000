import React from 'react'
import 'tachyons'

import './App.css'
import MuiTheme from './MuiTheme'

import AppDrawer from './AppDrawer'
import Main from '../Main'
import JsonEditor from '../json_editor/JsonEditor'

export default class App extends React.Component {
	render() {

		return (
			<div className="app">
				<MuiTheme>
					<AppDrawer/>
					<Main/>
					<JsonEditor/>
				</MuiTheme>
			</div>
		)
	}
}
