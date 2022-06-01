import React, { useState} from "react";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import {useRecoilState} from "recoil";
import {currentStateAtom} from "../../state";
import { postCall} from "../Services/BackEnd";
import {Typography} from "@material-ui/core";

let defaultValues = {

    "city": "default city",
    "country": "default country",
    "street": "default street",
    "zipcode": "default pin",

    "email": "output.attributes.email",
    "id": "output.username",
    "personName": "output.username",
    "phoneNumber": "default phone",
    "type": "customer",
    "points":"1000",
    "loyaltyGrade":"silver"
};
const Profile = () => {
    const [currentState] = useRecoilState(currentStateAtom);
    if (currentState?.user) {
        const apiState = currentState?.user
        defaultValues = {
            "city": apiState.address.city,
            "country": apiState.address.country,
            "street": apiState.address.street,
            "zipcode": apiState.address.zipcode,

            "email": apiState.email,
            "id": apiState.id,
            "personName": apiState.personName,
            "phoneNumber": apiState.phoneNumber,
            "type": apiState.type,
            "points":apiState.points,
            "loyaltyGrade":apiState.grade,

        };
    }


    const [formValues, setFormValues] = useState(defaultValues);
    const handleInputChange = (e) => {
        console.log('test55')

        const {name, value} = e.target;
        setFormValues({
            ...formValues,
            [name]: value,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const apiState = currentState?.user

        const submitValue = {

            address: {
                "id": apiState.address.id,
                "city": formValues.city,
                "country": formValues.country,
                "street": formValues.street,
                "zipcode": formValues.zipcode,
            },
            "email": formValues.email,
            "id": apiState.id,
            "personName": formValues.personName,
            "phoneNumber": formValues.phoneNumber,
            "type": formValues.type,
        }
        const res = await postCall(`person`, JSON.stringify(submitValue));
        console.log({formValues, res});
    };
    return (
        <>
            <form onSubmit={handleSubmit} style={{marginTop: "100px"}}>
                <Grid container alignItems="center" justify="center" direction="column">
                    <Grid item style={{width: "800px"}}>
                        <TextField style={{width: "800px"}}
                                   id="city-input"
                                   name="city"
                                   label="city"
                                   type="text"
                                   value={formValues.city}
                                   onChange={handleInputChange}
                        />
                    </Grid>
                    <Grid item style={{width: "800px"}}>
                        <TextField style={{width: "800px"}} id="country-input"
                                   name="country"
                                   label="country"
                                   type="text"
                                   value={formValues.country}
                                   onChange={handleInputChange}
                        />
                    </Grid>
                    <Grid item style={{width: "800px"}}>
                        <TextField style={{width: "800px"}} id="street-input"
                                   name="street"
                                   label="street"
                                   type="text"
                                   value={formValues.street}
                                   onChange={handleInputChange}
                        />
                    </Grid>
                    <Grid item style={{width: "800px"}}>
                        <TextField style={{width: "800px"}} id="zipcode-input"
                                   name="zipcode"
                                   label="zipcode"
                                   type="text"
                                   value={formValues.zipcode}
                                   onChange={handleInputChange}
                        />
                    </Grid> <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="email-input"
                               name="email"
                               label="email"
                               type="text"
                               value={formValues.email}
                               onChange={handleInputChange}
                    />
                </Grid> <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="personId-input"
                               name="personId"
                               label="personId"
                               type="text"
                               value={formValues.id}
                    />
                </Grid> <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="personName-input"
                               name="personName"
                               label="personName"
                               type="text"
                               value={formValues.personName}
                               onChange={handleInputChange}
                    />
                </Grid> <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="phoneNumber-input"
                               name="phoneNumber"
                               label="phoneNumber"
                               type="text"
                               value={formValues.phoneNumber}
                               onChange={handleInputChange}
                    />
                </Grid> <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="type-input"
                               name="type"
                               label="type"
                               type="text"
                               value={formValues.type}
                        // onChange={handleInputChange}
                    />
                </Grid>

                    <Button color={"primary"} variant={"contained"} type="submit">
                        Submit
                    </Button>
                </Grid>
            </form>
            <div style={{marginLeft:"450px",marginTop:"100px",marginBottom:"100px"}}>

                <Typography variant={"h4"} style={{marginBottom:"100px"}}>
                    Loyalty Account
                </Typography>
                <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="loyalty level-input"
                               name="loyalty level"
                               label="loyalty level"
                               type="text"
                               value={formValues.loyaltyGrade}
                        // onChange={handleInputChange}
                    />
                </Grid>
                <Grid item style={{width: "800px"}}>
                    <TextField style={{width: "800px"}}
                               id="points-input"
                               name="points"
                               label="points"
                               type="text"
                               value={formValues.points}
                        // onChange={handleInputChange}
                    />
                </Grid></div>
        </>
    );
};
export default Profile;