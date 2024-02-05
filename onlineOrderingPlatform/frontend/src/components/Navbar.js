import { Link } from "react-router-dom";
import '../styles/Navbar.css'

const Navbar = () => {
    return(
        <div>
            <nav className="navbar">
                <Link to={'/aboutUs'}>About Us</Link>
                <Link to={'/viewmenu'}>Menu</Link>
                <Link to={'/contact'}>Contact Us</Link>
                <Link to={'/login'}>Login</Link>
            </nav>
        </div>
    )
}

export default Navbar;