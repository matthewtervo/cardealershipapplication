import React from 'react'

const CarSearchResults = ({cars}) => {
    return (
        <div>
            <h1>Search Results</h1>
            {cars.map((car) => (
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Car ID: {car._id}</h5>
                        <h6 className="card-subtitle mb-2 text-muted">Make: {car.make}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Color: {car.color}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Year: {car.year}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Price: {car.price}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Sunroof: {String(car.hasSunroof)}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Four Wheel Drive: {String(car.hasFourWheelDrive)}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Low Miles: {String(car.hasLowMiles)}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Power Windows: {String(car.hasPowerWindows)}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Navigation: {String(car.hasNavigation)}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">Heated Seats: {String(car.hasHeatedSeats)}</h6>

                    </div>
                </div>
            ))}
        </div>
    )
};

export default CarSearchResults