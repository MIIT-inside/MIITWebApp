import React, {useState} from "react";
import logo from "../assets/RUT_rus.svg";
import {Link} from "react-router-dom";

export default function Header() {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <header
            className="bg-white w-full px-4 md:px-16 py-5 flex justify-between items-center fixed top-0 z-40 shadow-md">
            <img src={logo} alt="логотип" className="w-[8rem] md:w-[10rem]"/>

            <nav className="hidden lg:flex text-[#1E1E1E] space-x-5">
                <Link to="/main" className="hover:underline">Главная</Link>
                <Link to="/specialties" className="hover:underline">Специальности</Link>
                <Link to="/faq" className="hover:underline">Часто задаваемые вопросы</Link>
            </nav>
            <div className="flex space-x-4">
                <button className="hidden md:block px-5 py-2 bg-[#3A5BCC] text-white lg:inline">
                    Поиск
                </button>
                <div className="flex lg:hidden items-center space-x-4">
                    <button className="md:hidden px-4 py-2 bg-[#3A5BCC] text-white text-sm">
                        Поиск
                    </button>
                    <button
                        onClick={toggleMenu}
                        className="focus:outline-none text-[#1E1E1E]"
                    >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            fill="none"
                            viewBox="0 0 24 24"
                            strokeWidth={2}
                            stroke="currentColor"
                            className="w-8 h-8"
                        >
                            {isMenuOpen ? (
                                <path
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    d="M6 18L18 6M6 6l12 12"
                                />
                            ) : (
                                <path
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    d="M4 6h16M4 12h16m-7 6h7"
                                />
                            )}
                        </svg>
                    </button>
                </div>
            </div>
            {isMenuOpen && (
                <nav
                    className="absolute top-full right-0 bg-white shadow-md w-full flex flex-col space-y-4 px-6 py-4 md:hidden">
                    <Link to="/main" className="hover:underline text-[#1E1E1E]">Главная</Link>
                    <Link to="/specialties" className="hover:underline text-[#1E1E1E]">Специальности</Link>
                    <Link to="/faq" className="hover:underline text-[#1E1E1E]">Часто задаваемые вопросы</Link>
                </nav>
            )}
        </header>
    );
}
