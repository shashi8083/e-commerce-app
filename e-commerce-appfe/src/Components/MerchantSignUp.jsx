import { useState } from "react";
import  "../Styles/MerchantSignup.css";
import axios from "axios";

const Merchantsignup = () => {
    let [name,setname]= useState("")
    let [email,setemail] = useState("")
    let [phone,setphone] = useState("")
    let [gst_number,setgst_number]= useState("")
    let [password, setPassword] = useState("");

    let data = {name,email,gst_number,phone, password};

    let addMerchant = (e)=>{
        e.preventDefault();
        axios.post(`http://localhost:8080/merchants`,data)
        .then((res)=>{
            console.log(res);
            alert('Merchant Added Successfully')
        })
        .catch((err)=>{
            console.log(err);
            alert("Data not found")
        })
    }
    return ( 
    <div className="merchantsignup">
        <form onSubmit={addMerchant}  action="">
            <label htmlFor="">Name</label>
             <input type="text" value={name} onChange={(e)=>{setname(e.target.value)}} required />       
            <label htmlFor="">GST_number</label>
            <input type="text"value={gst_number} onChange={(e)=>{setgst_number(e.target.value)}} required/>
            <label htmlFor="">Email</label>
            <input type="email" value={email} onChange={(e)=>{setemail(e.target.value)}} required/>
            <label htmlFor="">Phone</label>
            <input type="tel" value={phone} onChange={(e)=>{setphone(e.target.value)}} required/>
            <label htmlFor="">Password</label>
            <input type="password"value={password} onChange={(e)=>{setPassword(e.target.value)}} required/>
            <button>Submit</button>
        </form>
           
    </div> 
    );
}
 
export default Merchantsignup;