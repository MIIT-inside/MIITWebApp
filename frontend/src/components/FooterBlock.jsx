import React from 'react';
import telegram from "../assets/telegram-svgrepo-com.svg"
import vk from "../assets/vk-v2-svgrepo-com.svg"
import { Link } from "react-router-dom"
import { Item } from './footerBlockComponent';

const FooterBlock = () => {
    return (
        <footer className="bg-gray-100 py-10 md:py-20">
            <div className="max-w-[1280px] mx-auto px-4">
                <div
                    className="flex flex-col md:flex-row md:justify-between text-center md:text-left items-center md:items-start">
                    <div className="text-3xl mb-4 md:mb-0">РУТ(МИИТ)</div>
                    <div className="flex flex-col space-y-3 md:space-y-0 md:space-x-5 md:flex-row items-center">
                        <Link to="/specialities" className="hover:underline">Специальности</Link>
                        <Link to="/faq" className="hover:underline">Часто задаваемые вопросы</Link>
                        <Link to="/main" className="hover:underline">Главная</Link>
                    </div>
                    <div
                        className="flex flex-col space-y-3 md:space-y-0 md:flex-row items-center space-x-0 md:space-x-5 mt-5 md:mt-0">
                        <a href="#" className="flex items-center space-x-2">
                            <img src={vk} alt="vk" className="w-6 h-auto"/>
                            <span>Вконтакте</span>
                        </a>
                        <a href="#" className="flex items-center space-x-2">
                            <img src={telegram} alt="telegram" className="w-6 h-auto"/>
                            <span>Телеграм</span>
                        </a>
                    </div>
                </div>
                <hr className="my-8 border-black"/>
                <div
                    className="flex flex-col md:flex-row md:justify-between text-gray-600 text-[14px] space-y-4 md:space-y-0">
            <span className="md:w-1/2">
                2024 РУТ(МИИТ). Все права защищены. Мы стараемся предоставить абитуриентам полную информацию о наших курсах и помочь им в выборе специальности.
            </span>
                    <span className="text-center md:text-left">Связь с разработчиками</span>
                </div>
            </div>
        </footer>

    );
};
export default FooterBlock;