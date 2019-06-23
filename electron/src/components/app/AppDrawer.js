import React from 'react'
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
				minWidth: '0px'
			}
		}
	}
})

export default class AppDrawer extends React.Component {

	constructor(props) {
		super(props);

	}

	_generateDrawerItems = items => {
		return (
			<List>
				{items.map(text => (
					<ListItem button key={text}>
						<ListItemIcon><FileIcon style={{color: "white", fontSize: "35px"}}/></ListItemIcon>
					</ListItem>
				))}
			</List>
		)
	}

	render() {
		const drawerItems = this._generateDrawerItems(['item1', 'item2'])

		return (
			<div>
				<MuiThemeProvider theme={drawerTheme}>
					<Drawer variant="permanent" anchor="left">
						<IconButton>
							<HomeIcon style={{color: "white", fontSize: "35px"}}/>
						</IconButton>
						<Divider />
						{drawerItems}
						<IconButton>
							<AddIcon style={{color: "white", fontSize: "35px"}}/>
						</IconButton>
					</Drawer>
				</MuiThemeProvider>
			</div>
		)
	}
}
