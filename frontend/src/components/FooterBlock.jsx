import React from 'react';
import telegram from "../assets/telegram-svgrepo-com.svg"
import vk from "../assets/vk-v2-svgrepo-com.svg"
import { Link } from "react-router-dom"
import { Item } from './footerBlockComponent';

const FooterBlock = () => {
    return (
        <footer className="bg-gray-100 py-20">
            <div className="max-w-[1280px] mx-auto">
                <div className="flex text-center justify-between">
                    <div className="text-3xl mb-4">РУТ(МИИТ)</div>
                    <div className="flex space-x-5 items-center">
                        <Item>
                            <Link to="/specialities" className="hover:underline">Специальности</Link>
                        </Item>

                            <Link to="/faq" className="hover:underline">
                                <Item textItem="Часто задаваемые вопросы"></Item>
                            </Link>

                            <Link to="/specialties" className="hover:underline">
                                <Item textItem="Специальности"></Item>
                            </Link>

                    </div>
                    <div className="flex items-center space-x-5">
                        <a href="https://vk.com/rutmiitvk?ysclid=m3dfo1zpcd324832534" target="_blank" className="flex items-center space-x-2">
                            <img src={vk} alt="vk" className="w-6 h-auto"/>
                            <span>Вконтакте</span>
                        </a>
                        <a href="https://t.me/s/rut_live" target="_blank" className="flex items-center space-x-2">
                            <img src={telegram} alt="telegram" className="w-6 h-auto"/>
                            <span>Телеграм</span>
                        </a>
                    </div>
                </div>
                <hr className="flex justify-center border-black"/>
                <div className="flex space-x-5 mt-10 text-[14px] text-gray-600 justify-between">
                    <span className="w-1/2">2024 РУТ(МИИТ). Все права защищены. Мы стараемся предоставить абитуриентам полную информацию о наших курсах и помочь им в выборе специальности</span>
                    <a href = "https://t.me/vnezapniy_losos" target="_blank">Связь с разработчиками</a>
                </div>
            </div>
        </footer>
    );
};
export default FooterBlock;