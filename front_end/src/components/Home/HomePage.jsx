import React, {useEffect, useState} from "react";
import HotelSearch from "./HotelSearch";
import Hotels from "./Hotels";
import {useRecoilState} from "recoil";
import {currentStateAtom, hotelListStateAtom} from "../../state";

import styled from "styled-components"
import hotelBg from  './background.webp'
import {Auth} from "aws-amplify";
import {getCall, postCall} from "../Services/BackEnd";
function HomePage() {
    const [hotelList, setHotelList] = useRecoilState(hotelListStateAtom);
    const [currentState, setCurrentState] = useRecoilState(currentStateAtom);






    useEffect(() => {
        setHotelList({})
        setCurrentState({})
        const getData=async () => {


            Auth.currentUserInfo().then(async (output) => {


                const hotelListData = await getCall('hotels')
                setHotelList(hotelListData.content)

                const personData = await getCall(`person/${output.username}`);
                 if(!personData){


                  const res=  await postCall(`person`,JSON.stringify({
                        "address": {
                            "city": "San Jose",
                            "country": "USA",
                            "street": "good street",
                            "zipcode": "95144"
                        },
                        "email": output.attributes.email,
                        "id": output.username,
                        "personName": output.username,
                        "phoneNumber": "0000000000",
                        "type": "customer"
                    }));


                    setCurrentState({user: res.content })

                }
                else {
                    setCurrentState({user: personData })

                }
            })

        };
        // Update the document title using the browser API
        getData()
    },[]);


    const Container= styled.div`
      background-image: url(${hotelBg});
     
    `

    return (
        <Container  >
            <HotelSearch />
            <Hotels />
        </Container>
    )

}

export default HomePage;