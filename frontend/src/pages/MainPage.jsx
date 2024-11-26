import React, {useEffect, useState} from 'react';
import InfoBlock from "../components/InfoBlock";
import Accordion from "../components/Accordion";
import image1 from "../assets/inostr.jpg"
import BlueCard from "../components/BlueCard";
import {AnimatePresence, motion, useScroll, useTransform} from "framer-motion";
import axios from "axios";
import {FadeInSection} from "../components/FadeInSection";
import mainPage from "../assets/image_2024-10-22_12-22-33.png"

export default function MainPage() {
    const {scrollY} = useScroll();
    const scale = useTransform(scrollY, [0, 500], [1, 1.1]);
    const [achievements, setAchievements] = useState([]);
    const [visibleCount, setVisibleCount] = useState(6);

    useEffect(() => {
        axios.get('http://localhost:8080/api/miit/achievements/')
            .then(response => {
                const mappedData = response.data.map(item => ({
                    description: item.description,
                    countPoints: item.countPoints
                }));
                setAchievements(mappedData);
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    const showMore = () => setVisibleCount(prevCount => prevCount + 6);

    const showLess = () => setVisibleCount(6);

    return (<div>
            <motion.div
                className="h-screen relative overflow-hidden bg-fixed bg-cover">
                <motion.div
                    className="absolute inset-0 bg-cover bg-center"
                    style={{
                        backgroundImage: `url(${mainPage})`,
                        transformOrigin: 'center',
                        scale,
                    }}
                ></motion.div>
                <div className="absolute inset-0 bg-black opacity-60"></div>
                <div
                    className="relative flex flex-col w-full lg:w-[47%] mx-auto space-y-6 items-center justify-center size-full border-0 rounded-xl pt-16 px-4 text-center z-10">
                    <span className="text-2xl md:text-4xl text-white font-bold">Добро пожаловать в РУТ МИИТ</span>
                    <span className="text-lg md:text-xl text-white">
            Откройте для себя возможности, которые предлагает наш университет. Узнайте больше о наших специальностях и карьерных перспективах.
        </span>
                    <div className="flex flex-col md:flex-row space-y-4 md:space-y-0 md:space-x-5">
                        <button className="bg-white px-5 py-3 rounded">Подробнее</button>
                        <button className="bg-none px-5 py-3 border rounded text-white">Контакты</button>
                    </div>
                </div>
            </motion.div>

            <FadeInSection>
                <div className="flex flex-col lg:flex-row items-center text-center mt-20 mx-auto max-w-[1280px] px-4">
                    <span className="text-2xl md:text-4xl font-bold">Российский университет транспорта (МИИТ)</span>
                    <div className="flex flex-col w-full lg:w-[50%] space-y-7 mt-6">
                <span className="text-lg md:text-xl text-[#1E1E1E]">
                    Миссия Российского университета транспорта – быть интеллектуальным драйвером транспортной отрасли.
                </span>
                        <div
                            className="flex flex-col space-y-4 lg:space-y-0 md:space-y-0 md:flex-row md:space-x-4 justify-center">
                            <InfoBlock
                                title="Профили подготовки"
                                description="Подробная информация о профилях подготовки"/>
                            <InfoBlock
                                title="Учебная программа"
                                description="Подробные описания курсов на каждый семестр"/>
                        </div>
                    </div>
                </div>
            </FadeInSection>

            <FadeInSection>
                <div className="flex flex-col items-center text-center mt-20 mx-auto max-w-[1280px] px-4">
                    <span className="text-2xl md:text-4xl font-bold">Навигация по университету</span>
                    <span className="text-lg md:text-xl text-[#1E1E1E]">Выберите блок, чтобы увидеть доступную информацию.</span>
                </div>
                <div className="mt-12 mx-auto max-w-[1280px] px-4">
                    <Accordion/>
                </div>
            </FadeInSection>

            <div className="bg-[#F3F4F6]">
                <div
                    className="flex flex-col lg:flex-row items-center lg:items-start justify-center mt-16 pt-16 space-y-8 lg:space-y-0 lg:space-x-8 mx-auto max-w-[1280px] px-4">
                    <div className="flex-col w-full lg:w-[50%] text-center lg:text-left">
                        <FadeInSection>
                            <div className="flex flex-col space-y-14">
                                <div className="text-2xl md:text-4xl font-bold">Об университете</div>
                                <div className="text-lg md:text-xl text-[#1E1E1E]">
                                    Российский университет транспорта (МИИТ) — ведущий национальный транспортный вуз,
                                    крупнейший отраслевой университет России, базовая площадка для кадрового обеспечения
                                    и научного сопровождения развития транспортной отрасли.
                                </div>
                            </div>
                        </FadeInSection>
                        <FadeInSection>
                            <div className="grid items-center lg:grid-cols-2 md:grid-cols-2 mt-14">
                                <InfoBlock title="> 29000" description="студентов по программам высшего образования"/>
                                <InfoBlock title="> 440"
                                           description="образовательных программ высшего и среднего профессионального образования"/>
                                <InfoBlock title="> 1923" description="педагогических работника"/>
                                <InfoBlock title="> 2000" description="иностранных студентов из 57 стран"/>
                            </div>
                        </FadeInSection>
                    </div>

                    <div className="flex w-full lg:w-[60%] justify-center">
                        <FadeInSection threshold={0.2}>
                            <img src={image1} alt="" className="w-full h-auto"/>
                        </FadeInSection>
                    </div>
                </div>

                <FadeInSection>
                    <div
                        className="flex flex-col items-center text-center justify-center mt-28 mx-auto max-w-[1280px] pb-16 px-4">
                        <span className="text-2xl md:text-4xl font-bold">Обзор наших курсов</span>
                        <div className="flex flex-col w-full lg:w-[50%] space-y-7 mt-6">
                    <span className="text-lg md:text-xl text-[#1E1E1E]">
                        Профили подготовки предлагают уникальные возможности для студентов, стремящихся к успешной карьере. Узнайте больше о каждом курсе, его учебной программе и карьерных перспективах.
                    </span>
                            <div className="flex flex-col md:flex-row md:space-x-4 justify-center">
                                <InfoBlock
                                    title="Карьера"
                                    description="Доступ к детальной информации о различных специальностях."/>
                            </div>
                        </div>
                    </div>
                </FadeInSection>
            </div>

            <FadeInSection>
                <div className="flex flex-col items-center text-center mt-20 mx-auto max-w-[1280px] px-4">
                    <span className="text-2xl md:text-4xl font-bold">Индивидуальные достижения</span>
                    <span className="text-lg md:text-xl text-[#1E1E1E]">Дополнительные баллы за достижения</span>
                </div>
            </FadeInSection>

            <FadeInSection threshold={0.4}>
                <div className="mx-auto max-w-[1280px] mt-14 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 px-4">
                    <AnimatePresence>
                        {achievements.slice(0, visibleCount).map((achievement, index) => (
                            <motion.div
                                key={index}
                                initial={{opacity: 0, y: -30}}
                                animate={{opacity: 1, y: 0}}
                                exit={{opacity: 0, y: 20}}
                                transition={{duration: 0.8, ease: "easeOut"}}>
                                <BlueCard
                                    key={index}
                                    countPoints={achievement.countPoints}
                                    description={achievement.description}
                                />
                            </motion.div>
                        ))}
                    </AnimatePresence>
                </div>
                <div className="flex justify-center mt-8 mb-11">
                    {visibleCount < achievements.length && (
                        <button onClick={showMore} className="bg-[#3A5BCC] text-white px-6 py-3">
                            Показать еще
                        </button>
                    )}
                    {visibleCount > 6 && (
                        <button onClick={showLess} className="text-black bg-none px-6 py-3 ml-4 border border-black">
                            Скрыть
                        </button>
                    )}
                </div>
            </FadeInSection>
        </div>
    )
}
