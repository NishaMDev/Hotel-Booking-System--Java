import {Col, Form, ListGroup, Row} from "react-bootstrap";
import React from "react";
import {useRecoilState} from "recoil";
import {currentStateAtom} from "../../../state";
import {Checkbox, Table, TableCell, TableHead, TableRow} from "@material-ui/core";
import {Button} from "@mui/material";

export default function Amenities() {

    const [currentState, setCurrentState] = useRecoilState(currentStateAtom);

    const amenities = currentState?.hotel?.amenities;

    const handleChange = (checked, selectedAmenity) => {

        let selectedAmenities = [];

        if (currentState?.selected?.amenities) {
            const currentSelectedAmenities = currentState?.selected?.amenities;

            for (const amenity of currentSelectedAmenities) {
                selectedAmenities.push(amenity)
            }
        }
        if (checked) {
            selectedAmenities.push(selectedAmenity)
        } else {
            const selectedAmenitiesBackup = selectedAmenities;
            selectedAmenities = []
            for (const amenity of selectedAmenitiesBackup) {
                if (amenity.id !== selectedAmenity.id) {
                    selectedAmenities.push(amenity)
                }
            }

        }
        setCurrentState({...currentState, ...{selected: {
                    amenities:  selectedAmenities,
                    rooms: currentState?.selected?.rooms}}})

        console.log(currentState)
    }
    return <>
        <Table>
            <TableRow>
                <TableCell align="center" colSpan={1}>
                    Name
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

            {amenities?.map((amenity) => {
                return (
                    <TableRow>
                        <TableCell align="center" colSpan={1}>
                            {amenity.name}
                        </TableCell>
                        <TableCell align="center" colSpan={1}>
                            {amenity.description}
                        </TableCell>
                        <TableCell align="center" colSpan={1}>
                            $ {amenity.price}
                        </TableCell>
                        <TableCell align="center" colSpan={1}>
                            <Checkbox
                                onChange={(event, checked) => {
                                    handleChange(checked, amenity)
                                }}
                                inputProps={{'aria-label': 'controlled'}}
                            />
                        </TableCell>
                    </TableRow>)

            })}
        </Table></>
        ;
}