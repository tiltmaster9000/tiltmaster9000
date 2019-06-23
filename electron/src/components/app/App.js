import React from 'react'
import 'tachyons'
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider'
import {createMuiTheme} from '@material-ui/core/styles'

import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import HomeIcon from '@material-ui/icons/Home';
import FileIcon from '@material-ui/icons/Class';
import AddIcon from '@material-ui/icons/AddBox';

import './App.css'
import MuiTheme from './MuiTheme'
import Main from '../main/Main'
import TiltFileEditor from '../tiltfile_editor/TiltFileEditor'

const drawerTheme = createMuiTheme({
	overrides: {
		MuiDrawer: {
			paperAnchorDockedLeft: {
				width: '75px',
				overflow: 'hidden',
				background: '#202225'
			}
		},
		MuiDivider: {
			root: {
				backgroundColor: '#2F3136',
				width: '50px',
				height: '2px',
				marginRight: 'auto',
				marginLeft: 'auto'
			}
		},
		MuiListItem: {
			root: {
				justifyContent: 'flex-center'
			}
		},
		MuiListItemIcon: {
			root: {
				minWidth: '0px',
				color: 'white'
			}
		},
		MuiButtonBase: {
			root: {
				color: 'white'
			}
		},
		MuiSvgIcon: {
			root: {
				fontSize: '35px'
			}
		}
	}
})

export default class App extends React.Component {

	constructor(props) {
		super(props);

		this.content = [{
			"test1": 2
		}, {
			"test2": 3
		}, {
			"greeting": "hello world"
		}]

		this.state = {
			view: 0
		}
	}

	onHomeScreenClick = () => {
		this.setState({
			view: 0
		})
	}

	onJsonEditorClick = (index) => {
		this.setState({
			view: index + 1
		})
	}

	displayMainContent = (id) => {
		if (id === 0)
			return this.displayHomeScreen()

		if (id > 0 && id <= this.content.length)
			return this.displayTiltFileEditor(this.content[id - 1])
	}

	displayHomeScreen = () => {
		return ( <Main /> )
	}

	displayTiltFileEditor = (json) => {
		return ( <TiltFileEditor json={json}/> )
	}

	displayAddMenu = () => {
		console.log("add menu")
	}

	generateDrawerItems = items => {
		return (
			<List>
				{items.map((text, index) => (
					<ListItem button key={index}
										disableRipple={true}
										disableTouchRipple={true}
										focusVisibleClassName="tfc"
										onClick={() => this.onJsonEditorClick(index)}
					>
						<ListItemIcon><FileIcon /></ListItemIcon>
					</ListItem>
				))}
			</List>
		)
	}

	render() {
		const drawerItems = this.generateDrawerItems(this.content)

		return (
			<div className="app">
				<MuiTheme>
					<div>
						<MuiThemeProvider theme={drawerTheme}>
							<Drawer variant="permanent" anchor="left">
								<IconButton onClick={this.onHomeScreenClick}>
									<HomeIcon />
								</IconButton>
								<Divider/>
								{drawerItems}
								<IconButton onClick={this.displayAddMenu}>
									<AddIcon />
								</IconButton>
							</Drawer>
						</MuiThemeProvider>

						<main style={{paddingLeft: "75px"}}>
							{this.displayMainContent(this.state.view)}
						</main>
					</div>
				</MuiTheme>
			</div>
		)
	}
}
