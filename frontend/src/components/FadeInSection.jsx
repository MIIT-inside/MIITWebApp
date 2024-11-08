import React from "react";
import {useInView} from "react-intersection-observer";
import {motion} from "framer-motion";

export const FadeInSection = ({children, threshold = 0.8, duration = 1}) => {
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