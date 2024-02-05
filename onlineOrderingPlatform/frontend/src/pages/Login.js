import '../styles/Login.css';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


const Login = ({ setIsLoggedIn }) => {
    const api = 'http://localhost:9001/api/v1/users';
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loginError, setLoginError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
    
        axios.get(`${api}/authenticate`, {
            params: {
                username,
                password,
            },
        })
            .then(response => {
                if (response.status === 200) {
                    console.log('Login successful')
                    setIsLoggedIn(true);
                    navigate('/indexLoggedIn');
                } else {
                    console.error('Login failed');
                }
            })
            .catch(error => {
                console.error('Error during login:', error)
                setLoginError('Invalid username or password')
            });
    };

    return(
        <div>
            <div>
                <h2>
                    Login
                </h2>
            </div>

            <div className="login-form">
                {loginError && <p style={{ color: 'red' }}>{loginError}</p>}

                <form onSubmit={handleLogin} method="post">
                <fieldset>
                    <label htmlFor="username">Username:</label>
                    <input type="text" id="username" name="username" value={username} onChange={(e) => setUsername(e.target.value)} required />

                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                </fieldset>

                <button type="submit" >Login</button>
                </form>
            </div>

            <div className="register-link">
                <p>Don't have an account yet? <a href="registerPage.html">Register here</a>.</p>
            </div>
        </div>
    )
}

export default Login;