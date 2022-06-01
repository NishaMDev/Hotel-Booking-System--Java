import React, {useEffect} from 'react';
import {Route, BrowserRouter, Routes} from "react-router-dom";

import ProcessBooking from './components/Customer/Bookings/ProcessBooking';
import HomePage from './components/Home/HomePage';
import Profile from './components/Home/Profile';

  import {RecoilRoot} from "recoil";
import ResponsiveAppBar from "./components/Home/AppHeader";
import Amplify, { Auth } from 'aws-amplify';
import awsconfig from './aws-exports';
import { withAuthenticator } from '@aws-amplify/ui-react';
import '@aws-amplify/ui-react/styles.css';
import Bookings from "./components/Customer/Bookings/Bookings";
import {Admin} from "./components/Admin/Admin";

Amplify.configure(awsconfig);
function App() {

    return (
        <RecoilRoot>
        <BrowserRouter>
            <ResponsiveAppBar/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/bookings" element={<Bookings/>}/>
                <Route path="hotel/:id" element={<ProcessBooking/>}/>
                <Route path="/profile" element={<Profile/>}/>
                <Route path="/admin" element={<Admin/>}/>

            </Routes>
        </BrowserRouter>
            </RecoilRoot>

    )

}
export default withAuthenticator(App);

// export default App;
