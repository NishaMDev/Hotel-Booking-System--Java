import React, {useEffect, useState} from "react";
 import { useHistory,Link ,useNavigate, Navigate} from 'react-router-dom';
  import {useRecoilState} from "recoil";
import {currentStateAtom, hotelListStateAtom} from "../../state";
import hotelImage from './hotel.png';
import {Typography} from "@mui/material";
import Button from "@mui/material/Button";


function Hotels( ) {
    const navigate = useNavigate();
    const [hotelList] = useRecoilState(hotelListStateAtom);
    const [currentState,setCurrentState] = useRecoilState(currentStateAtom);

    const isUserLoggedIn = () =>{
       return true;
       }

    let hotels = [];

    function handlePreAuthClick(i) {
        setCurrentState({...currentState,...{hotel:hotelList[i]}})
        navigate('/login');
    }

    function handleAuthClick(i) {
        setCurrentState({...currentState,...{hotel:hotelList[i]}})
        navigate(`/hotel/${i}`);
    }

    if(typeof(hotelList) != "undefined" && hotelList){

        for(let i=0; i< hotelList.length; i++){
            let currHotel = hotelList[i];
              if(currentState.city===currHotel.address.city){
            hotels.push(
                <div    style={{border:"2px black solid",display:"flex"}}>

                        <Typography variant="h5" style={{margin:"50px"}}>
                            {currHotel.name}
                        </Typography>
                    <img src={hotelImage} style={{margin:"50px"}}/>
                    <Typography variant="h5" style={{width:"200px",margin:"50px"}}> {currHotel.address.street} {currHotel.address.city} {currHotel.address.country}</Typography>

                    <div style={{margin:"50px"}}>

                            {isUserLoggedIn()? <Button  color={"primary"} variant={"contained"}  onClick={()=>{handleAuthClick(i)}}>Select Hotel</Button>  :

                                    <Button  color={"primary"} variant={"contained"}   onClick={()=>{handlePreAuthClick(i)}} className="select-hotel">Select</Button> }

                        </div>
                 </div>
            )}
        }
    } else {
        hotels = <></>
    }

    return (
        <>{hotels}</>
     )

}

export default Hotels;