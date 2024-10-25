import React from 'react';
import Header from "../components/Header";
import InfoBlock from "../components/InfoBlock";
import Accordion from "../components/Accordion";
import image1 from "../assets/tenweb_media_zhurFxyM.webp"
import BlueCard from "../components/BlueCard";
import {motion, useScroll, useTransform} from "framer-motion";
import {useInView} from "react-intersection-observer";

const FadeInSection = ({children, threshold = 0.8, duration = 1}) => {
    const {ref, inView} = useInView({
        triggerOnce: true, threshold: threshold,
    });

    return (<motion.div
            ref={ref}
            initial={{opacity: 0, y: 50}}
            animate={inView ? {opacity: 1, y: 0} : {}}
            transition={{duration: duration, ease: "easeOut"}}>
            {children}
        </motion.div>);
};

export default function MainPage() {
    const {scrollY} = useScroll();
    const scale = useTransform(scrollY, [0, 500], [1, 1.1]);

    return (<div>
        <Header/>
            <motion.div
                style={{scale}}
                className="h-screen relative bg-[url('./src/assets/image_2024-10-22_12-22-33.png')] bg-fixed bg-cover">
                <div className="absolute inset-0 bg-black opacity-60"></div>

                <div
                    className="relative flex flex-col w-[47%] m-auto space-y-6 items-center justify-center size-full border-0 rounded-xl pt-16">
                            <span
                                className="flex text-center text-4xl text-white font-bold">Добро пожаловать в РУТ МИИТ</span>
                    <span className="text-center text-xl text-white">Откройте для себя возможности, которые предлагает наш университет. Узнайте больше о наших специальностях и карьерных перспективах.</span>
                    <div className="flex space-x-5">
                        <button className="bg-white px-5 py-3 rounded">Подробнее</button>
                        <button className="bg-none px-5 py-3 border rounded text-white">Контакты</button>
                    </div>
                </div>
            </motion.div>
        <FadeInSection>
            <div className="flex justify-between mt-20 mx-auto max-w-[1280px]">
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
        </FadeInSection>
        <FadeInSection>
            <div className="flex flex-col mt-20 justify-center items-center space-y-5 mx-auto max-w-[1280px]">
                <span className="text-4xl font-bold">Навигация по курсам</span>
                <span className="text-xl text-[#1E1E1E]">Выберите факультет, чтобы увидеть доступные курсы.</span>
            </div>
            <div className=" mt-12 mx-auto max-w-[1280px]">
                <Accordion/>
            </div>
        </FadeInSection>
        <div className="bg-[#F3F4F6]">
            <div className="flex mt-16 pt-16 pspace-x-8 justify-between mx-auto max-w-[1280px]">
                <div className="flex-col w-[50%]">
                    <FadeInSection>
                    <div className="flex flex-col space-y-14">
                            <div className="text-4xl font-bold">About РУТ(МИИТ)</div>
                            <div className="text-xl text-[#1E1E1E]">РУТ(МИИТ) is dedicated to enhancing the navigation experience for prospective students. Our goal is to provide comprehensive information about courses and career paths, ensuring students make informed decisions about their future.</div>
                    </div>
                    </FadeInSection>
                    <FadeInSection>
                        <div className="grid grid-cols-2 gap-5 mt-14">
                            <InfoBlock title="100%"
                                       description="We aim to increase the navigation level of our website by 100%, making it easier for students to find the information they need about their chosen specialties."/>
                            <InfoBlock title="5000 students"
                                       description="Over 5000 students have successfully navigated our courses, benefiting from tailored guidance and support throughout their educational journey."/>
                            <InfoBlock title="200 courses"
                                       description="We offer more than 200 specialized courses, each designed to equip students with the knowledge and skills necessary for their future careers."/>
                            <InfoBlock title="150 facilities"
                                       description="Our state-of-the-art facilities support over 150 practical training sessions annually, ensuring students gain hands-on experience in their fields."/>
                        </div>
                    </FadeInSection>
                </div>

                <div className="w-[50%] ml-12">
                    <FadeInSection threshold={0.2}>
                        <img src={image1} alt="" className="w-full h-auto"/>
                    </FadeInSection>
                </div>

            </div>
            <FadeInSection>
            <div className="flex justify-between mt-28 mx-auto max-w-[1280px] pb-16">
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
            </FadeInSection>
        </div>
        <FadeInSection>
        <div className="flex flex-col mt-20 justify-center items-center space-y-5 mx-auto max-w-[1280px]">
            <span className="text-4xl font-bold">Индивидуальные достижения</span>
            <span className="text-xl text-[#1E1E1E]">Дополнительные баллы за достижения</span>
        </div>
        </FadeInSection>
        <FadeInSection threshold={0.4}>
        <div className="mx-auto max-w-[1280px] mt-14 grid grid-cols-3 gap-6">
            <BlueCard/>
            <BlueCard/>
            <BlueCard/>
            <BlueCard/>
            <BlueCard/>
            <BlueCard/>
        </div>
        </FadeInSection>
    </div>)
}
