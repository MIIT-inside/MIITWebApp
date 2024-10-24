import React from 'react';
import {Accordion} from "./universal/accordionComponent";

const HelpBlock = () => {
    return (
        <div className="container mx-auto py-20 space-y-20">
            <div className="flex justify-between items-start">
                <div className="w-1/2 pr-10">
                    <div className="text-3xl font-bold">Get Help</div>
                    <p className="text-base text-gray-700">
                        Have questions about admissions or courses? Our team is here to assist you in making informed decisions about your future at РУТ(МИИТ).
                    </p>
                </div>
                <div className="w-1/2">
                    <Accordion textTheme="Че там по специальности" description="Лучше заключи контракт с минобороной, все там будем"/>
                    <Accordion textTheme="Поддержка студентов" description="Словесная"/>
                    <Accordion textTheme="Есть стипендии" description="АХАХАХАХАХХАХАХААХАХАХАХАХА"/>
                    <Accordion textTheme="Какие перспективы" description="Туманные"/>
                </div>
            </div>
            <div className="flex justify-between items-start">
                <div className="w-1/2 pr-10">
                    <div className="text-3xl font-bold">Campus Life</div>
                    <p className="text-base text-gray-700 mt-2">
                        Discover essential information about life on campus, including housing, dining, and student activities. Your journey at РУТ(МИИТ) starts here!
                    </p>
                </div>
                <div className="w-1/2">
                    <Accordion textTheme="Варианты жилья" description="Подвал 4 корпуса"/>
                    <Accordion textTheme="Я могу принять участие в жизни университета" description="то, что мертво жить не может"/>
                    <Accordion textTheme="Питание студентов" description="перекресток через дорогу"/>
                    <Accordion textTheme="Трудоустрйство" description="Раздевалка первого корпуса"/>
                </div>
            </div>
        </div>
    );
}

export default HelpBlock;