import React from "react";

export default function Header() {
    return (
        <div className="flex bg-white w-full px-16 py-7 justify-between items-center z-40 top-0 fixed">
            <div className="text-3xl text-[#3A5BCC] font-bold">РУТ</div>
            <div className="text-[#1E1E1E] space-x-5">
                <span>Специальности</span>
                <span>Профориентация</span>
                <span>Часто задаваемые воопросы</span>
            </div>
            <button className="px-5 py-2 bg-[#3A5BCC] text-white">Поиск</button>
        </div>
    )
}
