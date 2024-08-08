import React, { useEffect } from "react";
import Header from "./container/header/header";
import Footer from "./container/footer/Footer";
import Home from "./container/home/home";
import About from "./container/About/About";
import Contact from "./container/Contact/Contact";
import "react-toastify/dist/ReactToastify.css";
import JobPage from "./container/JobPage/JobPage";
import { ToastContainer } from "react-toastify";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect,
} from "react-router-dom";
import Otp from "./container/login/Otp";
import Login from "./container/login/Login";
import Register from "./container/login/Register";
import ForgetPassword from "./container/login/ForgetPassword";

function App() {
  return (
    <Router>
      <Switch>
        <div className="App">
          <Route exact path="/">
            <Header />
            <Home />
            <Footer />
          </Route>
          <Route path="/about">
            <Header />
            <About />
            <Footer />
          </Route>
          <Route path="/contact">
            <Header />
            <Contact />
            <Footer />
          </Route>
          <Route path="/job">
            <Header />
            <JobPage />
            <Footer />
          </Route>

          <Route path="/company">
            <Header />
            {/* <ListCompany /> */}
            <Footer />
          </Route>
          <Route path="/detail-company/:id">
            <Header />
            {/* <DetailCompany /> */}
            <Footer />
          </Route>

          <Route path="/login">
            <Header />
            <Login />
            <Footer />
          </Route>
          <Route path="/register">
            <Header />
            <Register />
            <Footer />
          </Route>
          <Route path="/forget-password">
            <Header />
            <ForgetPassword />
            <Footer />
          </Route>

          {/* <DetailPage /> */}
          {/* <Route path="/detail-job/:id">
            <Header />
            <DetailPage />
            <Footer />
          </Route> */}

          <ToastContainer
            position="top-right"
            autoClose={4000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
          />
        </div>
      </Switch>
    </Router>
  );
}

export default App;
