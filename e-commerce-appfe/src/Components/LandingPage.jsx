import {Link} from "react-router-dom";
import '../Styles/LandingPage.css';
const LandingPage = () => {
    return (  
        <div className="landingpage">
        <Link to="/merchant">
            <img src="https://cdn-icons-png.flaticon.com/128/6024/6024190.png" alt="" />
       Merchant </Link>
        <Link to="/user">
            <img src="https://cdn-icons-png.flaticon.com/128/476/476863.png" alt="" />
            User</Link>
    </div>
    );
}
 
export default LandingPage;