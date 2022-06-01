import * as React from 'react';
import {DataGrid, GridColDef, GridValueGetterParams} from '@mui/x-data-grid';
import {useRecoilState} from "recoil";
import {currentStateAtom} from "../../../state";
import {Button} from "@mui/material";
import {deleteCall, getCall, postCall} from "../../Services/BackEnd";
import {useState} from "react";
import {useNavigate} from "react-router-dom";



export default function Bookings() {

    const [currentState,setCurrentState] = useRecoilState(currentStateAtom);
    const [selectedBooking,setSelectedBooking] = useState({});

    const columns = [
        {field: 'id', headerName: 'ID', width: 70},
        {field: 'noOfGuests', headerName: 'noOfGuests', width: 130},
        {field: 'status', headerName: 'status', width: 130},
        {field: 'amenities', headerName: 'amenities', width: 130},
        {field: 'roomBookingEntityList', headerName: 'roomBookingEntityList', width: 800, height:200,resizable:true,
            valueGetter: (params) =>
                `${JSON.stringify(params.row)  }  `},
        {field: 'Cancel', headerName: 'Cancel', width: 100, height:200,resizable:true,
            renderCell: (params) => <Button color={"primary"} variant={"contained"}  onClick={async (event) => {

                console.log(params)
               await deleteCall(`bookings/${params.id}`)
                await new Promise(r => setTimeout(r, 1000));

                const personData = await getCall(`person/${currentState?.user.id}`);

                console.log(personData)
                setCurrentState({user: personData} )


            }} > Cancel</Button> } ,
        {field: 'Select', headerName: 'Select', width: 100, height:200,resizable:true,
            renderCell: (params) => <Button color={"primary"} variant={"contained"}  onClick={async (event) => {


                setSelectedBooking(params.row.roomBookingEntityList)

            }} > Select</Button>,  }


    ];
    const navigate = useNavigate();


    const columnsBooking = [
        {field: 'id', headerName: 'ID', width: 70},
        {field: 'checkInDate', headerName: 'checkInDate', width: 130},
        {field: 'check_out_date', headerName: 'check_out_date', width: 130},
        {field: 'parentBookingId', headerName: 'parentBookingId', width: 130},
        {field: 'roomId', headerName: 'roomId', width: 800, height:200,resizable:true},
        {field: 'Cancel', headerName: 'Cancel', width: 100, height:200,resizable:true,
            renderCell: (params) => <Button color={"primary"} variant={"contained"}  onClick={async (event) => {

                console.log(params)
                await deleteCall(`bookings/room/${params.id}`)
                await new Promise(r => setTimeout(r, 1000));

                const personData = await getCall(`person/${currentState?.user.id}`);

                console.log(personData)
                setCurrentState({user: personData} )

                setSelectedBooking(undefined)
            }} > Cancel</Button> }



    ];


    const apiState = currentState?.user||{}

    const rows = apiState.bookingEntity;


    return (
        <>
        <div style={{height: 400, width: '100%'}}>
            <DataGrid
                rows={rows}
                columns={columns}
                pageSize={5}
                rowsPerPageOptions={[5]}
                hideFooter={true}
            />
        </div>
<div>
    {selectedBooking && selectedBooking!=={} && <>
        <div style={{height: 400, width: '100%'}}>
            <DataGrid
                rows={selectedBooking}
                columns={columnsBooking}
                pageSize={5}
                rowsPerPageOptions={[5]}
                hideFooter={true}
            />
        </div>

    </>}

</div>

        </>
    );
}
