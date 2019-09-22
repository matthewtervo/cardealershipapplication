import React, {Component} from 'react';
import Checkbox from "./components/Checkbox";
import CarSearchResults from "./components/CarSearchResults";

const OPTIONS = ["InclusiveSearch", "Sunroof", "Four Wheel Drive", "Low Miles", "Power Windows", "Navigation", "Heated Seats"];
const url = "http://localhost:8080/cars";

class App extends Component {
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

    handleFormSubmit = formSubmitEvent => {
        formSubmitEvent.preventDefault();
        var query = "?search=";
        var append = false;
        Object.keys(this.state.checkboxes)
            .filter(checkbox => this.state.checkboxes[checkbox])
            .forEach(checkbox => {
                console.log(checkbox, "is selected.");
                //Todo - replace this, switch?
                if("InclusiveSearch"=== (checkbox)) {
                    query = "?exclusive=false&search="
                } else if("Sunroof" === (checkbox)) {
                    query = query + "hasSunroof:true";
                    append = true;
                } else if("Low Miles" === (checkbox)) {
                    if (append) {
                        query = query + ",";
                    }
                    query = query + "hasLowMiles:true";
                    append = true;
                } else if("Power Windows" === (checkbox)) {
                    if (append) {
                        query = query + ",";
                    }
                    query = query + "hasPowerWindows:true";
                    append = true;
                } else if("Navigation" === (checkbox)) {
                    if (append) {
                        query = query + ",";
                    }
                    query = query + "hasNavigation:true";
                    append = true;
                } else if("Heated Seats" === (checkbox)) {
                    if (append) {
                        query = query + ",";
                    }
                    query = query + "hasHeatedSeats:true";
                    append = true;
                } else if("Four Wheel Drive" === (checkbox)) {
                    if (append) {
                        query = query + ",";
                    }
                    query = query + "hasFourWheelDrive:true";
                    append = true;
                }
            });
        console.log(query);
        this.fetchData(query);
    };

    fetchData = (query) => {
      fetch(url + query).then(res => res.json())
          .then((data) => {
              console.log(data);
              this.setState({ cars: data });
              this.setState({ renderResults: true });
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
                    <h2>Car Dealership Search Inventory</h2>
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

export default App;
