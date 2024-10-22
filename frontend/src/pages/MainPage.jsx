import React from 'react';
import Header from "../components/Header";

export default function MainPage() {
    return (
        <div>
            <Header/>
            <div className="h-screen relative bg-[url('./src/assets/image_2024-10-22_12-22-33.png')] bg-fixed bg-cover">
                <div className="absolute inset-0 bg-black opacity-60"></div>
                <div
                    className="relative flex flex-col w-[47%] m-auto space-y-6 items-center justify-center size-full border-0 rounded-xl">
                    <span className="flex text-center text-4xl text-white font-bold">Добро пожаловать в РУТ МИИТ</span>
                    <span className="text-center text-xl text-white">Откройте для себя возможности, которые предлагает наш университет. Узнайте больше о наших специальностях и карьерных перспективах.</span>
                    <div className="flex space-x-5">
                        <button className="bg-white px-5 py-3 rounded">Подробнее</button>
                        <button className="bg-none px-5 py-3 border rounded text-white">Контакты</button>
                    </div>
                </div>
            </div>
            <div className="flex justify-between mt-16 mx-16">
                <span className="text-4xl font-bold">Обзор наших курсов</span>
                <div className="flex flex-col w-[50%]">
                    <span className="text-xl text-[#1E1E1E]">Наши курсы предлагают уникальные возможности для студентов, стремящихся к успешной карьере. Узнайте больше о каждом курсе, его учебной программе и карьерных перспективах.</span>
                    <div className="flex">

                    </div>
                </div>
            </div>
        </div>
    )
}