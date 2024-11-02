import React from "react";
import logo from "../assets/RUT_rus.svg"

export default function Header() {
    return (
        <div className="flex bg-white w-full px-16 py-7 justify-between items-center z-40 top-0 fixed">
            <img src={logo} alt="логотип" className="w-[10rem]"/>
            <div className="text-[#1E1E1E] space-x-5">
                <a>Специальности</a>
                <a>Профориентация</a>
                <a>Часто задаваемые воопросы</a>
            </div>
            <button className="px-5 py-2 bg-[#3A5BCC] text-white">Поиск</button>
        </div>
    )
}
