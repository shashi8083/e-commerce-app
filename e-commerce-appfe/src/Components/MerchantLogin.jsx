import { useState } from 'react';
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
const MerchantLogin = () => {
    let [email,setemail] = useState("")
    let [password,setpassword] = useState("")

    let navigate = useNavigate()
    function verifyMerchant(e){
        e.preventDefault();
        // if(email == "abc@gmail.com" && password == "1234"){
        //     alert("login successfull")
        // }
        // else{
        //     alert("login failed")
        // }
        axios.post(`http://localhost:8080/merchants/verify-by-email?email=${email}&password=${password}`)
        .then((res)=>{
            console.log(res.data);
            navigate('/merchanthomepage')
            alert("Login successfull")
        })
        .catch((err)=>{
            console.log(err.data);
            alert("Invalid Credentials")
        })
    }
    return ( 
        <div className="merchantLogin">
            <h1>Merchant Login</h1>
            <Form>
                <Form.Group className="mb-3" controlId="formGroupEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control value={email} onChange={(e)=>{setemail(e.target.value)}}  type="email" placeholder="Enter email" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formGroupPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control value={password} onChange={(e)=>{setpassword(e.target.value)}} type="password" placeholder="Password" />
                </Form.Group>
                <Form.Group>
                    <button className='btn btn-success mx-5' onClick={verifyMerchant}>Sign In</button>
                    <button className='btn btn-danger mx-5'><Link to="/merchantsignup">Sign Up</Link></button>
                </Form.Group>
            </Form>
        </div>
     );
}
 
export default MerchantLogin;