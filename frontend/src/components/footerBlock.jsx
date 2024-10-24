import React from 'react';
import { Item } from './universal/footerBlockComponent';

const FooterBlock = () => {
    return (
        <footer className="bg-gray-100 py-20">
            <div className="container mx-auto text-center">
                <h1 className="text-3xl font-serif mb-4">РУТ(МИИТ)</h1>
                <div className="flex justify-center space-x-4 mb-4">
                    <Item textItem="Специальности" />
                    <Item textItem="Профориентация" />
                    <Item textItem="Часто Задаваемые Вопросы" />
                    <Item textItem="Service-career-guidance-services" />
                </div>
                <hr className="border-gray-300 my-4" />
                <p className="text-gray-600 text-sm">
                    @ 2024 РУТ(МИИТ). Все права защищены. Мы стремимся предоставить абитуриентам полную информацию о наших курсах и помочь им в выборе специальности.
                </p>
                <div className="flex justify-center space-x-4 mt-4">
                    <Item textItem="Политика Конфиденциальности" />
                    <Item textItem="Условия Использования" />
                </div>
            </div>
        </footer>
    );
};
export default FooterBlock;