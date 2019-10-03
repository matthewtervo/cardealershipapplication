import React, {Component} from 'react';
import Checkbox from "./Checkbox";
import CarSearchResults from "./CarSearchResults";

const SEARCH_OPTIONS = {
    "Sunroof": "hasSunroof:true,",
    "Four Wheel Drive": "hasFourWheelDrive:true,",
    "Low Miles": "hasLowMiles:true,",
    "Power Windows": "hasPowerWindows:true,",
    "Navigation": "hasNavigation:true,",
    "Heated Seats": "hasHeatedSeats:true,",
    "Inclusive Search": "&exclusive=false"
    };

const url = "http://localhost:8080/cars?search=";

class CarInventorySearch extends Component {

    state = {
        checkboxes: Object.keys(SEARCH_OPTIONS).reduce(
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
        return query + SEARCH_OPTIONS[checkbox];
    };

    handleFormSubmit = formSubmitEvent => {
        formSubmitEvent.preventDefault();
        let query = "";
        Object.keys(this.state.checkboxes)
            .filter(checkbox => this.state.checkboxes[checkbox])
            .forEach(checkbox => {
                query = this.appendQuery(query, checkbox);
            });
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

    render() {
        return (
            <div className="container">
                <div className="row mt-5">
                    <h2>Search Inventory</h2>
                    <div className="col-sm-12">
                        <form onSubmit={this.handleFormSubmit}>
                            {Object.keys(SEARCH_OPTIONS).map(option =>
                                <Checkbox
                                    label={option}
                                    isSelected={this.state.checkboxes[option]}
                                    onCheckboxChange={this.handleCheckboxChange}
                                    key={option}
                                />
                            )}
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