import React, {useEffect, useState} from 'react';

import {useRecoilState} from "recoil";
import {currentStateAtom} from "../../../state";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DatePicker} from "@mui/x-date-pickers/DatePicker";
import TextField from "@mui/material/TextField";
import {Button, Typography} from "@mui/material";
import {Container, Slider} from "@material-ui/core";
import Amenities from "./Amenities";
import {PayPal} from "./paypalButton";
import {getCall, postCall} from "../../Services/BackEnd";
import Rooms from "./Rooms";
import {useNavigate} from "react-router-dom";


export default function ProcessBooking() {
    const navigate = useNavigate();


    const [currentState, setCurrentState] = useRecoilState(currentStateAtom);


    const hotel = currentState.hotel;


    const [noOfGuest, setNoOfGuest] = useState(1);

    const [payReady, setPayReady] = useState(false);

    const getTotal = () => {
        let roomTotal = 0;
        let amenityTotal = 0;

        if (currentState?.selected?.rooms) {

            currentState?.selected?.rooms.forEach((room) => {
                roomTotal = roomTotal + room.price
            })
        }
        if (currentState?.selected?.amenities) {
            currentState?.selected?.amenities.forEach((amenity) => {
                amenityTotal = amenityTotal + amenity.price
            })
        }

        console.log({total: roomTotal + amenityTotal, roomTotal, amenityTotal})
        return {total: roomTotal + amenityTotal, roomTotal, amenityTotal}

    }

    const updateGuestCount = (e, value) => {
        if (e) {
            setNoOfGuest(e.target.value);
        } else {
            setNoOfGuest(value);
        }
    };


    const [from, setCheckIn] = useState(new Date().toISOString().substring(0, 10));
    const [to, setCheckOut] = useState(new Date().toISOString().substring(0, 10));

    const marks = [];
    Array.from(new Array(21), (x, i) => marks.push({
        value: i,
        label: i,

    }));


    useEffect(() => {
        const getData = async () => {
            const res = await getCall('rooms/avaiable', {from, to, hotelId: hotel.id});
            console.log(res)
            setCurrentState({...currentState, ...{rooms: res}})
            console.log(currentState)

        }

        const aDay = 86400000;

        const diff = Math.floor(
            (
                Date.parse(to) - Date.parse(from)
            ) / aDay);

        if (diff > 7) {
            alert('please select window under 7 days')
            setCheckIn(new Date().toISOString().substring(0, 10))
            setCheckOut(new Date().toISOString().substring(0, 10))

        } else {
            getData()
        }


    }, [to, from]);
    return (


        <React.Fragment>
            <Container style={{
                backgroundColor: 'lightskyblue',
                borderRadius: '25px',
                background: 'dodgerblue',
                padding: '20px'
            }}>
                <Container style={{
                    backgroundColor: 'dodgerblue',
                    borderRadius: '25px',
                    background: 'dodgerblue',
                    padding: '20px'
                }}>
                    <Typography variant={"h2"} align={"left"} padding={"2px"}

                    > Hotel Name: {hotel.name}</Typography>
                    <Typography variant={"h5"} align={"left"} padding={"2px"}>
                        <b>Hotel Address </b>
                        : {hotel.address.street} , {hotel.address.city} , {hotel.address.zipcode}, {hotel.address.country}
                        <b> Phone</b>: {hotel.phone}
                        <b> Email</b>: {hotel.email}
                    </Typography>


                </Container>


                <Container maxWidth={"xl"} style={{
                    backgroundColor: 'aliceblue',


                }}>
                    <div style={{padding: "20px"}}>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DatePicker
                                allowSameDateSelection={true}
                                label="Check In Date"
                                inputFormat="MM/dd/yyyy"
                                value={from}
                                onChange={(d) => setCheckIn(d.toISOString().substring(0, 10))}
                                renderInput={(params) => <TextField fullWidth={true} {...params} />}
                            />
                        </LocalizationProvider>
                    </div>
                    <div style={{padding: "20px"}}>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DatePicker
                                allowSameDateSelection={true}
                                label="Check Out Date"
                                inputFormat="MM/dd/yyyy"
                                value={to}
                                onChange={(d) => setCheckOut(d.toISOString().substring(0, 10))}
                                renderInput={(params) => <TextField fullWidth={true} {...params} />}
                            />
                        </LocalizationProvider>

                    </div>
                    <div style={{margin: "20px"}}>


                        <Typography variant={"h5"} style={{width: "600px"}}>
                            Number of Guests
                        </Typography>
                        <Typography variant={"h5"} style={{width: "600px"}}>

                            <TextField
                                value={noOfGuest}
                                onChange={updateGuestCount}

                            />
                        </Typography>


                    </div>
                </Container>

                <Container style={{
                    backgroundColor: 'aliceblue',
                }}>
                    <Typography variant={"h3"}>
                        Rooms</Typography>
                    <Rooms/>

                </Container>
                <Container style={{
                    backgroundColor: 'aliceblue', marginTop: "20px"
                }}>
                    <Typography variant={"h5"} align={"left"}>Add amenities</Typography>

                    <Amenities/>


                </ Container>

                <Typography variant={"h3"}> Total Price: {getTotal().total}</Typography>


                <Typography variant={"h5"}> Amenities Price: {getTotal().amenityTotal}</Typography>

                <Typography variant={"h5"}> Rooms Price: {getTotal().roomTotal}</Typography>

                <Button color={"primary"} variant={"contained"} onClick={() => {

                    console.log('currentState?.selected?.rooms')
                    console.log(currentState?.selected?.rooms)
                         setPayReady(true)



                }}> Click to Pay Amount ${getTotal().total}</Button>
                {payReady && <div style={{marginLeft: "400px"}}>
                    <PayPal amount={getTotal().total}

                            onSuccess={async () => {
                                console.log('success')

                                let amenities = "";
                                let roomBookings = []

                                if (currentState?.selected?.amenities) {
                                    for (const amenity of currentState?.selected?.amenities) {
                                        amenities = amenities + amenity.id + ','
                                    }
                                }

                                for (const room of currentState?.selected?.rooms) {
                                    roomBookings.push({
                                        "checkInDate": from,
                                        "checkOutDate": to,
                                        "roomId": room.id
                                    })
                                }
                                try {
                                    await postCall('bookings', JSON.stringify({
                                        "amenities": amenities,
                                        "noOfGuests": noOfGuest,
                                        roomBookings,
                                        "status": "booked",
                                        "userId": currentState?.user?.id
                                    }))

                                    const personData = await getCall(`person/${currentState?.user.id}`);

                                    console.log(personData)
                                    setCurrentState({user: personData, hotel: currentState.hotel})
                                    navigate('/bookings')

                                } catch (e) {
                                    alert(e)
                                }
                            }
                            }
                    />
                </div>}

            </Container>
        </React.Fragment>

    );

}
    
