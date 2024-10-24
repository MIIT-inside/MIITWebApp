import React, { useState, useRef } from 'react';

const Accordion = () => {
    const [openSection, setOpenSection] = useState(null);
    const contentRef = useRef(null);

    const toggleSection = (index) => {
        setOpenSection(openSection === index ? null : index);
    };

    return (
        <div className="w-full">
            {/* Факультет 1 */}
            <div className="border p-4 border-black">
                <button
                    onClick={() => toggleSection(1)}
                    className="w-full text-2xl flex justify-between items-center font-bold">
                    Факультет Информационных Технологий
                    {openSection === 1 ? (
                        <span className="text-2xl">-</span>) : (<span className="text-2xl">+</span>)}
                </button>
                <div
                    ref={contentRef}
                    className={`overflow-hidden transition-[max-height] duration-500 ease-in-out ${
                        openSection === 1 ? 'max-h-96' : 'max-h-0'
                    }`}
                    style={{ maxHeight: openSection === 1 ? `${contentRef.current.scrollHeight}px` : '0' }}>
                    <div className="px-4 py-6">
                        <ul className="list-disc pl-5">
                            <li>Программирование</li>
                            <li>Кибербезопасность</li>
                            <li>Искусственный интеллект</li>
                        </ul>
                    </div>
                </div>
            </div>

            {/* Факультет 2 */}
            <div className="border border-t-0 p-4 border-black">
                <button
                    onClick={() => toggleSection(2)}
                    className="w-full text-2xl flex justify-between items-center font-bold">
                    Факультет Экономики и управления
                    {openSection === 2 ? (
                        <span className="text-2xl">-</span>) : (<span className="text-2xl">+</span>)}
                </button>
                <div
                    ref={contentRef}
                    className={`overflow-hidden transition-[max-height] duration-500 ease-in-out ${
                        openSection === 2 ? 'max-h-96' : 'max-h-0'
                    }`}
                    style={{ maxHeight: openSection === 2 ? `${contentRef.current.scrollHeight}px` : '0' }}>
                    <div className="px-4 py-6">
                        <ul className="list-disc pl-5">
                            <li>Программирование</li>
                            <li>Кибербезопасность</li>
                            <li>Искусственный интеллект</li>
                        </ul>
                    </div>
                </div>
            </div>

            {/* Факультет 3 */}
            <div className="border border-t-0 p-4 border-black">
                <button
                    onClick={() => toggleSection(3)}
                    className="w-full text-2xl flex justify-between items-center font-bold">
                    Факультет Транспортных технологий
                    {openSection === 3 ? (
                        <span className="text-2xl">-</span>) : (<span className="text-2xl">+</span>)}
                </button>
                <div
                    ref={contentRef}
                    className={`overflow-hidden transition-[max-height] duration-500 ease-in-out ${
                        openSection === 3 ? 'max-h-96' : 'max-h-0'
                    }`}
                    style={{ maxHeight: openSection === 3 ? `${contentRef.current.scrollHeight}px` : '0' }}>
                    <div className="px-4 py-6">
                        <ul className="list-disc pl-5">
                            <li>Программирование</li>
                            <li>Кибербезопасность</li>
                            <li>Искусственный интеллект</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Accordion;
