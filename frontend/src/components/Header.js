import React from 'react';
import '../styles/Header.css';
import HeaderLogo from "../assets/header_logo.svg";

const Header = () => {
    return (
        <header className="header">
            <div className="header--container">
                <div className="header--logo">
                    <img src={HeaderLogo} alt="Header logo"/>
                </div>
            </div>
        </header>
    )
}

export default Header;
