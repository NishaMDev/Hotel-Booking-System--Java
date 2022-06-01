import {Col, Form, ListGroup, Row} from "react-bootstrap";
import React from "react";
import {useRecoilState} from "recoil";
import {currentStateAtom} from "../../../state";
import {Checkbox, Table, TableCell, TableHead, TableRow} from "@material-ui/core";
import {Button} from "@mui/material";
import Image from "react-bootstrap/Image";
import SingleRoom from "./singleRoom.jpg";
import DoubleRoom from './doubleRoom.jpg';
import Suiteimg from './suite.jpg';


export default function Amenities() {

    const [currentState, setCurrentState] = useRecoilState(currentStateAtom);

    const rooms = currentState?.rooms;

        const handleChange = (checked, selected) => {

            let selectedRooms = [];

            if (currentState?.selected?.rooms) {
                const currentSelectedRooms = currentState?.selected?.rooms;

                for (const amenity of currentSelectedRooms) {
                    selectedRooms.push(amenity)
                }
            }
            if (checked) {
                selectedRooms.push(selected)
            } else {
                const backup = selectedRooms;
                selectedRooms = []
                for (const room of backup) {
                    if (room.id !== selected.id) {
                        selectedRooms.push(room)
                    }
                }

            }
            setCurrentState({...currentState, ...{selected: {
                amenities: currentState?.selected?.amenities,
                rooms: selectedRooms}}})


            console.log(currentState)
        }

        return <>
            <Table>
                <TableRow>
                    <TableCell align="center" colSpan={1}>

                    </TableCell>
                    <TableCell align="center" colSpan={1}>
                        Type
                    </TableCell>
                    <TableCell align="center" colSpan={1}>
                        Description
                    </TableCell>
                    <TableCell align="center" colSpan={1}>
                        Price
                    </TableCell>
                    <TableCell align="center" colSpan={1}>

                    </TableCell>
                </TableRow>

                { rooms?.map((room) => {
                    return (
                        <TableRow>
                            <TableCell>
                                <Image src={room.type==='single' && SingleRoom ||
                                room.type==='double' && DoubleRoom ||
                                room.type==='suite' && Suiteimg

                                } width={400} height={150}/>


                            </TableCell>
                            <TableCell align="center" colSpan={1}>
                                {room.type}
                            </TableCell>
                            <TableCell align="center" colSpan={1}>
                                {room.description}
                            </TableCell>
                            <TableCell align="center" colSpan={1}>
                                {room.price}
                            </TableCell>
                            <TableCell align="center" colSpan={1}>
                                <Checkbox
                                    onChange={(event, checked) => {
                                        handleChange(checked,room)
                                    }}
                                    inputProps={{'aria-label': 'controlled'}}
                                />
                            </TableCell>
                        </TableRow>)

                })}
            </Table></>
    }
