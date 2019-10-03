import React, {Component} from 'react';
import Checkbox from "./Checkbox";
import CarSearchResults from "./CarSearchResults";

const OPTIONS = ["Inclusive Search", "Sunroof", "Four Wheel Drive", "Low Miles", "Power Windows", "Navigation", "Heated Seats"];
const url = "http://localhost:8080/cars";

class CarInventorySearch extends Component {

    state = {
        checkboxes: OPTIONS.reduce(
            (options, option) => ({
                ...options,
                [option]: false
            }),
            {}
        ),
        isLoading: true,
        renderResults: false,
        cars: []
    };

    handleCheckboxChange = changeEvent => {
        const { name } = changeEvent.target;

        this.setState(prevState => ({
            checkboxes: {
                ...prevState.checkboxes,
                [name]: !prevState.checkboxes[name]
            }
        }));
    };

    appendQuery = (query, checkbox) => {
        //todo - use proper data structure, map key to value
        if("Sunroof" === checkbox) {
            query = query + "hasSunroof:true,";
        } else if("Low Miles" === checkbox) {
            query = query + "hasLowMiles:true,";
        } else if("Power Windows" === checkbox) {
            query = query + "hasPowerWindows:true,";
        } else if("Navigation" === checkbox) {
            query = query + "hasNavigation:true,";
        } else if("Heated Seats" === checkbox) {
            query = query + "hasHeatedSeats:true,";
        } else if("Four Wheel Drive" === checkbox) {
            query = query + "hasFourWheelDrive:true,";
        }
        return query;
    };

    handleFormSubmit = formSubmitEvent => {
        formSubmitEvent.preventDefault();
        let query = "";
        let exclusive = true;
        Object.keys(this.state.checkboxes)
            .filter(checkbox => this.state.checkboxes[checkbox])
            .forEach(checkbox => {
                //todo - proper data structure
                if("Inclusive Search" === checkbox) {
                    exclusive = false;
                } else {
                    query = this.appendQuery(query, checkbox);
                }
            });
        if(exclusive) {
            query = "?exclusive=true&search=" + query;
        } else {
            query = "?exclusive=false&search=" + query;
        }
        console.log(query);
        this.fetchData(query);
    };

    fetchData = (query) => {
        fetch(url + query).then(res => res.json())
            .then((data) => {
                console.log(data);
                this.setState({ cars: data, renderResults: true });
            })
            .catch(console.log)

    };

    createCheckbox = option => (
        <Checkbox
            label={option}
            isSelected={this.state.checkboxes[option]}
            onCheckboxChange={this.handleCheckboxChange}
            key={option}
        />
    );

    createCheckboxes = () => OPTIONS.map(this.createCheckbox);

    render() {
        return (
            <div className="container">
                <div className="row mt-5">
                    <h2>Search Inventory</h2>
                    <div className="col-sm-12">
                        <form onSubmit={this.handleFormSubmit}>
                            {this.createCheckboxes()}
                            <div className="form-group mt-2">
                                <button type="submit" className="btn btn-primary">
                                    Search
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                {this.state.renderResults && <CarSearchResults  cars={this.state.cars} />}
            </div>
        );
    }
}

export default CarInventorySearch;