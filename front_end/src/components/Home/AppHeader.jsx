import * as React from "react";

import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import {useNavigate} from 'react-router-dom';
import {useRecoilState} from "recoil";
import {currentStateAtom} from "../../state";
import {Auth} from "aws-amplify";

const isAdmin = () => {
    return false;
}

export default function AppHeader() {
    const [currentState, setCurrentState] = useRecoilState(currentStateAtom);

    const navigate = useNavigate();
    const isLoggedIn = currentState?.user;
    const isAdmin = currentState?.user?.type==='admin';
    console.log(currentState?.user)
    return (
        <AppBar position="static">
            <Toolbar>

                <IconButton
                    size="large"
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    sx={{mr: 2}}
                >


                    <Button color={"primary"} variant={"contained"} style={{marginLeft: "20px"}} onClick={() => {
                        navigate('/');
                    }
                    }>Home </Button>
                </IconButton>

                <IconButton
                    size="large"
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    sx={{mr: 2}}
                >


                    {isLoggedIn &&
                    <Button color={"primary"} variant={"contained"} style={{marginLeft: "20px"}} onClick={() => {
                        Auth.signOut();
                    }}>
                        LogOut</Button>}
                </IconButton>

                <IconButton
                    size="large"
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    sx={{mr: 2}}
                >


                    <Button color={"primary"} variant={"contained"} style={{marginLeft: "20px"}}

                            onClick={() => {
                                navigate('/profile');
                            }}
                    >Profile</Button>
                </IconButton>
                <IconButton
                    size="large"
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    sx={{mr: 2}}
                >


                    <Button color={"primary"} variant={"contained"} style={{marginLeft: "20px"}} onClick={() => {
                        navigate('/bookings');
                    }}>Bookings</Button>
                </IconButton>
                {isAdmin && <IconButton
                    size="large"
                    edge="start"
                    color="inherit"
                    aria-label="menu"
                    sx={{mr: 2}}
                >
                    <Button color={"primary"} variant={"contained"} style={{marginLeft: "20px"}} onClick={() => {
                        navigate('/admin');
                    }}>Admin Console</Button>
                </IconButton>}

            </Toolbar>
        </AppBar>
    );
}
