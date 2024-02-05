import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ViewMenu = () => {

    const api = 'http://localhost:9001/api/v1/menu';
    const [menuItems, setMenuItems] = useState([]);

    useEffect(() => {
        axios.get(api)
            .then(response => {
                setMenuItems(response.data);
            })
            .catch(error => {
                console.error('Error fetching menu items:', error);
            });
    }, [api]);

    console.log('menuItems:', menuItems);
    
    return(
        <div>
            <h2>View the Menu option</h2>

            <ul style={{ listStyle: 'none' }}>
                {menuItems.map((menuItem) => (
                <li key={menuItem.foodId}>
                    <div>
                        <strong>{menuItem.foodName}</strong> - ${menuItem.price}
                    </div>
                    <div>{menuItem.description}</div>
                </li>
                ))}
            </ul>
        </div>
    )
}

export default ViewMenu;