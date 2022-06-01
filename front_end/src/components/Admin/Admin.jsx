import {useEffect, useState} from "react";
import {Button} from "@mui/material";
import * as React from "react";
import {Auth} from "aws-amplify";
import {deleteCall, getCall, postCall} from "../Services/BackEnd";
import {Checkbox, Table, TableCell, TableRow} from "@material-ui/core";
import Image from "react-bootstrap/Image";
import SingleRoom from "../Customer/Bookings/singleRoom.jpg";
import DoubleRoom from "../Customer/Bookings/doubleRoom.jpg";
import Suiteimg from "../Customer/Bookings/suite.jpg";

export function Admin() {

    const [showRooms, setShowRooms] = useState(false);
    const [rooms, setRooms] = useState({});

    useEffect(() => {
        const getData = async () => {
            const res = await getCall('/rooms')
            setRooms(res)
        };
        // Update the document title using the browser API
        getData()
    }, []);


    return (
        <div>

            <Button color={"primary"} variant={"contained"} style={{marginLeft: "20px"}} onClick={() => {
                setShowRooms(!showRooms);
            }
            }>{showRooms ? "Hide Rooms" : "Show All rooms "}</Button>

            {showRooms && <Table>
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

                {rooms?.map((room) => {
                    return (
                        <TableRow>
                            <TableCell>
                                <Image src={room.type === 'single' && SingleRoom ||
                                room.type === 'double' && DoubleRoom ||
                                room.type === 'suite' && Suiteimg

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

                                <

                                >    <Button color={"primary"} variant={"contained"}
                                             onClick={async () => {

                                                 await deleteCall(`rooms/${room.id}`)

                                                 const res = await getCall('/rooms')
                                                 setRooms(res)
                                             }}
                                             inputProps={{'aria-label': 'controlled'}}
                                > Delete</Button></>
                            </TableCell>
                        </TableRow>)

                })}
            </Table>}
        </div>
    )
}
