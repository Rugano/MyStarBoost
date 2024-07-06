import React, { useState, useEffect } from 'react';

const DateOfBirth = React.forwardRef(({ updateDob }, ref) => {
    const [day, setDay] = useState('');
    const [month, setMonth] = useState('');
    const [year, setYear] = useState('');

    useEffect(() => {
        ref.current = {
            reset: () => {
                setDay('');
                setMonth('');
                setYear('');
            }
        };
    }, [ref]);

    const handleDayChange = (e) => {
        const { value } = e.target;
        setDay(value); // Ensure two-digit day
        updateDob(`${year}-${month.padStart(2, '0')}-${value.padStart(2, '0')}`);
    };

    const handleMonthChange = (e) => {
        const { value } = e.target;
        setMonth(value); // Ensure two-digit month
        updateDob(`${year}-${value.padStart(2, '0')}-${day.padStart(2, '0')}`);
    };

    const handleYearChange = (e) => {
        const { value } = e.target;
        setYear(value);
        updateDob(`${value}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`);
    };

    

    return (
        <div>
            <label>Day:</label>
            <input type="text" value={day} onChange={handleDayChange} required />
            <label>Month:</label>
            <input type="text" value={month} onChange={handleMonthChange} required />
            <label>Year:</label>
            <input type="text" value={year} onChange={handleYearChange} required />
        </div>
    );
});

export default DateOfBirth;
