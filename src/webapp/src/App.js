import React, {Component} from 'react';
import CarCreate from  "./components/CarCreate";
import CarInventorySearch from "./components/CarInventorySearch";

class App extends Component {
    render() {
        return (
            <div className="container">
                <h1>Car Dealership App</h1>
                <CarInventorySearch />
            </div>
        );
    }

}

export default App;
