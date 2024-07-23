import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../service/UserService";


function LoginPage(){
const [username, setUserName] = useState('')
const [password, setPassword] = useState('')
const [error, setError] = useState('')
const navigate = useNavigate();

const handleSubmit = async (e) => {
    e.preventDefault();

    try {
        const userData = await UserService.login(username, password)
        console.log(userData)
        if (userData.token) {
            localStorage.setItem('token', userData.token)
            localStorage.setItem('user_type', userData.user_type)
            navigate('/profile')
        }else{
            setError(userData.message)
        }
        
    } catch (error) {
        console.log(error)
        setError(error.message)
        setTimeout(()=>{
            setError('');
        }, 5000);
    }
}


    return(
        <div className="auth-container">
            <h2>Login</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username: </label>
                    <input type="text" value={username} onChange={(e) => setUserName(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Password: </label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    )

}

export default LoginPage;