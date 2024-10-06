import { useEffect, useState, Children, cloneElement } from 'react';
import { FaChevronLeft, FaChevronRight } from 'react-icons/fa';
import './Carousel.css';

export const Carousel = ({ children }) => {
    const [pages, setPages] = useState([]);
    const [offset, setOffset] = useState(0);
    const [pageWidth, setPageWidth] = useState(window.innerWidth * 0.65);  // Задаем ширину как 90% от ширины экрана

    const handleLeftArrowClick = () => {
        setOffset((currentOffset) => {
            const newOffset = currentOffset + pageWidth;
            return Math.min(newOffset, 0);  // Ограничение на скролл влево
        });
    };

    const handleRightArrowClick = () => {
        setOffset((currentOffset) => {
            const newOffset = currentOffset - pageWidth;
            const maxOffset = -(pageWidth * (pages.length - 1));  // Ограничение на скролл вправо
            return Math.max(newOffset, maxOffset);  // Не даем прокрутить дальше последней карточки
        });
    };

    // Обновляем ширину карточек при изменении размера окна
    useEffect(() => {
        const handleResize = () => {
            setPageWidth(window.innerWidth * 0.65);  // Обновляем ширину при изменении размера окна
        };

        window.addEventListener('resize', handleResize);  // Добавляем обработчик на событие resize

        // Удаляем обработчик при размонтировании компонента
        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    useEffect(() => {
        setPages(
            Children.map(children, (child) => {
                return cloneElement(child, {
                    style: {
                        minWidth: `${pageWidth}px`,
                        maxWidth: `${pageWidth}px`,
                        height: '100%',
                    },
                });
            })
        );
    }, [children, pageWidth]);

    return (
        <div className="main-container">
            <FaChevronLeft className="arrow" onClick={handleLeftArrowClick} />
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
            <FaChevronRight className="arrow" onClick={handleRightArrowClick} />
        </div>
    );
};
