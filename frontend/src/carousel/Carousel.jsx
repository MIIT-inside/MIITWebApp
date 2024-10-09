import {useEffect, useState, Children, cloneElement} from 'react';
import {FaChevronLeft, FaChevronRight} from 'react-icons/fa';
import './Carousel.css';
import React from 'react'

export const Carousel = ({children}) => {
    const [pages, setPages] = useState([]);
    const [offset, setOffset] = useState(0);
    const [pageWidth, setPageWidth] = useState(window.innerWidth * 0.65);

    const handleLeftArrowClick = () => {
        setOffset((currentOffset) => {
            const newOffset = currentOffset + pageWidth;
            return Math.min(newOffset, 0);
        });
    };

    const handleRightArrowClick = () => {
        setOffset((currentOffset) => {
            const newOffset = currentOffset - pageWidth;
            const maxOffset = -(pageWidth * (pages.length - 1));
            return Math.max(newOffset, maxOffset);
        });
    };

    // Обновляем ширину карточек при изменении размера окна
    useEffect(() => {
        const handleResize = () => {
            setPageWidth(window.innerWidth * 0.65);
        };

        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    useEffect(() => {
        setPages(Children.map(children, (child) => {
            return cloneElement(child, {
                style: {
                    minWidth: `${pageWidth}px`, maxWidth: `${pageWidth}px`, height: '100%',
                },
            });
        }));
    }, [children, pageWidth]);

    return (
        <div className="main-container">
            <FaChevronLeft className="arrow" onClick={handleLeftArrowClick}/>
            <div className="window">
                <div
                    className="all-pages-container"
                    style={{
                        transform: `translateX(${offset}px)`,
                    }}
                >
                    {pages}
                </div>
            </div>
            <FaChevronRight className="arrow" onClick={handleRightArrowClick}/>
        </div>);
};
