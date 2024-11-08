import React, { useState } from 'react';
import { motion } from 'framer-motion';
import { FadeInSection } from "./FadeInSection";

export default function AccordionComponent({ textTheme, description }) {
    const [isOpen, setIsOpen] = useState(false);

    const handleToggle = () => {
        setIsOpen(!isOpen);
    };

    return (
        <FadeInSection>
            <div className="border-b border-gray-700">
                <button
                    onClick={handleToggle}
                    className="flex text-start justify-between w-full text-lg font-semibold cursor-pointer py-4">
                    <span>{textTheme}</span>
                    <span className={`transform transition-transform duration-200 ${isOpen ? 'rotate-90' : 'rotate-0'}`}>
                        &gt;
                    </span>
                </button>
                <motion.div
                    initial={{ height: 0, opacity: 0 }}
                    animate={{ height: isOpen ? 'auto' : 0, opacity: isOpen ? 1 : 0 }}
                    transition={{ duration: 0.3, ease: "easeInOut" }}
                    className="overflow-hidden"
                >
                    <p className="text-start text-gray-700 py-2">
                        {description}
                    </p>
                </motion.div>
            </div>
        </FadeInSection>
    );
};
