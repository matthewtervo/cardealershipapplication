import React, {Component} from 'react';
import Checkbox from "./Checkbox";
import CarSearchResults from "./CarSearchResults";
import Select from 'react-select';

const CHECKBOXES_OPTIONS = {
    "Sunroof": "hasSunroof:true,",
    "Four Wheel Drive": "hasFourWheelDrive:true,",
    "Low Miles": "hasLowMiles:true,",
    "Power Windows": "hasPowerWindows:true,",
    "Navigation": "hasNavigation:true,",
    "Heated Seats": "hasHeatedSeats:true,",
    "Inclusive Search": "&exclusive=false"
    };

const MAKE_OPTIONS = {
    "Chevy": "make:chevy,",
    "Ford": "make:ford,",
    "Mercedes": "make:mercedes,",
    "Toyota": "make:toyota,"
};

const COLOR_OPTIONS = {
    "Gray": "color:gray,",
    "Silver": "color:silver,",
    "Black": "color:black,",
    "White": "color:white,"
};

const url = "http://localhost:8080/cars?search=";

class CarInventorySearch extends Component {

    state = {
        checkboxes: Object.keys(CHECKBOXES_OPTIONS).reduce(
            (options, option) => ({
                ...options,
                [option]: false
            }),
            {}
        ),
        make:  Object.keys(MAKE_OPTIONS).map(
            (label, value) => ({
                label: label,
                value: value,
                type: "make",
                selected: false
            }),
            {}
        ),
        color:  Object.keys(COLOR_OPTIONS).map(
            (label, value) => ({
                label: label,
                value: value,
                type: "color",
                selected: false
            }),
            {}
        ),
        renderResults: false,
        cars: []
    };

    handleDropDownChange = changeEvent => {
        const type = changeEvent.type;
        const value = changeEvent.value;

        this.setState(prevState => ({
            [type]: prevState[type].map(
                entry =>
                    entry.value === value ?
                        ({...entry, selected: !entry.selected}) :
                        {...entry, selected: false},
                {}
            ),
        }));
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

    appendColor = (query, color) => {
        return query + COLOR_OPTIONS[color.label]
    };

    appendMake = (query, make) => {
        return query + MAKE_OPTIONS[make.label];
    };

    appendCheckboxQuery = (query, checkbox) => {
        return query + CHECKBOXES_OPTIONS[checkbox];
    };

    handleFormSubmit = formSubmitEvent => {
        formSubmitEvent.preventDefault();
        let query = "";

        this.state.make
            .filter(make => make.selected)
            .forEach(make => {
                query = this.appendMake(query, make);
            });

        this.state.color
            .filter(color => color.selected)
            .forEach(color => {
                query = this.appendColor(query, color);
            });

        Object.keys(this.state.checkboxes)
            .filter(checkbox => this.state.checkboxes[checkbox])
            .forEach(checkbox => {
                query = this.appendCheckboxQuery(query, checkbox);
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
                            {Object.keys(CHECKBOXES_OPTIONS).map(option =>
                                <Checkbox
                                    label={option}
                                    isSelected={this.state.checkboxes[option]}
                                    onCheckboxChange={this.handleCheckboxChange}
                                    key={option}
                                />
                            )}
                            Make:
                            <Select
                                options={ this.state.make }
                                onChange={this.handleDropDownChange}
                            />
                            Color:
                            <Select
                                options={ this.state.color }
                                onChange={this.handleDropDownChange}
                            />
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