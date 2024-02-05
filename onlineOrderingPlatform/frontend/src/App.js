import './App.css';
import { Route, Routes } from 'react-router-dom'
import Name from './components/Name'
import Navbar from './components/Navbar'
import AboutUs from './pages/AboutUs'
import ViewMenu from './pages/ViewMenu'
import Contact from './pages/Contact'
import Login from './pages/Login'
import IndexLoggedIn from './pages/IndexLoggedIn'
import NavbarLoggedIn from './components/NavbarLoggedIn'
import MenuLoggedIn from './pages/MenuLoggedIn'
import Favourites from './pages/Favourites'
import { useState } from 'react';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false)

  return (
      <div className="App">
        <Name />

        {isLoggedIn ? <NavbarLoggedIn setIsLoggedIn={setIsLoggedIn}/> : <Navbar />}

        <Routes>
          <Route path="/aboutUs" element={<AboutUs />} />
          <Route path="/viewMenu" element={<ViewMenu />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login setIsLoggedIn = {setIsLoggedIn}/>} />

          <Route path="/indexLoggedIn" element={<IndexLoggedIn />} />
          <Route path="/menuLoggedIn" element={<MenuLoggedIn />} />
          <Route path="/favourites" element={<Favourites />} />
        </Routes>
      </div>
  )
}

export default App;
