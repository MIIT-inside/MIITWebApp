import React from 'react';
import Header from "../components/Header";
import InfoBlock from "../components/InfoBlock";
import Dropdown from "../components/Accordion";
import Accordion from "../components/Accordion";

export default function MainPage() {
    return (
        <div>
            <Header/>
            <div className="h-screen relative bg-[url('./src/assets/image_2024-10-22_12-22-33.png')] bg-fixed bg-cover">
                <div className="absolute inset-0 bg-black opacity-60"></div>
                <div
                    className="relative flex flex-col w-[47%] m-auto space-y-6 items-center justify-center size-full border-0 rounded-xl pt-16">
                    <span className="flex text-center text-4xl text-white font-bold">Добро пожаловать в РУТ МИИТ</span>
                    <span className="text-center text-xl text-white">Откройте для себя возможности, которые предлагает наш университет. Узнайте больше о наших специальностях и карьерных перспективах.</span>
                    <div className="flex space-x-5">
                        <button className="bg-white px-5 py-3 rounded">Подробнее</button>
                        <button className="bg-none px-5 py-3 border rounded text-white">Контакты</button>
                    </div>
                </div>
            </div>
            <div className="flex justify-between mt-16 mx-16 items-center">
                <span className="text-4xl font-bold">Обзор наших курсов</span>
                <div className="flex flex-col w-[50%] space-y-7">
                    <span className="text-xl text-[#1E1E1E]">Наши курсы предлагают уникальные возможности для студентов, стремящихся к успешной карьере. Узнайте больше о каждом курсе, его учебной программе и карьерных перспективах.</span>
                    <div className="flex space-x-4">
                        <InfoBlock title="Карьера"
                                   description="Персонализированные консультации и доступ к детальной информации о различных специальностях."/>
                        <InfoBlock title="Учебная программа"
                                   description="Подробные описания курсов и их значимость на рынке труда."/>
                    </div>
                </div>
            </div>
            <div className="flex flex-col mt-20 justify-center items-center space-y-5">
                <span className="text-4xl font-bold">Навигация по курсам</span>
                <span className="text-xl text-[#1E1E1E]">Выберите факультет, чтобы увидеть доступные курсы.</span>
            </div>
            <div className="mx-16 mt-12">
                <Accordion/>
            </div>
        </div>
    )
}