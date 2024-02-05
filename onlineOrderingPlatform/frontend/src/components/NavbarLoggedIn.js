import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import '../styles/Navbar.css'

const NavbarLoggedIn = ({ setIsLoggedIn }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        console.log('Logout successful')
        setIsLoggedIn(false);
        navigate('/aboutUs');
    }

    return(
        <div>
            <nav className="navbar">
                <Link to={'/menuLoggedIn'}>Menu</Link>
                <Link to={'/favourites'}>Favourites</Link>
            </nav>

            <div className="top-right-container">
                <button id="cartButton">Cart</button>
                <button id="logoutButton" onClick={handleLogout}>
                    Logout
                </button>
            </div>
        </div>
    )
}

export default NavbarLoggedIn;