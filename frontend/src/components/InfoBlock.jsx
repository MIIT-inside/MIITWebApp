import React, { useEffect, useState } from 'react';
import { motion } from 'framer-motion';
import {useInView} from "react-intersection-observer";

export default function InfoBlock({ title, description }) {
    const { ref, inView } = useInView({
        threshold: 0.5,
        triggerOnce: true,
    });

    const numberMatch = title.match(/(\d+)/);
    const isNumber = numberMatch !== null;
    const displayTitle = isNumber ? title.replace(numberMatch[0], '') : title;
    const numberValue = isNumber ? parseInt(numberMatch[0]) : 0;

    return (
        <div className="flex flex-col space-y-5" ref={ref}>
            <span className="text-3xl font-bold">
                {displayTitle}
                {isNumber && inView ? (
                    <AnimatedNumber value={numberValue} />
                ) : (
                    numberMatch ? numberMatch[0] : ''
                )}
            </span>
            <span className="text-[#1E1E1E]">{description}</span>
        </div>
    );
}

export const AnimatedNumber = ({ value }) => {
    const [displayValue, setDisplayValue] = useState(0);

    useEffect(() => {
        const startValue = 0;
        const endValue = value;
        const duration = 2000;
        const frameDuration = 1000 / 60;
        const totalFrames = Math.round(duration / frameDuration);
        let currentFrame = 0;

        const increment = (endValue - startValue) / totalFrames;

        const interval = setInterval(() => {
            currentFrame++;
            setDisplayValue(prev => Math.min(prev + increment, endValue));

            if (currentFrame >= totalFrames) {
                clearInterval(interval);
                setDisplayValue(endValue);
            }
        }, frameDuration);

        return () => clearInterval(interval);
    }, [value]);

    return <motion.span>{Math.floor(displayValue)}</motion.span>;
};

