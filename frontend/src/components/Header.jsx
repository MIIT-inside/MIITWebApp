import React from "react";
import logo from "../assets/RUT_rus.svg"
import {Link} from "react-router-dom";

export default function Header() {
    return (
        <div className="flex bg-white w-full px-16 py-7 justify-between items-center z-40 top-0 fixed">
            <img src={logo} alt="логотип" className="w-[10rem]"/>
            <div className="text-[#1E1E1E] space-x-5">
                <Link to="/specialties" className="hover:underline">Специальности</Link>
                <Link to="/faq" className="hover:underline">Часто задаваемые вопросы</Link>
                <Link to="/main" className="hover:underline">Главная</Link>
            </div>
            <button className="px-5 py-2 bg-[#3A5BCC] text-white">Поиск</button>
        </div>
    )
}
