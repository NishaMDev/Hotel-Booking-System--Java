import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';


  import {useRecoilState} from "recoil";
import {currentStateAtom, hotelListStateAtom} from "../../state";
import {Typography} from "@material-ui/core";
const cities=new Set();
const country=new Set();
function HotelSearch( ) {
    const [hotelList, setHotelList] = useRecoilState(hotelListStateAtom);

     const [currentState,setCurrentState] = useRecoilState(currentStateAtom);
    if(hotelList.length>0){
    hotelList.forEach((hotel)=>{
        cities.add(hotel.address.city)
        country.add(hotel.address.country)
    })}

     const [location, setLocation] = useState(cities.size>0?cities[0]:"");

    const updateLocation=(e)=>{
       setLocation(e.target.value)
        setCurrentState({...currentState,...{city:e.target.value}})
    }


    const citiesDiv = [];
    cities.forEach((city)=>{
         citiesDiv.push( <option value={city}>{city}</option>)
    })

    return (
        < div style={{padding:"20px"}}>

            <Typography variant="h1" align={"center"} >   Find Best Hotels at best price</Typography>

            <Typography style={{margin:"50px"}} align={"center"} variant="h2"> Please Select the City</Typography>
            <FormControl style={{ m: 1, minWidth: 120, maxWidth: 300,marginLeft:"800px" ,fontSize:"large" }}>
                <InputLabel shrink htmlFor="select-multiple-native">
                    City
                </InputLabel>
                <Select

                    multiple
                    native
                     // @ts-ignore Typings are not considering `native`
                    onChange={updateLocation}
                    label="Cities"

                >
                    {Array.from(cities).map((name) => (
                        <option key={name} value={name} style={{fontSize:"40px"}}>
                            {name}
                        </option>
                    ))}
                </Select>
            </FormControl>




        </ div>
    );
};

export default HotelSearch;