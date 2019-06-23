import React from 'react'
import 'tachyons'

import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import HomeIcon from '@material-ui/icons/Home';
import FileIcon from '@material-ui/icons/Class';
import AddIcon from '@material-ui/icons/Add';

import './App.css'
import Main from './Main.js'
import JsonEditor from './JsonEditor.js'

export default class App extends React.Component {
	render() {
		return (
			<div className="app">
				<Main/>

				<Drawer
					variant="permanent"
					open={open}
				>
					<div>
						<IconButton>
							<HomeIcon/>
						</IconButton>
					</div>
					<Divider/>
					<List>
						{['All mail', 'Trash', 'Spam'].map(text => (
							<ListItem button key={text}>
								<ListItemIcon><FileIcon/></ListItemIcon>
							</ListItem>
						))}
					</List>
					<div>
						<IconButton>
							<AddIcon/>
						</IconButton>
					</div>
				</Drawer>

				<JsonEditor/>
			</div>
		)
	}
}
