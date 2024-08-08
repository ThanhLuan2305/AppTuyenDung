import React, { useEffect, useState } from "react";
import "./Otp.scss";
// import firebase from "../../util/firebase";
import { toast } from "react-toastify";
// import { createNewUser, handleLoginService } from "../../service/userService";
const Otp = (props) => {
  const [dataUser, setdataUser] = useState({});
  const [otpnumber, setotpnumber] = useState("");
  const [inputValues, setInputValues] = useState({
    so1: "",
    so2: "",
    so3: "",
    so4: "",
    so5: "",
    so6: "",
  });

  
  const handleOnChange = (event) => {
    const { name, value } = event.target;
    setInputValues({ ...inputValues, [name]: value });
  };

  
  return (
    <>
      <div className="container d-flex justify-content-center align-items-center container_Otp">
        <div className="card text-center">
          <div className="card-header p-5">
            <img src="https://raw.githubusercontent.com/Rustcodeweb/OTP-Verification-Card-Design/main/mobile.png" />
            <h5 style={{ color: "#fff" }} className="mb-2">
              XÁC THỰC OTP
            </h5>
            <div>
              <small>
                mã đã được gửi tới sdt{" "}
                {props.dataUser && props.dataUser.phonenumber}
              </small>
            </div>
          </div>
          <div className="input-container d-flex flex-row justify-content-center mt-2">
            <input
              value={inputValues.so1}
              name="so1"
              onChange={(event) => handleOnChange(event)}
              type="text"
              className="m-1 text-center form-control rounded"
              maxLength={1}
            />
            <input
              value={inputValues.so2}
              name="so2"
              onChange={(event) => handleOnChange(event)}
              type="text"
              className="m-1 text-center form-control rounded"
              maxLength={1}
            />
            <input
              value={inputValues.so3}
              name="so3"
              onChange={(event) => handleOnChange(event)}
              type="text"
              className="m-1 text-center form-control rounded"
              maxLength={1}
            />
            <input
              value={inputValues.so4}
              name="so4"
              onChange={(event) => handleOnChange(event)}
              type="text"
              className="m-1 text-center form-control rounded"
              maxLength={1}
            />
            <input
              value={inputValues.so5}
              name="so5"
              onChange={(event) => handleOnChange(event)}
              type="text"
              className="m-1 text-center form-control rounded"
              maxLength={1}
            />
            <input
              value={inputValues.so6}
              name="so6"
              onChange={(event) => handleOnChange(event)}
              type="text"
              className="m-1 text-center form-control rounded"
              maxLength={1}
            />
          </div>
          <div>
            <small>
              bạn không nhận được Otp ?
              <a
                onClick={() => {/*resendOTP()*/}}
                style={{ color: "#3366FF" }}
                className="text-decoration-none ml-2"
              >
                Gửi lại
              </a>
            </small>
          </div>
          <div className="mt-3 mb-5">
            <div id="sign-in-button"></div>
            <button
              onClick={() => {/*submitOTP()*/ }}
              className="btn btn-success px-4 verify-btn"
            >
              Xác thực
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Otp;
