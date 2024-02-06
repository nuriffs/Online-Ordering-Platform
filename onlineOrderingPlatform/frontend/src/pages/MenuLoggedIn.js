import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/MenuLoggedIn.css';

const MenuLoggedIn = ({ userId }) => {
  const api = 'http://localhost:9001/api/v1/menu';
  const cartApi = `http://localhost:9001/api/v1/cart/${userId}/{cartItemId}`;
  const [menuItems, setMenuItems] = useState([]);
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    axios.get(api)
      .then(response => {
        setMenuItems(response.data);
      })
      .catch(error => {
        console.error('Error fetching menu items:', error);
      });
  }, [api]);

  useEffect(() => {
    axios.get(cartApi)
        .then(response => {
            setCartItems(response.data);
        })
        .catch(error => {
            console.error('Error fetching cart items:', error);
        });
}, [cartApi]);


    const addToCart = (foodId) => {
        axios.post(cartApi, { foodId })
            .then(response => {
                console.log(`Item ${foodId} added to the cart`);
                setCartItems([...cartItems, response.data]);
            })
            .catch(error => {
                console.error('Error adding item to the cart:', error);
            });
    };

  const addToFavorites = (foodId) => {
    const favouritesApi = `http://localhost:9001/api/v1/favorites/${userId}/add/${foodId}`;

    axios.post(favouritesApi, { foodId })
      .then(() => {
        console.log(`Item ${foodId} added to favorites`);
      })
      .catch(error => {
        console.error('Error adding item to favorites:', error);
      });
  };

  return (
    <div>
      <h2>Menu</h2>

      <ul style={{ listStyle: 'none' }}>
        {menuItems.map((menuItem) => (
          <li key={menuItem.foodId}>
            <div>
              <strong>{menuItem.foodName}</strong> - ${menuItem.price}
            </div>
            <div>{menuItem.description}</div>
            <button onClick={() => addToCart(menuItem.foodId)}>Add to Cart</button>
            <button onClick={() => addToFavorites(menuItem.foodId)}>Add to Favorites</button>
          </li>
        ))}
      </ul>

      {/* FOR TESTING! */} 
      <h2>Items in Cart</h2>

            <ul style={{ listStyle: 'none' }}>
                {cartItems.map((cartItem) => (
                    <li key={cartItem.foodId}>
                        <div>
                            <strong>{cartItem.foodName}</strong> - ${cartItem.price}
                        </div>
                        <div>
                        Quantity: {cartItem.quantity}
                        </div>
                    </li>
                ))}
            </ul>
      
    </div>
  );
};

export default MenuLoggedIn;