import React, {Component} from 'react';
import CarCreateForm from "./CarCreateForm";

class CarCreate extends Component {

    handleFormSubmit = formSubmitEvent => {
        formSubmitEvent.preventDefault();
        console.log("CREATE CAR");
        //this.fetchData(query);
    };

    render() {
        return (
            <div className="container">
                <div className="row mt-5">
                    <h2>Create Car</h2>
                    <div className="col-sm-12">
                        <form onSubmit={this.handleFormSubmit}>
                            <div className="form-group mt-2">
                                <button type="submit" className="btn btn-primary">
                                    Create
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default CarCreate;